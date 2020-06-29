package com.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.api.model.Pessoa;
import com.os.api.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {

}
