package com.os.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.os.api.model.Estado;
import com.os.api.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoRepository estadoRepos;
	
	@GetMapping
	public List<Estado> listar() {
		
		return this.estadoRepos.findAll();
	}
}
