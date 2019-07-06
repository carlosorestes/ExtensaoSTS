package com.easysoft.api.resource;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easysoft.api.domain.Atividade;
import com.easysoft.api.services.AtividadeService;

@RestController
@RequestMapping(value="/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeService service;
	
	private static final Logger log = LoggerFactory.getLogger(AtividadeResource.class);
	
	/**
	 * Description: Request Test Atividades
	 * @author malaquias
	 * @return String
	 */
	@RequestMapping(method=RequestMethod.GET, path = "/test")
	public String test() {
		log.info("Requisicao Teste: /test ");
		return "Rest Ok";
	}
	
	/**
	 * Description: Request Listar Atividades
	 * @author malaquias
	 * @return listAtividades
	 */
	@RequestMapping(method=RequestMethod.GET, path = "/list")
	public ResponseEntity<List<?>> listar() {
		log.info("Requisicao Teste: /list ");
		return ResponseEntity.ok().body(service.findAll());
	}
	
	/**
	 * Description: Request Listar Atividade por identificador
	 * @author malaquias
	 * @param id
	 * @return atividade
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		try {
			log.info("Buscando Atividades por ID do funcionario: {}", id);
			Atividade atividade = service.buscar(id);
			return ResponseEntity.ok().body(atividade);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	/**
	 * Description: Request Listar Atividade por Nome
	 * @author malaquias
	 * @param id
	 * @return atividade
	 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET, path = "/findByName")
	public ResponseEntity<?> findByName(@PathVariable String name){
		log.info("Buscando Atividades por ID do funcionario: {}", name);
		List<Atividade> atividades = service.findByNomeContaining(name);
		return ResponseEntity.ok().body(atividades);
	}
	
	/**
	 * Description: Request Salva nova Atividade
	 * @param atividade
	 * @return atividade
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Atividade atividade) {
		log.info("Salvando funcionario: {}", atividade.toString());
		return ResponseEntity.ok().body(service.save(atividade));
	}
	
	/**
	 * Description: Request responsavel por atualizar Atividades
	 * @param id
	 * @param atividade
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id,
			@RequestBody Atividade atividade) {
		
		log.info("Atualizando funcionario de codigo: {}", id);
		
		Optional<Atividade> fetchAtividade =  service.findById(id);
		fetchAtividade.get().setNome(atividade.getNome());
		
		return ResponseEntity.ok().body(service.save(fetchAtividade.get()));	
	}
	
	/**
	 * Description: Request Deletar Atividade por identificador
	 * @author malaquias
	 * @param id
	 * @return listAtividade
	 */
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		log.info("Deletando Atividade por ID do funcionario: {}", id);
		
		List<Atividade> listAtividade =  service.delete(id);
		
		return ResponseEntity.ok().body(listAtividade);
	}
	
	
}
