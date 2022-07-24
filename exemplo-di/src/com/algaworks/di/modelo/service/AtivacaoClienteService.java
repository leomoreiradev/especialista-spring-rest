package com.algaworks.di.modelo.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.noticacao.Notificador;


public class AtivacaoClienteService {
	
	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema esta ativo");
	}
}
