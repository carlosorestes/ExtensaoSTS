package com.easysoft.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.easysoft.api.domain.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
	
	public List<Atividade> findByNomeContaining(@Param("nome") String nome);

}
