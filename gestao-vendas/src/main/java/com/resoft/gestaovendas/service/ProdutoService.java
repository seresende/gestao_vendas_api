package com.resoft.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resoft.gestaovendas.entity.Produto;
import com.resoft.gestaovendas.exception.RegraNegocioException;
import com.resoft.gestaovendas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
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
	
	public Produto salvar(Produto produto) {
		validarCatDoProdutoExiste(produto.getCategoria().getCodigo());
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}

   
	private void validarCatDoProdutoExiste(Long codCategoria) {
		
		if (codCategoria == null) {
			throw new RegraNegocioException("A categoria não pode ser nula!!");
		}
		
		if(categoriaService.buscarPorCodigo(codCategoria).isEmpty()) {
			throw new RegraNegocioException(String.format("A categoria de código %s informada não existe no cadastro!", codCategoria));
		}
		
	}
	
	private void validarProdutoDuplicado(Produto produto) {
		
		if(produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao()).isPresent()) {
			throw new RegraNegocioException(String.format("O produto %s já se encontra cadastrado", produto.getDescricao()));
		}
	 
	}

}

