package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

/**
 * Product entity
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
@Entity
@Table(name = "INVOICE_PRODUCTS")
public class InvoiceProductsEntity {

    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "INVOICE_ID")
    @ManyToOne
    private InvoiceEntity invoice;

    @Column(name = "PRODUCT_ID")
    @ManyToOne
    private ProductEntity product;

    @Column(name = "PRODUCT_COUNT")
    private Integer productCount;

    @Column(name = "PRODUCT_PRICE")
    private Integer productPrice;
}
