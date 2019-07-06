package com.easysoft.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.easysoft.api.domain.Atividade;
import com.easysoft.api.repositories.AtividadeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtividadeRepositoryTest {
	
	@Autowired
	private AtividadeRepository repo;
	
	@Test
	public void verificaQuantidadeCadastrada() {
		Page<Atividade> atividades = this.repo.findAll(PageRequest.of(0, 10));
		assertThat(atividades.getTotalElements()).isGreaterThan(1L);
	}
	
	@Test
	public void findByNome() {
		List<Atividade> atividades = this.repo.findByNomeContaining("Malaquias");
		assertThat(atividades.size()).isEqualTo(0);
	}
	
	@Test
	public void find() {
		List<Atividade> atividades = this.repo.findByNomeContaining("Simpos");
		assertThat(atividades.size()).isEqualTo(1);
	}

}
