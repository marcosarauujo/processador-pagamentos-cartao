package com.marcos.operadora_cartao_credito.business;

import com.marcos.operadora_cartao_credito.infrastructure.entity.CartaoEntity;
import com.marcos.operadora_cartao_credito.infrastructure.entity.ClienteEntity;
import com.marcos.operadora_cartao_credito.infrastructure.exception.ConflictException;
import com.marcos.operadora_cartao_credito.infrastructure.exception.ResourceNotFoundException;
import com.marcos.operadora_cartao_credito.infrastructure.repository.ClienteRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepositoryJpa clienteRepository;
    private final GeraDadosCartaoService geraDadosCartao;

    public ClienteEntity solicitarCartao(ClienteEntity clienteEntity) {
        try {
            if (clienteRepository.existsByEmail(clienteEntity.getEmail())) { //Validação de e-mail
                throw new ConflictException("Usuário já possui um cartão cadastrado com este e-mail.");
            }
            if (clienteRepository.existsByCpf(clienteEntity.getCpf())) { // Validação de CPF
                throw new ConflictException("Já existe um cliente cadastrado com este CPF.");
            }
            CartaoEntity cartao = geraDadosCartao.gerarCartaoParaCliente(clienteEntity);
            clienteEntity.setCartao(cartao);
            return clienteRepository.save(clienteEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Conflito ao salvar os dados do cliente no banco de dados.", e.getCause()
            );
        }
    }


    public ClienteEntity buscaClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(()
                -> new ResourceNotFoundException("Cliente não encontrado para o CPF informado: " + cpf)
        );
    }
}
