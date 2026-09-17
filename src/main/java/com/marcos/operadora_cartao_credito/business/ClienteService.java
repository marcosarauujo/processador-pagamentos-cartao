package com.marcos.operadora_cartao_credito.business;

import com.marcos.operadora_cartao_credito.infrastructure.entity.CartaoEntity;
import com.marcos.operadora_cartao_credito.infrastructure.entity.ClienteEntity;
import com.marcos.operadora_cartao_credito.infrastructure.repository.ClienteRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepositoryJpa clienteRepository;
    private final GeraDadosCartaoService geraDadosCartao;

    public ClienteEntity solicitarCartao(ClienteEntity clienteEntity) {
        if (clienteRepository.existsByEmail(clienteEntity.getEmail())) {
            throw new IllegalArgumentException("Usuário ja possui cartão.");
        }
        CartaoEntity cartao = geraDadosCartao.gerarCartaoParaCliente(clienteEntity);
        clienteEntity.setCartao(cartao);
        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity buscaClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(()
                -> new IllegalArgumentException("Cliente não encontrado.")
        );
    }


}
