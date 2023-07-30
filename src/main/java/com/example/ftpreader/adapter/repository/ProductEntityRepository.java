package com.example.ftpreader.adapter.repository;

import com.example.ftpreader.adapter.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Product entity repository
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID> {
}
