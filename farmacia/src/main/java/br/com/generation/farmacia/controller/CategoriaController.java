package br.com.generation.farmacia.controller;

import java.util.List;

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

import br.com.generation.farmacia.model.Categoria;
import br.com.generation.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	private ResponseEntity<List<Categoria>> getall(){
		
		return ResponseEntity.ok(categoriaRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> getById(@PathVariable long id){
		
		return categoriaRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> GetByDescricao(@PathVariable String descricao){
		
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));	
	}

	
	@PostMapping
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
		
	}
	@PutMapping
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
		
	}
	@DeleteMapping("/{id}")
	public void deleteId(@PathVariable long id){
		categoriaRepository.deleteById(id);
		}
}