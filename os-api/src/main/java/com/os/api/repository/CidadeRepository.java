package com.os.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.api.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	List<Cidade> findByEstadoId(Long estadoId);
}
