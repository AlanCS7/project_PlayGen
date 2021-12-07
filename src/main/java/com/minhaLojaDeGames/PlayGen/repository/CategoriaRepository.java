package com.minhaLojaDeGames.PlayGen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.minhaLojaDeGames.PlayGen.model.CategoriaModel;


/**
 * Interface responsável pela tabela Categoria, ela tem a finalidade de se conectar e manipular o banco de dados.
 * @author Wesley Ninaja
 * @since 1.0
 *
 */


@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>{
	public List<CategoriaModel> findAllByCategoriaContainingIgnoreCase (String categoria);
}

