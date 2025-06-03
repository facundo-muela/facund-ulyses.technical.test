package com.septeo.ulyses.technical.test.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.septeo.ulyses.technical.test.entity.Sales;
import com.septeo.ulyses.technical.test.service.SalesService;


@RestController
@RequestMapping("/api/sales")
public class SalesController {

	@Autowired
	private SalesService salesService;

	@GetMapping
	public ResponseEntity<List<Sales>> getAllSales(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int size) {    	
		return ResponseEntity.ok(salesService.getAllSales(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
		return salesService.getSalesById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// TODO: implement here your endpoints
	@GetMapping("/brands/{brandId}")
	public ResponseEntity<List<Sales>> getAllSalesByBrandId(@PathVariable Long brandId) {
		return ResponseEntity.ok(salesService.getAllByBrandId(brandId));
	}

	@GetMapping("/vehicles/{vehicleId}")
	public ResponseEntity<List<Sales>> getAllByVehicleId(@PathVariable Long vehicleId) {
		return ResponseEntity.ok(salesService.getAllByVehicleId(vehicleId));
	}

	@GetMapping("/vehicles/bestSelling")
	public ResponseEntity<List<Sales>> getTopFiveVehicles(@RequestParam(required = false) Date startData, @RequestParam(required = false) Date endDate) {
		return ResponseEntity.ok(salesService.getTopFiveVehicles(startData, endDate));
	}

}
