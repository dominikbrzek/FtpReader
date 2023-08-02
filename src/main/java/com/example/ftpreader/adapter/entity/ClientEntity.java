package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.w3c.dom.Element;

import java.sql.Types;
import java.util.UUID;

import static com.example.ftpreader.adapter.service.XMLHelper.getContentFromTag;

/**
 * Client entity
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
@Entity
@NoArgsConstructor
@Table(name = "CLIENT")
public class ClientEntity implements DomainEntity {
    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE")
    private Integer phone;

    public ClientEntity(Element element) {
        this.name = getContentFromTag(element, "name");
        this.surname = getContentFromTag(element, "surname");
        this.phone = Integer.parseInt(getContentFromTag(element, "phone"));
    }

    public ClientEntity(UUID id) {
        this.id = id;
    }

}
