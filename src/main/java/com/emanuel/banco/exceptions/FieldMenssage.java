package com.emanuel.banco.exceptions;

import java.io.Serializable;

import lombok.Data;

@Data
public class FieldMenssage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String field;
	private String mensagem;

	public FieldMenssage() {
	}

	public FieldMenssage(String field, String mensagem) {
		this.field = field;
		this.mensagem = mensagem;
	}

}
