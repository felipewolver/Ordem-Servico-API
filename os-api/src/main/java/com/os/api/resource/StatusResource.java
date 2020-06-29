package com.os.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.os.api.model.Status;
import com.os.api.repository.StatusRepository;

@RestController
@RequestMapping("/status")
public class StatusResource {
	
	@Autowired
	private StatusRepository statusRepos;
	
	@GetMapping
	public List<Status> listar() {
		
		return this.statusRepos.findAll();
	}

}
