package com.emanuel.banco.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransacaoDto {
	
	@NotNull(message = "O valor da trânsferencia é obrigatório.")
	private BigDecimal valorTransferencia;
	
	@NotNull(message = "O documento identificador da origem da transferência recebida é obrigatório.")
	private Integer identificadorOrigemRecebida;
	
	@NotNull(message = "O banco origem é obrigatório.")
	private Integer bancoOrigem;
	
	@NotNull(message = "A conta origem é obrigatório.")
	private Integer contaOrigem;
	
	@NotNull(message = "A agencia origem é obrigatório.")
	private Integer agenciaOrigem;
	
	@NotNull(message = "O código único da transferência para aquele determinado banco de origem é obrigatório.")
	private Integer codigoUnicoTransferenciaBancoOrigem;
	
	@NotNull(message = "A conta destino é obrigatório.")
	private Integer contaDestino;
	
	@NotNull(message = "A agencia destinoé obrigatório.")
	private Integer agenciaDestino;
	
}
