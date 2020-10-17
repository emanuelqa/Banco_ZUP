package com.emanuel.banco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emanuel.banco.controller.dto.TransacaoDto;
import com.emanuel.banco.model.Conta;
import com.emanuel.banco.model.Transacao;
import com.emanuel.banco.repository.ContaRepository;
import com.emanuel.banco.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaRepository contaRepository;


	public ResponseEntity<?> transferir(TransacaoDto dto) {
		Optional<Conta> conta = contaRepository.findByContaAndAgencia(dto.getContaDestino(), dto.getAgenciaDestino());
		if (conta.isPresent()) {
			Transacao transacao = new Transacao(dto);
			conta.get().setSaldo(conta.get().getSaldo().add(transacao.getValorTransferencia()));
			contaRepository.save(conta.get());
			transacaoRepository.save(transacao);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
