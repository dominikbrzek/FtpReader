package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.ftpreader.adapter.service.XMLHelper.getContentFromTag;

/**
 * Invoice entity
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
@Entity
@NoArgsConstructor
@Table(name = "INVOICE")
public class InvoiceEntity implements DomainEntity {

    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "SUMMARY_PRICE")
    private BigDecimal summaryPrice;

    public InvoiceEntity(Element element) {
        this.client = new ClientEntity(UUID.fromString(getContentFromTag(element, "clientId")));
        this.creationDate = LocalDateTime.parse(getContentFromTag(element, "creationDate"));
        this.summaryPrice = new BigDecimal(getContentFromTag(element, "summaryPrice"));
    }

    public InvoiceEntity(UUID id) {
        this.id = id;
    }
}
