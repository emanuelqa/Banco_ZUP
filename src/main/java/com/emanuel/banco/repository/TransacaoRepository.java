package com.emanuel.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.banco.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
