package com.minhaLojaDeGames.PlayGen.repository;

import java.util.List;

import com.minhaLojaDeGames.PlayGen.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

	/**
	 * 
	 * Interface responsavel por se conectar e manipular o banco de dados
	 * @author AlanCS7
	 * @since 1.0
	 * 
	 */

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{

	public List<ProdutoModel> findAllByNomeContainingIgnoreCase (String nome); 
	
}
