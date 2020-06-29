package com.os.api.repository.ordemservico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.os.api.dto.OsEstatisticaPorStatus;
import com.os.api.model.OrdemServico;
import com.os.api.repository.filter.OrdemServicosFilter;
import com.os.api.repository.projection.OrdemServicoProjection;

public interface OrdemServicoRepositoryQuery {
	
	List<OsEstatisticaPorStatus> porStatus(LocalDate mesReferencia);
	List<OsEstatisticaPorStatus> relatorioPorStatus(LocalDate dataInicio, LocalDate dataFim);
	List<OrdemServicoProjection> relatorioDeOs(Long id);
	
	Page<OrdemServico> filtrar(OrdemServicosFilter osFilter, Pageable pageable);
	
	
}
