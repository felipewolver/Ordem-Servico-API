package com.os.api.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.os.api.model.Pessoa;
import com.os.api.repository.PessoaRepository;
import com.os.api.repository.filter.PessoasFilter;
import com.os.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepos;
	
	@Autowired
	private PessoaService pessoaService;
	
	/*@GetMapping
	public List<Pessoa> listar() {
		
		return this.pessoaRepos.findAll();
	} */
	
	@GetMapping("/consultas")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')") 
	public Pessoa consultar(@RequestParam Long id) {
		
		return this.pessoaRepos.consultaPorId(id);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public Page<Pessoa> pesquisar(PessoasFilter pessoasFilter, Pageable pageable) {
		
		return this.pessoaRepos.filtrar(pessoasFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		
		Optional<Pessoa> pessoaExistente = this.pessoaRepos.findById(id);
		if(pessoaExistente.isPresent()) {
			
			return ResponseEntity.ok(pessoaExistente.get());
		}
		
		// Aqui poder ser usado tanto esta exceção como o ResponseEntity.notFound quer vai retornar o Status 404
		throw new EmptyResultDataAccessException(1);
		//return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa pessoa) {
		
		Pessoa novaPessoa = this.pessoaService.salvar(pessoa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
	
		Pessoa pessoaExistente = pessoaService.atualizarPessoa(pessoa, id);
		
		return ResponseEntity.ok(pessoaExistente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void removerPorId(@PathVariable Long id) {
		
		Optional<Pessoa> pessoaExistente = this.pessoaRepos.findById(id);
		if(pessoaExistente.isPresent()) {
			
			this.pessoaRepos.deleteById(id);
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}

}
