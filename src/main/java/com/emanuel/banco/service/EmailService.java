package com.emanuel.banco.service;

import org.springframework.mail.SimpleMailMessage;

import com.emanuel.banco.model.Conta;

public interface EmailService {
	
	void sendContaConfirmacao(Conta conta);
	
	void sendPrimeiroAcesso(Conta conta);
	
	void sendDefinirSenha(Conta conta);
	
	void sendEmail(SimpleMailMessage msg);

//	void sendNewPasswordEmail(Cliente cliente, String newPassword);

}
