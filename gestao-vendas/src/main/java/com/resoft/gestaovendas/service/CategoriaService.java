package com.resoft.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Optional<Categoria> buscarPorId(Long id){
		return  categoriaRepository.findById(id);
		
	}
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
