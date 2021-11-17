package com.resoft.gestaovendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.resoft.gestaovendas.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findByCategoriaCodigo(Long codigoCategoria);
	
	@Query("Select prod"
			+ " from Produto prod"
			+ " where prod.codigo = :codigo"
			+ "   and prod.categoria.codigo = :codigoCategoria")
	Optional<Produto> buscaPorcodigo(Long codigo, Long codigoCategoria);
	
	//Pesquiso se tem naquela categoria aquele produto
	Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria,String descricao);

}
