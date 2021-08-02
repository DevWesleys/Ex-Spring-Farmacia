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


import br.com.generation.farmacia.model.Produto;
import br.com.generation.farmacia.repository.ProdutoRepository;


@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	private ResponseEntity<List<Produto>> getall(){
		return ResponseEntity.ok(produtoRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Produto> getById(@PathVariable long id){
		
		return produtoRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Produto>> GetByName(@PathVariable String titulo){
		
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));	
	}

	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		
	}
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		
	}
	@DeleteMapping("/{id}")
	public void deleteId(@PathVariable long id){
		produtoRepository.deleteById(id);
		}
}