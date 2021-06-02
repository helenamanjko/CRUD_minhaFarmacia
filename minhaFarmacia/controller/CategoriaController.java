package org.generation.minhaFarmacia.controller;

import java.util.List;

import org.generation.minhaFarmacia.model.Categoria;
import org.generation.minhaFarmacia.model.Produto;
import org.generation.minhaFarmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


	@RestController
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping("/categorias")
	public class CategoriaController {
			
		@Autowired
		private CategoriaRepository repository;
		
		@GetMapping ResponseEntity<List<Categoria>> getAll(){
			return ResponseEntity.ok(repository.findAll());
		}
		@GetMapping("/{id}")
		public ResponseEntity<Categoria> GetById(@PathVariable long id){
			return repository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
		}
		@GetMapping("/setor/{setor}")
		public ResponseEntity<List<Categoria>> getByName(@PathVariable String setor){
			return ResponseEntity.ok(repository.findAllBySetorContainingIgnoreCase(setor));
		}
		@PostMapping
		public ResponseEntity<Categoria> post (@RequestBody Categoria setor){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(repository.save(setor));
		}
		@PutMapping
		public ResponseEntity<Categoria> put (@RequestBody Categoria setor){
			return ResponseEntity.ok(repository.save(setor));
		}
		@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
			repository.deleteById(id);
		}


}
