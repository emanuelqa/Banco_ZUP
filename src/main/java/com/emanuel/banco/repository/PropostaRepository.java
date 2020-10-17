package com.emanuel.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.banco.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	Proposta findByCpf(String cpf);
	
	Proposta findByEmail(String email);
	
	Proposta findByCpfAndEmail(String cpf, String email);

}
