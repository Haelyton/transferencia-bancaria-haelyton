package com.maicon_projeto_back.desafio_back_end.Services;

import com.maicon_projeto_back.desafio_back_end.entity.ContaBancaria;
import com.maicon_projeto_back.desafio_back_end.repository.RepositoryContaBancaria;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Service

public class ContaBancariaService {

    @Autowired
    RepositoryContaBancaria repContaBancaria;

    public void createContaBancaria(ContaBancaria conta) {

        try {
            if (conta.getData().getYear() < LocalDate.now().getYear()) {
                throw new ValidationException("Deve ser maior de 18 anos.");
            }

            repContaBancaria.saveAndFlush(conta);
        } catch (ValidationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContaBancaria(ContaBancaria conta) {
        try {
            ContaBancaria contaOld = repContaBancaria.getOne(conta.getId());

            if (Objects.isNull(contaOld)) {
                throw new FileNotFoundException("Conta bancária inexistente");
            }

            contaOld.setNome(conta.getNome());
            contaOld.setSaldo(conta.getSaldo());

            repContaBancaria.saveAndFlush(contaOld);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    // buscar - recebe um ID de conta bancaria e retorna o objeto completo, excluindo a senha.
    public ContaBancaria getContaBancariaById(Long id) {
        try {

            ContaBancaria conta = repContaBancaria.getOne(id);

            if (Objects.isNull(conta)) {
                throw new FileNotFoundException("Conta inexistente");

            }

            conta.setSenha(null);
            return conta;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // verificar saldo - Recebe um ID de conta bancaria e um BigDecimal do saldo e verifica se o
    // saldo é maior, igual ou menor que o saldo informado - retornando um boolean true se for maior ou igual
    // e false se for menor.

    public boolean verificarSaldo(Long idContaBancaria, BigDecimal saldoInformado) {

        ContaBancaria conta = getContaBancariaById(idContaBancaria);
        return conta.getSaldo().compareTo(saldoInformado) >= 0;

    }

}











