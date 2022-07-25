package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	//Injeta um EntityManager
	@PersistenceContext 
	EntityManager manager;
	
	public List<Cozinha> listar() {
		//TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
			
	}
}
