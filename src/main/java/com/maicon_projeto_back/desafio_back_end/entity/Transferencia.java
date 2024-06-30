package com.maicon_projeto_back.desafio_back_end.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Como aqui é uma conexão entre tabelas, não utiliza o @Column, se utiliza o @JoinColumn
    // vou deixar o contaEmitente como exemplo e você faz no recebimento
    // o OneToOne é para dizer que é uma relação de um para um
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaEmitente")
    private ContaBancaria contaEmitente;

    @Column(name = "contaRecebimento")
    private ContaBancaria contaRecebimento;

    @Column(name = "BigDecimal")
    private BigDecimal valor = BigDecimal.ZERO;
}
