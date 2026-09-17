package com.marcos.operadora_cartao_credito.business.dto.out;

import java.time.LocalDate;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        Integer idade,
        double rendaMensal,
        CartaoResponseDTO cartao) {
}
