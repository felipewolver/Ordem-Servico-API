package com.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.api.model.OrdemServico;
import com.os.api.repository.ordemservico.OrdemServicoRepositoryQuery;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, OrdemServicoRepositoryQuery {

}
