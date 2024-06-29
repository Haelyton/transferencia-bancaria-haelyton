package com.maicon_projeto_back.desafio_back_end.repository;

import java.util.Optional;

import org.jrimum.domkee.banco.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface repositoryContaBancaria extends JpaRepository<ContaBancaria, Long> {

    Optional<ContaBancaria> findByCpf(String cpf);
}