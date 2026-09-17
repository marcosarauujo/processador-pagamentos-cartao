package com.marcos.operadora_cartao_credito.infrastructure.repository;

import com.marcos.operadora_cartao_credito.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Long> {

    boolean existsByEmail(String email);

    Optional<ClienteEntity> findByCpf(String cpf);
}
