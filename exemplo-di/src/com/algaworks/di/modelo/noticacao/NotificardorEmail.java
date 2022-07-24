package com.algaworks.di.modelo.noticacao;

import com.algaworks.di.modelo.Cliente;

public class NotificardorEmail implements Notificador{
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s atraves do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
