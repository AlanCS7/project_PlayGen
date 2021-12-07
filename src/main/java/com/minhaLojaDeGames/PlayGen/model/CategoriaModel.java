package com.minhaLojaDeGames.PlayGen.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * Classe responsável pela tabela categoria no banco de dados
 * 
 * @author fabriciorocha
 * @since 1.0
 * 
 */

@Entity
@Table(name = "tb_categoria")
public class CategoriaModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCategoria;

	private @NotBlank String categoria;

	private @NotBlank String subcategoria;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	private List<ProdutoModel> produtos = new ArrayList<>();

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

}
