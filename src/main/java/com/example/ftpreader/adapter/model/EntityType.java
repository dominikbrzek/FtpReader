package com.example.ftpreader.adapter.model;

import com.example.ftpreader.adapter.entity.*;
import lombok.Getter;

/**
 * Entity type
 * <br>
 * <p/>
 * Creation date: 09/08/2023<br>
 *
 * @author dobr
 */
@Getter
public enum EntityType {
    CLIENTS("client", ClientEntity.class),
    PRODUCTS("product", ProductEntity.class),
    INVOICES("invoice", InvoiceEntity.class),
    INVOICE_PRODUCTS("invoiceProduct", InvoiceProductsEntity.class);

    private final String tag;
    private final Class<? extends DomainEntity> type;

    EntityType(String tag, Class<? extends DomainEntity> type) {
        this.tag = tag;
        this.type = type;
    }
}
