package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.modelo.noticacao.Notificador;
import com.algaworks.di.modelo.noticacao.NotificardorEmail;

public class EmissaoNotaFiscalService {
	
	
private Notificador notificador;
	
	public EmissaoNotaFiscalService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public void emitir(Cliente cliente, Produto produto) {
		// emite a nota fiscal aqui...
		
		NotificardorEmail notificardorEmail = new NotificardorEmail();
		notificardorEmail.notificar(cliente, "Nota fiscal do produto " 
		+ produto.getNome() + " foi emitida" );
	}
}
