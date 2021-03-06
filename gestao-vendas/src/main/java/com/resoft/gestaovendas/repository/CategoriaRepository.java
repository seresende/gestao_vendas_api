package com.resoft.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resoft.gestaovendas.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	//spring data jpa(query creates)
	Categoria findByNome(String nome);

}
