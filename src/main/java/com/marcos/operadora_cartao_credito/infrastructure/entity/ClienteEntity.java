package com.marcos.operadora_cartao_credito.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private Integer idade;
    private String cpf;
    private double rendaMensal;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private CartaoEntity cartao;

}
