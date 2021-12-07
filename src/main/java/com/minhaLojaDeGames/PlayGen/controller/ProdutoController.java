package com.minhaLojaDeGames.PlayGen.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.minhaLojaDeGames.PlayGen.model.ProdutoModel;
import com.minhaLojaDeGames.PlayGen.repository.ProdutoRepository;

/**
 * Classe responsável pelo CRUD
 * 
 * @author Josiane Caroliny
 * @since 1.0
 * 
 */

@RestController
@RequestMapping("/playGen/produtos")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutoModel>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> GetById(@PathVariable(value = "id") long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não existe"));
	}

	@GetMapping("/nome/{produto}")
	public ResponseEntity<List<ProdutoModel>> GetByProduto(@PathVariable String produto) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(produto));
	}

	@PostMapping("/save")
	public ResponseEntity<ProdutoModel> post(@Valid @RequestBody ProdutoModel produto) {
		return ResponseEntity.status(201).body(repository.save(produto));
	}

	@PutMapping("/update")
	public ResponseEntity<ProdutoModel> put(@Valid @RequestBody ProdutoModel produto) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		repository.deleteById(id);
	}
}
