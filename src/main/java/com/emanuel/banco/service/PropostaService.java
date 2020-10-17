package com.emanuel.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.banco.controller.dto.PropostaDTO;
import com.emanuel.banco.model.Conta;
import com.emanuel.banco.model.EnderecoProposta;
import com.emanuel.banco.model.Proposta;
import com.emanuel.banco.repository.ContaRepository;
import com.emanuel.banco.repository.EnderecoPropostaRepository;
import com.emanuel.banco.repository.PropostaRepository;
import com.emanuel.banco.utils.Constantes;

@Service
public class PropostaService {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private EnderecoPropostaRepository enderecoPropostaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Proposta inserir(Proposta proposta) {
		enderecoPropostaRepository.save(proposta.getEnderecoProposta());
		return propostaRepository.save(proposta);
	}
	
	public PropostaDTO buscarPorCpf(String cpf) {
		return fromProposta(propostaRepository.findByCpf(cpf));
	}
	
	public Proposta fromDTO(PropostaDTO propostaDTO) {
		Proposta proposta = new Proposta(null, propostaDTO.getNome(),propostaDTO.getSobrenome(), propostaDTO.getEmail(), propostaDTO.getDataNascimento(),
				propostaDTO.getCpf());
		EnderecoProposta enderecoProposta = new EnderecoProposta(null, propostaDTO.getCep(), propostaDTO.getRua(), propostaDTO.getBairro(),
				propostaDTO.getComplemento(), propostaDTO.getCidade(), propostaDTO.getEstado());
		proposta.setEnderecoProposta(enderecoProposta);
		return proposta;
	}
	
	public PropostaDTO fromProposta(Proposta proposta) {
		return new PropostaDTO(proposta.getNome(), proposta.getSobrenome(), proposta.getEmail(), proposta.getDataNascimento(),
				proposta.getCpf(), proposta.getEnderecoProposta().getCep(), proposta.getEnderecoProposta().getRua(), proposta.getEnderecoProposta().getBairro(),
				proposta.getEnderecoProposta().getComplemento(), proposta.getEnderecoProposta().getCidade(), proposta.getEnderecoProposta().getEstado());
	}

	public void aceita(String cpf, String aceita) {
		Proposta proposta = propostaRepository.findByCpf(cpf);
		if(aceita != null && Constantes.SIM.equals(aceita)) {
			proposta.setAceito(Constantes.SIM);
		}else {
			proposta.setAceito(Constantes.NAO);
		}
		propostaRepository.save(proposta);
	}

	public void aprovar(String cpf, String aprovar) {
		Proposta proposta = propostaRepository.findByCpf(cpf);
		if(aprovar != null && Constantes.SIM.equals(aprovar)) {
			proposta.setAprovada(Constantes.SIM);
			Conta conta = new Conta(proposta);
			contaRepository.save(conta);
			proposta.setConta(conta);
			emailService.sendContaConfirmacao(conta);
		}else {
			proposta.setAprovada(Constantes.NAO);
		}
		propostaRepository.save(proposta);
	}

}
