package com.maicon_projeto_back.desafio_back_end.resources;

import com.maicon_projeto_back.desafio_back_end.Services.ContaBancariaService;
import com.maicon_projeto_back.desafio_back_end.entity.ContaBancaria;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaBancariaResource {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping()
    public String createContaBancaria(ContaBancaria conta) {

        try {
            contaBancariaService.createContaBancaria(conta);
            return "Conta Bancária criada com sucesso!";
        }catch (Exception e){
            return e.getMessage();
        }

    }

}
