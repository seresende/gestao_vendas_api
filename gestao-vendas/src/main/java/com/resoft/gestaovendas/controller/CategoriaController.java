package com.resoft.gestaovendas.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.resoft.gestaovendas.entity.Categoria;
import com.resoft.gestaovendas.service.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "Listar",nickname = "listarTodas")
	@GetMapping
	public List<Categoria> listarTodas(){
		return categoriaService.listarTodas();
		
	}
	
	@ApiOperation(value = "Listar por Código",nickname = "buscarPorId")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaService.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Salvar",nickname = "salvar")
	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
		Categoria categoriaSave = categoriaService.salvar(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSave);
	}
	
	@ApiOperation(value = "Atualizar", nickname = "atualizar")
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Long codigo, @RequestBody Categoria categoria){
	
		return ResponseEntity.ok(categoriaService.Atualizar(codigo, categoria));
	}
	
	@ApiOperation(value = "Deletar", nickname = "deletar")
	@DeleteMapping("/{codigo}")
	//esta anotação é para qando não retornamos nada
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		 categoriaService.delete(codigo);
	}

}
