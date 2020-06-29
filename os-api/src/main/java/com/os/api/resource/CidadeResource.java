package com.os.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.os.api.model.Cidade;
import com.os.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepos;
	
	@GetMapping
	public List<Cidade> listar(@RequestParam Long estado) {
		
		return this.cidadeRepos.findByEstadoId(estado);
	}
}
