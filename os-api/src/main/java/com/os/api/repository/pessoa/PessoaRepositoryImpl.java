package com.os.api.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.os.api.model.Pessoa;
import com.os.api.repository.filter.PessoasFilter;


public class PessoaRepositoryImpl implements PessoaRepositoryQuery {
    
	@PersistenceContext
	private EntityManager entityManager;
	
    
	// Consulta usando jpql que vai listar todas as pessas
	@Override
	public List<Pessoa> consultaJpql() {
		
		String jpql = "select p from Pessoa p"; // p - alias que vai buscar todos dados. - Pessoa quem vem da classe Pessoa
		TypedQuery<Pessoa> typedQuery = this.entityManager.createQuery(jpql, Pessoa.class);
		
		return typedQuery.getResultList();
	}
	
	@Override
	public Pessoa consultaPorId(Long id) {
  		
		String jqplId = "select p from Pessoa p where p.id = :idPessoa"; // id - propiedade da Classe Pessoa - :idPessoa - alias como parametro de busca
		TypedQuery<Pessoa> typedQuery = this.entityManager.createQuery(jqplId, Pessoa.class);
		typedQuery.setParameter("idPessoa", id);
		
		return typedQuery.getSingleResult();
	}

	
	@Override
	public Page<Pessoa> filtrar(PessoasFilter pessoasFilter, Pageable pageable) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoasFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		// Vai listar o campo nome em ordem alfabetica de A - Z 
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));
		
		TypedQuery<Pessoa> query = this.entityManager.createQuery(criteriaQuery);
		adicionarRestricoesDePagina(query, pageable);
		
		return new PageImpl<Pessoa>(query.getResultList(), pageable, total(pessoasFilter));
	}
	
	
	private Predicate[] criarRestricoes(PessoasFilter pessoasFilter, CriteriaBuilder builder, Root<Pessoa> root ) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(pessoasFilter.getNome()) ) {
			predicates.add(builder.like(builder.lower(root.get("nome"))
					, "%" + pessoasFilter.getNome().toLowerCase()+ "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePagina(TypedQuery<?> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	} 
	
	private Long total(PessoasFilter pessoasFilter) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoasFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		return this.entityManager.createQuery(criteriaQuery).getSingleResult();
	}


}
