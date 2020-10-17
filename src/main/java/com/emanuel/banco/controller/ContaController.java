package com.emanuel.banco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.banco.controller.dto.EmailCpfDto;
import com.emanuel.banco.controller.dto.SenhaDto;
import com.emanuel.banco.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping("/primeiroAcesso")
	public ResponseEntity<?> primeiroAcesso(@Valid @RequestBody EmailCpfDto dto) {
		return contaService.primeiroAcesso(dto);
	}
	
	@PostMapping("/definirSenha")
	public ResponseEntity<?> definirSenha(@Valid @RequestBody SenhaDto dto) {
		return contaService.definirSenha(dto);
	}

}
