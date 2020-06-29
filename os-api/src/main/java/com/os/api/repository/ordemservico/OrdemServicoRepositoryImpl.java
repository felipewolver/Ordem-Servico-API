package com.os.api.repository.ordemservico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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

import com.os.api.dto.OsEstatisticaPorStatus;
import com.os.api.model.OrdemServico;
import com.os.api.repository.filter.OrdemServicosFilter;
import com.os.api.repository.projection.OrdemServicoProjection;


public class OrdemServicoRepositoryImpl implements OrdemServicoRepositoryQuery {
    
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<OrdemServicoProjection> relatorioDeOs(Long id) {
		
		/* Em SQL para testes: select o.os, o.equipamento, s.id, s.descricao, 
    		p.id, p.nome from osapi.ordem_servico o, osapi.servico s,
			osapi.pessoa p
			where s.os_ordem_servico = o.os and o.id_pessoa = p.id 
    		and o.os = 1 group by s.id; feito no programa sql workbench */
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdemServicoProjection> criteriaQuery = criteriaBuilder.createQuery(OrdemServicoProjection.class);
		Root<OrdemServico> root = criteriaQuery.from(OrdemServico.class);
			
		criteriaQuery.select(criteriaBuilder.construct(OrdemServicoProjection.class, root.get("os"), root.get("pessoa"), 
				root.get("categoria"), root.get("equipamento"), root.get("descricao"), root.get("defeito"), 
				root.get("dataRecebimento"), root.get("dataEntrega"), root.get("valor"), root.get("laudoTecnico"), 
				root.get("garantia") )); 
		
		/*criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get("dataRecebimento"), dataInicial),
				criteriaBuilder.lessThanOrEqualTo(root.get("dataRecebimento"), dataFinal)); */
	    
	    criteriaQuery.where(criteriaBuilder.equal(root.get("os"), id)); 
	    
	  //  criteriaQuery.groupBy(root.get("os"));
		
		TypedQuery<OrdemServicoProjection> typedQuery = this.entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
		
	}
	
	// Não estah sendo usado no projeto
	@Override
	public List<OsEstatisticaPorStatus> relatorioPorStatus(LocalDate dataInicio, LocalDate dataFim) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OsEstatisticaPorStatus> criteriaQuery = criteriaBuilder.createQuery(OsEstatisticaPorStatus.class);
		Root<OrdemServico> root = criteriaQuery.from(OrdemServico.class);
		
		criteriaQuery.select(criteriaBuilder.construct(OsEstatisticaPorStatus.class, root.get("status"), 
				root.get("dataRecebimento"), criteriaBuilder.sum(root.get("valor")) ));
		
		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get("dataRecebimento"), dataInicio),
				criteriaBuilder.lessThanOrEqualTo(root.get("dataRecebimento"), dataFim));
		
		criteriaQuery.groupBy(root.get("status"), root.get("dataVencimento"));
		
		TypedQuery<OsEstatisticaPorStatus> query = this.entityManager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	@Override
	public List<OsEstatisticaPorStatus> porStatus(LocalDate mesReferencia) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OsEstatisticaPorStatus> criteriaQuery = criteriaBuilder.createQuery(OsEstatisticaPorStatus.class);
		Root<OrdemServico> root = criteriaQuery.from(OrdemServico.class);
		
		criteriaQuery.select(criteriaBuilder.construct(OsEstatisticaPorStatus.class, root.get("status"), 
				criteriaBuilder.sum(root.get("valor")) ));
		
		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia   = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());
		
		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get("dataRecebimento"), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get("dataRecebimento"), ultimoDia));
		
		criteriaQuery.groupBy(root.get("status"));
		
		TypedQuery<OsEstatisticaPorStatus> query = this.entityManager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	@Override
	public Page<OrdemServico> filtrar(OrdemServicosFilter osFilter, Pageable pageable) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdemServico> criteriaQuery = criteriaBuilder.createQuery(OrdemServico.class);
		Root<OrdemServico> root = criteriaQuery.from(OrdemServico.class);
		
		Predicate[] predicates = criarRestricoes(osFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("pessoa").get("nome")) );
		
		TypedQuery<OrdemServico> query = this.entityManager.createQuery(criteriaQuery);
		adicionarRestricoesDePagina(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(osFilter));
	}
	
	
	private void adicionarRestricoesDePagina(TypedQuery<?> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private Predicate[] criarRestricoes(OrdemServicosFilter osFilter, CriteriaBuilder builder, Root<OrdemServico> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(osFilter.getEquipamento()) ) {
			predicates.add(builder.like(builder.lower(root.get("equipamento"))
					, "%"+ osFilter.getEquipamento().toLowerCase()+ "%"));
		}
		
		if(osFilter.getDataRecebimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataRecebimento"), 
					osFilter.getDataRecebimentoDe()));
		}
		
		if(osFilter.getDataRecebimentoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataRecebimento")
					, osFilter.getDataRecebimentoAte()));
		}
		return predicates.toArray(new Predicate[predicates.size()] );
	}
	
	private Long total(OrdemServicosFilter osFilter) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<OrdemServico> root = criteriaQuery.from(OrdemServico.class);
		
		Predicate[] predicates = criarRestricoes(osFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		return this.entityManager.createQuery(criteriaQuery).getSingleResult();
	}

	

}
