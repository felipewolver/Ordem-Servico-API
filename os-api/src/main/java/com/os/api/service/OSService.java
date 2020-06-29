package com.os.api.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.os.api.dto.OsEstatisticaPorStatus;
import com.os.api.model.Categoria;
import com.os.api.model.OrdemServico;
import com.os.api.model.Pessoa;
import com.os.api.model.Status;
import com.os.api.repository.CategoriaRepository;
import com.os.api.repository.OrdemServicoRepository;
import com.os.api.repository.PessoaRepository;
import com.os.api.repository.StatusRepository;
import com.os.api.repository.projection.OrdemServicoProjection;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class OSService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepos;
	
	@Autowired
	private StatusRepository statusRepos;
	
	@Autowired
	private PessoaRepository pessoaRepos;
	
	@Autowired
	private CategoriaRepository catRepos;
	
	
	public byte[] relatorioDeOS(Long id) throws Exception {
		
		List<OrdemServicoProjection> dados = this.ordemServicoRepos.relatorioDeOs(id);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("DT_INICIO", Date.valueOf(dataInicio));
		//parametros.put("DT_FIM", Date.valueOf(dataFim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/ordem-servicos-pessoas.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, 
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	} 
	
	public OrdemServico salvar(OrdemServico ordemServico) {
		
		ordemServico.getServicos().forEach(s -> s.setOrdemServico(ordemServico));
		
		validarCodigos(ordemServico);
		
		return this.ordemServicoRepos.save(ordemServico);
	}
	
	public OrdemServico atualizarOS(OrdemServico ordemServico, Long id) {
		
		OrdemServico osExistente = buscarOSExistente(id);
		osExistente.getServicos().clear();
		osExistente.getServicos().addAll(ordemServico.getServicos());
		osExistente.getServicos().forEach(s -> s.setOrdemServico(osExistente));
		
		validarCodigos(ordemServico);
		
		BeanUtils.copyProperties(ordemServico, osExistente, "id", "servicos");
		
		return this.ordemServicoRepos.save(osExistente);
	}
	
	
	private OrdemServico buscarOSExistente(Long id) {
		
		Optional<OrdemServico> osExistente = this.ordemServicoRepos.findById(id);
		if(osExistente.isPresent()) {
			
			return osExistente.get();
		}
		
		throw new EmptyResultDataAccessException(1);
		
	}
	
	// Vai verificar os códigos se eles existem(não estão null) antes de salvar.
	private void validarCodigos(OrdemServico ordemServico) {
		
		Optional<Pessoa> pessoaExistente = this.pessoaRepos.findById(ordemServico.getPessoa().getId());
		Optional<Categoria> catExistente = this.catRepos.findById(ordemServico.getCategoria().getId());
		Optional<Status> statusExistente = this.statusRepos.findById(ordemServico.getStatus().getId());
		if(!statusExistente.isPresent() || !pessoaExistente.isPresent() || !catExistente.isPresent()) {
			
			/* Vai entrar nesta exceção caso venha ocorrer algum erro status 500 Internal Server Error vindo do metodo 
			   illegalArgumentException() da classe OSExceptioHandler
			 */
			throw new IllegalArgumentException();
		}
	}
	
}
