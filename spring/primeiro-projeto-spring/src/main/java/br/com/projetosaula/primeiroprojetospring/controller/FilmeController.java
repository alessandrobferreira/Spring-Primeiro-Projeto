package br.com.projetosaula.primeiroprojetospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetosaula.primeiroprojetospring.entity.Filme;
import br.com.projetosaula.primeiroprojetospring.repository.FilmeRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	FilmeRepository filmeRepository;

	public FilmeController() {
		
	}
		
	@GetMapping
	public List<Filme> getList() {
		return filmeRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Filme getFilmeById(@PathVariable("id") Long id) throws Exception {
		Filme filme = filmeRepository.findById(id)
				.orElseThrow(() -> new Exception("Filme não encontrado com o id="+ id));
		
		return filme;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean saveFilme(@RequestBody Filme filme) throws Exception {
	  if(filme == null) {
		  throw new Exception("Dados não enviados.");

	  }
	 
	  filmeRepository.save(filme);
	 return true;
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public boolean editFilme(@RequestBody Filme filme) throws Exception {
		if(filme.getId() == null) {
			 throw new Exception("Registro sem Id.");
	}
		
		filmeRepository.findById(filme.getId()).orElseThrow(
				() -> new Exception("Registro não encontrado"));
		
		filmeRepository.save(filme); 
		return true;
	}	
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK)
	public boolean deleteFilme(@RequestBody Long id) throws Exception {
		filmeRepository.findById(id).orElseThrow(
				() -> new Exception("Registro não encontrado!"));
		
		filmeRepository.deleteById(id);
		return true;
	}
}

