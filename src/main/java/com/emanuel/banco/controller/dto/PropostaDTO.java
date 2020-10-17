package com.emanuel.banco.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.emanuel.banco.controller.dto.validation.PropostaInsert;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@PropostaInsert
public class PropostaDTO {
	
	@NotEmpty(message = "O nome é obrigatório.")
	private String nome;
	
	@NotEmpty(message = "O sobrenome é obrigatório.")
	private String Sobrenome;
	
	@NotEmpty(message = "O e-mail é obrigatório.")
	@Email(message = "Emails inválido")
	private String email;
	
	@Past(message = "A data deve estar no passado.")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotEmpty(message = "O CPF é obrigatório.")
	private String cpf;
	
	@NotEmpty(message = "O CEP é obrigatório.")
	@Pattern(regexp = "\\d{5}-\\d{3}")
	private String cep;
	
	@NotEmpty(message = "O rua é obrigatório.")
	private String rua;
	
	@NotEmpty(message = "O bairro é obrigatório.")
	private String bairro;
	
	@NotEmpty(message = "O complemento é obrigatório.")
	private String complemento;
	
	@NotEmpty(message = "O cidade é obrigatório.")
	private String cidade;
	
	@NotEmpty(message = "O estado é obrigatório.")
	private String estado;

	public PropostaDTO() {
	}

	public PropostaDTO(String nome, String sobrenome, String email, LocalDate dataNascimento, String cpf, String cep, String rua,
			String bairro, String complemento, String cidade, String estado) {
		this.nome = nome;
		Sobrenome = sobrenome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
	}
}
