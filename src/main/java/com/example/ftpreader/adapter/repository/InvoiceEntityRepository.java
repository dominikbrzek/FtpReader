package com.example.ftpreader.adapter.repository;

import com.example.ftpreader.adapter.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Invoice entity repository
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, UUID> {
}
