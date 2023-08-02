package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

import static com.example.ftpreader.adapter.service.XMLHelper.getContentFromTag;

/**
 * Invoice products entity
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
@Entity
@NoArgsConstructor
@Table(name = "INVOICE_PRODUCT")
public class InvoiceProductsEntity implements DomainEntity {

    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private InvoiceEntity invoice;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @Column(name = "PRODUCT_COUNT")
    private Integer productsCount;

    @Column(name = "PRODUCT_PRICE")
    private BigDecimal productPrice;

    public InvoiceProductsEntity(Element element) {
        this.invoice = new InvoiceEntity(UUID.fromString(getContentFromTag(element, "invoiceId")));
        this.product = new ProductEntity(UUID.fromString(getContentFromTag(element, "productId")));
        this.productsCount = Integer.parseInt(getContentFromTag(element, "productsCount"));
        this.productPrice = new BigDecimal(getContentFromTag(element, "productPrice"));
    }
}
