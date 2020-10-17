package com.emanuel.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.banco.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	Optional<Conta> findByContaAndAgencia(Integer conta, Integer Agencia);

}
