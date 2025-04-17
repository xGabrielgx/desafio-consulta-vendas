package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport( Pageable pageable,
			@RequestParam (value = "minDate", defaultValue = "") String minDate,
			@RequestParam (value = "maxDate", defaultValue = "")String maxDate,
			@RequestParam (value = "name", defaultValue = "")String name
	) {

		Page<SaleMinDTO> list = service.findBySale(pageable, minDate, maxDate,name);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSummaryMinDTO>> getSummary(
			Pageable pageable,
			@RequestParam (value = "minDate", defaultValue = "") String minDate,
			@RequestParam (value = "maxDate", defaultValue = "") String maxDate
	) {
		Page<SaleSummaryMinDTO> list = service.findBySaleSummary(pageable, minDate,  maxDate);
		return ResponseEntity.ok(list);
	}
}
