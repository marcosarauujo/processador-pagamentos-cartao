package com.marcos.operadora_cartao_credito.infrastructure.repository;

import com.marcos.operadora_cartao_credito.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Long> {

}
