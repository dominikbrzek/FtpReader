package com.example.ftpreader.adapter.repository;

import com.example.ftpreader.adapter.entity.InvoiceProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Invoice products entity repository
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
public interface InvoiceProductsEntityRepository extends JpaRepository<InvoiceProductsEntity, UUID> {
}
