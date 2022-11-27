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

	//Muitos restaurantes tem uma cozinha
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false) // nome da FK na tabela Restaurante
	private Cozinha cozinha;
	
}
