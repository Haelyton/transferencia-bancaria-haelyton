package com.maicon_projeto_back.desafio_back_end.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaBancaria {
    private Integer id;
    private String nome;
    private String cpf;
    private String senha;
    private LocalDate data;
    private BigDecimal saldo = BigDecimal.ZERO;
    

}
