package com.example.ftpreader.adapter.repository;

import com.example.ftpreader.adapter.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Client entity repository
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {
}
