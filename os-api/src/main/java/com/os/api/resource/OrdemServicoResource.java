package com.os.api.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.os.api.dto.OsEstatisticaPorDia;
import com.os.api.dto.OsEstatisticaPorStatus;
import com.os.api.model.OrdemServico;
import com.os.api.model.Servico;
import com.os.api.repository.OrdemServicoRepository;
import com.os.api.repository.filter.OrdemServicosFilter;
import com.os.api.repository.projection.OrdemServicoProjection;
import com.os.api.service.OSService;
@RestController
@RequestMapping("/ordem-servicos")
public class OrdemServicoResource {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepos;
	
	@Autowired
	private OSService osService;
	
	/* Antigo metodo que lista todas as OSs
	@GetMapping
	public List<OrdemServico> listar() {
		
		return this.ordemServicoRepos.findAll();
	} */
	
	
	@GetMapping("/estatisticas/por-status")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_OS') and #oauth2.hasScope('read')")
	public List<OsEstatisticaPorStatus> estatisticaPorStatus() {
		
		return this.ordemServicoRepos.porStatus(LocalDate.now()); // consulta de valores mais antigos LocalDate.now().withYear(2020).withMonth(5)
	}
	
	@GetMapping("/relatorios/por-status")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_OS') and #oauth2.hasScope('read')")
	public List<OsEstatisticaPorStatus> estatisticaPorDia() {
		
		return this.ordemServicoRepos.porStatus(LocalDate.now().withYear(2020).withMonth(5)); // consulta de valores mais antigos LocalDate.now().withYear(2020).withMonth(5)
	}
	
	@GetMapping("/relatorios")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_OS') and #oauth2.hasScope('read')")
	public ResponseEntity<byte[]> pesquisarOs(@RequestParam Long id) throws Exception {
		
		byte[] relatorio = this.osService.relatorioDeOS(id);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
			.header("Content-Disposition", "attachment; filename=RelatorioDeOs.pdf")
			.body(relatorio);
	} 
	
	/* teste de consulta com jpql 
	@GetMapping("/teste-relatorios")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_OS') and #oauth2.hasScope('read')")
	public List<OrdemServicoProjection> testar(@RequestParam Long id) {
		
		return this.ordemServicoRepos.relatorioDeOs(id);
	} */
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_OS') and #oauth2.hasScope('read')")
	public Page<OrdemServico> pesquisar(OrdemServicosFilter osFilter, Pageable pageable) {
		
		return this.ordemServicoRepos.filtrar(osFilter, pageable);
	} 
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_OS') and #oauth2.hasScope('read')")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		
		Optional<OrdemServico> osExistente = this.ordemServicoRepos.findById(id);
		if(osExistente.isPresent()) {
			
			return ResponseEntity.ok(osExistente.get());
		} 
		
		throw new EmptyResultDataAccessException(1);
		
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_OS') and #oauth2.hasScope('write')")
	public ResponseEntity<OrdemServico> adicionar(@Valid @RequestBody OrdemServico ordemServico) {
		
		OrdemServico novaOS = this.osService.salvar(ordemServico);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novaOS);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_OS') and #oauth2.hasScope('write')")
	public ResponseEntity<OrdemServico> atualizar(@PathVariable Long id, @Valid @RequestBody OrdemServico ordemServico) {
		
		OrdemServico osExistente = this.osService.atualizarOS(ordemServico, id);
		return ResponseEntity.ok(osExistente);
    }
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_OS') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		
		Optional<OrdemServico> osExistente = this.ordemServicoRepos.findById(id);
		if(osExistente.isPresent()) {
			this.ordemServicoRepos.deleteById(id);
		} else {
		
			throw new EmptyResultDataAccessException(1);
		
		}
	}

}
