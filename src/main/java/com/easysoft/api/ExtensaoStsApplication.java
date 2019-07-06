package com.easysoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.easysoft.api.domain.Atividade;
import com.easysoft.api.repositories.AtividadeRepository;

@SpringBootApplication
public class ExtensaoStsApplication implements CommandLineRunner {
	
	@Autowired
	private AtividadeRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(ExtensaoStsApplication.class, args);
	}
	
	@Override
	public void run(String...strings ) throws Exception {
		Atividade atividade = new Atividade();
		atividade.setNome("Simpos");
		repo.save(atividade);
	}

}
