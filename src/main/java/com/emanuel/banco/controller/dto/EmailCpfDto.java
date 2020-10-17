package com.emanuel.banco.controller.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class EmailCpfDto {

	@NotEmpty(message = "O email é obrigatório.")
	private String email;
	
	@NotEmpty(message = "O cpf é obrigatório.")
	private String cpf;
	
}
