package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


//    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.amount, tb_sales.date, tb_seller.name " +
//            "FROM tb_sales INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
//            "WHERE tb_sales.date BETWEEN '2022-05-01' AND '2022-05-31' AND tb_seller.name LIKE '%Odinson'"
//    )

    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name))",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller")
    Page<SaleMinDTO> searchBySale(Pageable pageable, LocalDate minDate, LocalDate maxDate, String name);

}
