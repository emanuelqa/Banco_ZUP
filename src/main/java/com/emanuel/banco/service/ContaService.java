package com.emanuel.banco.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emanuel.banco.controller.dto.EmailCpfDto;
import com.emanuel.banco.controller.dto.SenhaDto;
import com.emanuel.banco.model.Conta;
import com.emanuel.banco.model.Proposta;
import com.emanuel.banco.repository.ContaRepository;
import com.emanuel.banco.repository.PropostaRepository;
import com.emanuel.banco.utils.Constantes;
import com.emanuel.banco.utils.Utils;

@Service
public class ContaService {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Value("${default.expiracaoToken}")
	private Long expiracaoToken;

	public ResponseEntity<?> primeiroAcesso(EmailCpfDto dto) {
		Proposta proposta = propostaRepository.findByCpfAndEmail(dto.getCpf(), dto.getEmail());
		if(proposta != null) {
			Conta conta = proposta.getConta();
			if(Constantes.SIM.equals(conta.getPrimeiroAcesso())){
				conta.setPrimeiroAcesso(Constantes.NAO);
				conta.setToken(Utils.gerarToken());
				conta.setDataExpiracaoToken(LocalDateTime.now().plusMinutes(expiracaoToken));
				emailService.sendPrimeiroAcesso(proposta.getConta());
				contaRepository.save(conta);
				return ResponseEntity.ok(null);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	public ResponseEntity<?> definirSenha(SenhaDto dto) {
		Proposta proposta = propostaRepository.findByCpfAndEmail(dto.getCpf(), dto.getEmail());
		
		if(proposta != null && proposta.getConta() != null) {
			Conta conta = proposta.getConta();
			
			if(Constantes.SIM.equals(conta.getIsTokenUtilizado())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			
			if(conta.getDataExpiracaoToken().isBefore(LocalDateTime.now())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			
			if(conta.getToken().equals(dto.getToken())) {
					conta.setSenha(Utils.criptografar(dto.getSenha()));
					conta.setIsTokenUtilizado(Constantes.SIM);
					contaRepository.save(conta);
					emailService.sendDefinirSenha(proposta.getConta());
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
		}
	}

}
