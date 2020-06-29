package com.os.api.repository.pessoa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.os.api.model.Pessoa;
import com.os.api.repository.filter.PessoasFilter;

public interface PessoaRepositoryQuery {
    
	public Page<Pessoa> filtrar(PessoasFilter pessoasFilter, Pageable pageable);
	
	public List<Pessoa> consultaJpql();
	
	public Pessoa consultaPorId(Long id);
}
