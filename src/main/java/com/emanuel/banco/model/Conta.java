package com.emanuel.banco.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.emanuel.banco.utils.Constantes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class Conta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer agencia;
	private Integer conta;
	private Integer codigoBanco;
	private BigDecimal saldo;
	@OneToOne
	private Proposta proposta;
	private String primeiroAcesso = Constantes.SIM;
	private String token;
	private String isTokenUtilizado = Constantes.NAO;
	private LocalDateTime dataExpiracaoToken;
	private String senha;
	
	public Conta() {
	}
	
	public Conta(Proposta proposta) {
		Random gerador = new Random();
		this.proposta = proposta;
		this.agencia = gerador.nextInt((9999 - 1000) + 1) + 1000;
		this.conta = gerador.nextInt((99999999 - 10000000) + 1) + 10000000;
		this.codigoBanco = 123;
		this.saldo = new BigDecimal(0);
	}
}
