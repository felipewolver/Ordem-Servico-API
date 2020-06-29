package com.os.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("Senha secret client: "+ encoder.encode("@ngul@r0")); // para servidor cliente 
		System.out.println("Senha user: "+ encoder.encode("maria")); // para usuario do sistema. 

	}

}
