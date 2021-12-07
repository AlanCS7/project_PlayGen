package com.minhaLojaDeGames.PlayGen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minhaLojaDeGames.PlayGen.model.enums.ProdutoEnums;

/**
 * Classe responsável pela tabela produtos no banco de dados
 * 
 * @author Guilherme Cruz
 * @since 1.0
 * 
 */

@Entity
@Table(name = "tb_produtos")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;

	@NotBlank
	@Size(min = 2, max = 100)
	private String nome;

	@NotBlank
	@Size(min = 2, max = 100)
	private String desenvolvedora;

	@NotNull
	private Double preco;

	@Column(columnDefinition = "ENUM('PLAYSTATION', 'XBOX', 'PC', 'NINTENDO', 'MULTIPLATAFORMA')")
	@Enumerated(EnumType.STRING)
	private ProdutoEnums plataforma;

	@ManyToOne
	@JoinColumn(name = "fk_categoria")
	@JsonIgnoreProperties("produtos")
	private CategoriaModel categoria;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ProdutoEnums getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(ProdutoEnums plataforma) {
		this.plataforma = plataforma;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

}
