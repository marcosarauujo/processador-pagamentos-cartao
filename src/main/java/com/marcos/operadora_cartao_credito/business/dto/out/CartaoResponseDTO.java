package com.marcos.operadora_cartao_credito.business.dto.out;

import java.time.LocalDate;

public record CartaoResponseDTO(
        String numero,
        LocalDate dataExpiracao,
        String cvv,
        double limiteCartao,
        double limiteCartaoDisponivel,
        Integer dataVencimentoFatura) {
}
