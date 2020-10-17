package com.emanuel.banco.model;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.banco.controller.dto.TransacaoDto;
import com.emanuel.banco.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class Transferencia {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@PostMapping("/transferir")
	public ResponseEntity<?> transferir(@Valid @RequestBody TransacaoDto dto) {
		return transacaoService.transferir(dto);
	}

}
