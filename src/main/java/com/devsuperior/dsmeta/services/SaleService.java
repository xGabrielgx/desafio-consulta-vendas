package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.projections.SaleMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class SaleService {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


	LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	LocalDate result = today.minusYears(1L);


	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> findAll(Pageable pageable, String minDate, String maxDate, String name) {

		if(minDate.trim().isEmpty()) {
			minDate = String.valueOf(result);
		}
		if (maxDate.trim().isEmpty()) {
			maxDate = String.valueOf(today);
		}

		LocalDate minLocalDate = LocalDate.parse(minDate, formatter);
		LocalDate maxLocalDate = LocalDate.parse(maxDate, formatter);

		return repository.searchBySale(pageable, minLocalDate, maxLocalDate, name);
	}
}
