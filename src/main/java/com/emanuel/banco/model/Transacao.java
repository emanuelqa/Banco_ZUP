package com.emanuel.banco.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.emanuel.banco.controller.dto.TransacaoDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class Transacao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valorTransferencia;
	private LocalDateTime dataTransferencia = LocalDateTime.now();
	private Integer identificadorOrigemRecebida;
	private Integer bancoOrigem;
	private Integer contaOrigem;
	private Integer agenciaOrigem;
	private Integer codigoUnicoTransferenciaBancoOrigem;
	private Integer contaDestino;
	private Integer agenciaDestino;
	
	public Transacao(TransacaoDto dto) {
		this.valorTransferencia = dto.getValorTransferencia();
		this.identificadorOrigemRecebida = dto.getIdentificadorOrigemRecebida();
		this.bancoOrigem = dto.getBancoOrigem();
		this.contaOrigem = dto.getContaOrigem();
		this.agenciaOrigem = dto.getAgenciaOrigem();
		this.codigoUnicoTransferenciaBancoOrigem = dto.getCodigoUnicoTransferenciaBancoOrigem();
		this.contaDestino = dto.getContaDestino();
		this.agenciaDestino = dto.getAgenciaDestino();
	}

}
