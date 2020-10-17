package com.emanuel.banco.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.emanuel.banco.model.Conta;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendContaConfirmacao(Conta obj) {
		SimpleMailMessage sm = prepararSimpleMailMessage(obj);
		sendEmail(sm);
	}
	
	@Override
	public void sendPrimeiroAcesso(Conta obj) {
		SimpleMailMessage sm = prepararMailPrimeiroAcesso(obj);
		sendEmail(sm);
	}
	
	@Override
	public void sendDefinirSenha(Conta obj) {
		SimpleMailMessage sm = prepararMailDefinirSenha(obj);
		sendEmail(sm);
	}

	private SimpleMailMessage prepararMailDefinirSenha(Conta obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getProposta().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Parabéns, sua senha foi definida com sucesso");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Parabéns, sua senha foi definida com sucesso");
		return sm;
	}

	private SimpleMailMessage prepararMailPrimeiroAcesso(Conta obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getProposta().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Parabéns, você esta realizando o primeiro acesso");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Token de acesso: " + obj.getToken());
		return sm;
	}

	protected SimpleMailMessage prepararSimpleMailMessage(Conta obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getProposta().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Parabéns, sua conta foi aprovada!!!!!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Agencia: "+ obj.getAgencia()+ "\nConta: " + obj.getConta()+ "\nCodigo Banco: " + obj.getCodigoBanco());
		return sm;
	}
	
}
