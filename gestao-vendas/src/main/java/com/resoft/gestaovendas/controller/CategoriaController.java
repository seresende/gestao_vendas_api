package com.resoft.gestaovendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resoft.gestaovendas.entity.Categoria;
import com.resoft.gestaovendas.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listarTodas(){
		return categoriaService.listarTodas();
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaService.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
		Categoria categoriaSave = categoriaService.salvar(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSave);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @RequestBody Categoria categoria){
	
		return ResponseEntity.ok(categoriaService.Atualizar(codigo, categoria));
	}

}
