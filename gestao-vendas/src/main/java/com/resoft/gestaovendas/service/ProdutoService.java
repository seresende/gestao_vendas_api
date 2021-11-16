package com.resoft.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resoft.gestaovendas.entity.Produto;
import com.resoft.gestaovendas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	
	public Optional<Produto> listarId(Long id){
		return produtoRepository.findById(id);
	}
	
	
	public List<Produto> findByCategoriaCodigo(Long id){
		return produtoRepository.findByCategoriaCodigo(id);
	}
	
	public Optional<Produto> buscaPorcodigo(Long codigo, Long codigoCategoria){
		return produtoRepository.buscaPorcodigo(codigo, codigoCategoria);
	}
	
	
	public void delete(Long id){
		produtoRepository.deleteById(id);
	}


}

