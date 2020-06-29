package com.os.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.os.api.model.Pessoa;
import com.os.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepos;
	
	public Pessoa salvar(Pessoa pessoa) {
		
		return this.pessoaRepos.save(pessoa);
	}
	
	public Pessoa atualizarPessoa(Pessoa pessoa, Long id) {
		
		Pessoa pessoaExistente = buscarPessoaExistente(id);
		
		BeanUtils.copyProperties(pessoa, pessoaExistente, "id");
		
		return this.pessoaRepos.save(pessoaExistente);
	}
	
	private Pessoa buscarPessoaExistente(Long id) {
		
		Optional<Pessoa> pessoaExistente = this.pessoaRepos.findById(id);
		if(pessoaExistente.isPresent()) {
			return pessoaExistente.get();
		}
		
		throw new EmptyResultDataAccessException(1);
	}
}
