package com.marcos.operadora_cartao_credito.business.mapper;

import com.marcos.operadora_cartao_credito.business.dto.in.ClienteRequestDTO;
import com.marcos.operadora_cartao_credito.business.dto.out.ClienteResponseDTO;
import com.marcos.operadora_cartao_credito.infrastructure.entity.CartaoEntity;
import com.marcos.operadora_cartao_credito.infrastructure.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    //Converte a DTO de Entrada para Entidade e já vincula o cartão inicial com o vencimento da fatura
    @Mapping(target = "cartao", expression = "java(paraCartaoEntity(clienteRequestDTO))")
    ClienteEntity paraClienteEntity(ClienteRequestDTO clienteRequestDTO);

    //Converte a Entidade salva no banco para a DTO de Saída (que devolve o JSON pro usuário)
    ClienteResponseDTO paraClienteResponseDTO(ClienteEntity clienteEntity);

    //Metodo auxiliar que pega a dataVencimentoFatura da DTO de Entrada e coloca na CartaoEntity

    CartaoEntity paraCartaoEntity(ClienteRequestDTO clienteRequestDTO);
}
