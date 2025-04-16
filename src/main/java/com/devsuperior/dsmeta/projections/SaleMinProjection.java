package com.devsuperior.dsmeta.projections;

import com.devsuperior.dsmeta.entities.Seller;

import java.time.LocalDate;

public interface SaleMinProjection {

    Long getId();
    Double getAmount();
    LocalDate getDate();
    String getSellerName();
}
