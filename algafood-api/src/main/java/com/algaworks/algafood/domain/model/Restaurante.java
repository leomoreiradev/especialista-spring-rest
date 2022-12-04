package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(nullable = false)
	private String nome;

	//usa o  nullable = false quando se cria as tabelas a partir das entidades
	//Caso o banco for criado na mão (sem ser a partir das entidades, não precisa do nullable)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	//@ManyToOne = Muitos restaurantes tem uma cozinha
	//@JoinColumn(name = "cozinha_id), o valor name = "cozinha_id"
	//refere-se a o nome da FK na tabela restaurante, ou seja se no banco de dados a FK estiver com um nome "codigo"
	//devemos colocar dentro de @JoinColumn o valor de name = codigo
	// O nullable = false é necessario quando criamos a tabela via hibernate e queremos definir
	//se aquele campo em especifico pode ou não ser nulo
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
}
