package com.easysoft.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easysoft.api.domain.Atividade;
import com.easysoft.api.repositories.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository repo;
	
	public Atividade buscar(Integer id) {
		Atividade atividade = repo.getOne(id);
		return atividade;
	}
	
	public List<Atividade> findAll() {
		return repo.findAll();
	}
	
	public Atividade save(Atividade atividade) {
		return repo.save(atividade);
	}
	
	public Optional<Atividade> findById(int id) {
		return repo.findById(id);
	}
	
	public List<Atividade> delete(int id){
		repo.deleteById(id);
		return findAll();
	}
	
	public List<Atividade> findByNomeContaining(String name) {
		return repo.findByNomeContaining(name);
	}
	
	
}
