package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

import static com.example.ftpreader.adapter.service.XMLHelper.getContentFromTag;

/**
 * Product entity
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
@Entity
@NoArgsConstructor
@Table(name = "PRODUCT")
public class ProductEntity implements DomainEntity {

    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String countryCode;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    public ProductEntity(Element element) {
        this.name = getContentFromTag(element, "name");
        this.countryCode = getContentFromTag(element, "countryCode");
        this.price = new BigDecimal(getContentFromTag(element, "price"));
        this.expirationDate = LocalDate.parse(getContentFromTag(element, "expirationDate"));
    }

    public ProductEntity(UUID id) {
        this.id = id;
    }
}
