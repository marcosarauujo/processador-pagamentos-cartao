package com.marcos.operadora_cartao_credito.business.dto.in;

import com.marcos.operadora_cartao_credito.infrastructure.entity.EnderecoEntity;


public record ClienteRequestDTO(
        String nome,
        String email,
        Integer idade,
        String cpf,
        double rendaMensal,
        EnderecoEntity endereco) {

}


