package com.emanuel.banco.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SenhaDto {
	
	@NotEmpty(message = "O email é obrigatório.")
	private String email;
	
	@NotEmpty(message = "O cpf é obrigatório.")
	private String cpf;
	
	@NotEmpty(message = "O token é obrigatório.")
	private String token;

	@NotEmpty(message = "A senha é obrigatório.")
	@Length(min = 8, max = 8, message = "A senha deve conter 8 digitos")
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8}$",message="Senha deverá conter no mínimo uma letra minúscula, uma maiúscula, um número, um caractere especial e com o comprimento mínimo de oito caracteres.")  
	private String senha;
}
