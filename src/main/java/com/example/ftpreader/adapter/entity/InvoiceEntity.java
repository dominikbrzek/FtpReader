package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigInteger;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Invoice entity
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
@Entity
@Table(name = "INVOICES")
public class InvoiceEntity {

    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "CLIENT_ID")
    @ManyToOne
    private ClientEntity client;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "SUMMARY_PRICE")
    private BigInteger summaryPrice;

}
