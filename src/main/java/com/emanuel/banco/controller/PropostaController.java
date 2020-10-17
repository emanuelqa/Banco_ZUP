package com.emanuel.banco.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emanuel.banco.controller.dto.PropostaDTO;
import com.emanuel.banco.model.Proposta;
import com.emanuel.banco.service.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
	
	@Autowired
	private PropostaService propostaService;
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody PropostaDTO propostaDTO){
		Proposta propostaSalva = propostaService.inserir(propostaService.fromDTO(propostaDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(propostaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<PropostaDTO> buscarPorCpf(@PathVariable String cpf){
		PropostaDTO propostaDTO = propostaService.buscarPorCpf(cpf);
		return ResponseEntity.ok().body(propostaDTO);
	}
	
	@PostMapping("/aceita/{cpf}&{aceita}")
	public ResponseEntity<?> aceitaProposta(@PathVariable String cpf, @PathVariable String aceita){
		propostaService.aceita(cpf, aceita);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/aprovar/{cpf}&{aprovar}")
	public ResponseEntity<?> aprovarProposta(@PathVariable String cpf, @PathVariable String aprovar){
		propostaService.aprovar(cpf, aprovar);
		return ResponseEntity.ok(null);
	}

}
