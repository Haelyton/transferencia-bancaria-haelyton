package com.maicon_projeto_back.desafio_back_end.repository;

import com.maicon_projeto_back.desafio_back_end.entity.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositoryContaBancaria extends JpaRepository<ContaBancaria, Long> {

    // Remover - não é necessário
    Optional<ContaBancaria> findByCpf(String cpf);

}