package com.emanuel.banco.model;

import java.time.LocalDate;

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
public class Proposta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String Sobrenome;
	private String email;
	private LocalDate dataNascimento;
	private String cpf;
	private String aceito = Constantes.NAO;
	private String aprovada = Constantes.NAO;
	@OneToOne
	private EnderecoProposta enderecoProposta;
	@OneToOne(mappedBy = "proposta")
	private Conta conta;
	
	public Proposta() {
	}
	
	public Proposta(Long id, String nome, String sobrenome, String email, LocalDate dataNascimento, String cpf) {
		this.id = id;
		this.nome = nome;
		this.Sobrenome = sobrenome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}
	
}
