package com.marcos.operadora_cartao_credito.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cartao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private LocalDate dataExpiracao;
    private String cvv;
    private double limiteCartao;
    private double LimiteCartaoDisponivel;
    private LocalDate ultimaAlteracaoLimite;
    private Integer dataVencimentoFatura;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public CartaoEntity(String numero, LocalDate dataExpiracao, String cvv,
                       double limite, double availableLimit, ClienteEntity cliente,
                       LocalDate ultimaAlteracaoLimite, Integer dataVencimentoFatura) {
        this.numero = numero;
        this.dataExpiracao = dataExpiracao;
        this.cvv = cvv;
        this.limiteCartao = limite;
        this.LimiteCartaoDisponivel = availableLimit;
        this.cliente = cliente;
        this.ultimaAlteracaoLimite = ultimaAlteracaoLimite;
        this.dataVencimentoFatura = dataVencimentoFatura;
    }


}
