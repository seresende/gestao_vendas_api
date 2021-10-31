package com.resoft.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.resoft.gestaovendas.entity.Categoria;
import com.resoft.gestaovendas.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> buscarPorCodigo(Long id){
		return  categoriaRepository.findById(id);
		
	}
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria Atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExiste(codigo);
		//Copiar a categoria sobre a categoria que veio do banco com excessão o código
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
		return categoriaRepository.save(categoriaSalvar);
	}

	public void delete(Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
	
	private Categoria validarCategoriaExiste(Long codigo) {
		Optional<Categoria> categoria = buscarPorCodigo(codigo);
		if(categoria.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoria.get();
		
	}
	
	
}
