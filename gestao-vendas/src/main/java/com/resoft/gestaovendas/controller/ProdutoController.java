package com.resoft.gestaovendas.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resoft.gestaovendas.entity.Produto;
import com.resoft.gestaovendas.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

		
	
	@ApiOperation(value = "Listar", nickname = "listarTodas")
	@GetMapping
	public List<Produto> listarTodas(@PathVariable Long codigoCategoria) {
		return produtoService.findByCategoriaCodigo(codigoCategoria);

	}

	@ApiOperation(value = "Listar por Código", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> buscarPorCodigo(@PathVariable Long codigoCategoria, @PathVariable Long codigo) {
		Optional<Produto> produto = produtoService.buscaPorcodigo(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Salvar", nickname = "salvarProduto")
	@PostMapping
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produto));
	}

}
