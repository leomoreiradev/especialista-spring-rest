package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.noticacao.Notificador;
import com.algaworks.di.modelo.noticacao.NotificadorSMS;
import com.algaworks.di.modelo.noticacao.NotificardorEmail;
import com.algaworks.di.modelo.service.AtivacaoClienteService;

public class Main {

	public static void main(String[] args) {
		Cliente joao = new Cliente("Joao", "joao@gmai.com", "3495955966");
		Cliente maria = new Cliente("Maria", "maria@gmail.com", "349998882");
		
		Notificador notificador = new NotificadorSMS();
		
		AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService(notificador);
		ativacaoClienteService.ativar(joao);
		ativacaoClienteService.ativar(maria);

	}

}
