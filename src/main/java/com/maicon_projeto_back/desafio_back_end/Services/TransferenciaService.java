package com.maicon_projeto_back.desafio_back_end.Services;

import com.maicon_projeto_back.desafio_back_end.entity.ContaBancaria;
import com.maicon_projeto_back.desafio_back_end.entity.Transferencia;
import com.maicon_projeto_back.desafio_back_end.repository.RepositoryContaBancaria;
import com.maicon_projeto_back.desafio_back_end.repository.RepositoryTransferencia;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Objects;

public class TransferenciaService {

    @Autowired
     RepositoryContaBancaria repContaBancaria;

    @Autowired
     RepositoryTransferencia repTransferencia;


        public void realizarTransferencia(Long idEmitente, Long idContaRecebimento, BigDecimal valor) throws ValidationException, FileNotFoundException {

            //Verifica se o valor para transferência é maior que zero
            if (Objects.isNull(valor) || valor.compareTo(BigDecimal.ZERO) <= 0) {

                throw new ValidationException("O valor deve ser maior que zero!");

            }

            //Verificar se a conta é uma conta Existente
            ContaBancaria contaEmitente = repContaBancaria.getOne(idEmitente);
            if (Objects.isNull(contaEmitente)) {
                throw new FileNotFoundException("Conta inexistente");

            }

            //Verificar se a conta de recebimento é existente
            ContaBancaria contaRecebimento = repContaBancaria.getOne(idContaRecebimento);
            if (Objects.isNull(contaRecebimento)) {
                throw new FileNotFoundException("Conta de recebimento inexistente");

            }

            //Verifica se o valor da transferência consta com o mesmo valor do saldo
            if (contaEmitente.getSaldo().compareTo(valor) <= 0) {
                throw new ValidationException("Saldo insuficiente!");
            }

            //Subtract contaEmitente - valor da transferência
            contaEmitente.setSaldo(contaEmitente.getSaldo().subtract(valor));

            //add do valor da transferência conta de Recebimento
            contaRecebimento.setSaldo(contaRecebimento.getSaldo().add(valor));


            repContaBancaria.saveAndFlush(contaEmitente);
            repContaBancaria.saveAndFlush(contaRecebimento);


            Transferencia transferencia = new Transferencia();
            transferencia.setContaEmitente(contaEmitente);
            transferencia.setContaRecebimento(contaRecebimento);
            transferencia.setValor(valor);

            repTransferencia.saveAndFlush(transferencia);


        }

}
