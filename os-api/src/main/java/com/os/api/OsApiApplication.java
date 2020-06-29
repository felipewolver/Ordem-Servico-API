package com.os.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.os.api.config.property.OSApiProperty;
import com.os.api.model.Pessoa;

@SpringBootApplication
@EnableConfigurationProperties(OSApiProperty.class)
public class OsApiApplication {
    
	

	public static void main(String[] args) {
		SpringApplication.run(OsApiApplication.class, args);
	    
	   // EntityManagerFactory entityManagerFactory = Persistence
       //         .createEntityManagerFactory("Pessoas-PU");
	    	    		   
	}
		
    

}
