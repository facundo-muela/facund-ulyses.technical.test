package com.septeo.ulyses.technical.test.controller;

import com.septeo.ulyses.technical.test.entity.Brand;
import com.septeo.ulyses.technical.test.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Brand operations.
 */
@RestController
@RequestMapping("/api/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping
	public ResponseEntity<List<Brand>> getAllBrands() {
		return ResponseEntity.ok(brandService.getAllBrands());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
		return brandService.getBrandById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		Brand savedBrand = brandService.saveBrand(brand);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
		return brandService.getBrandById(id)
				.map(existingBrand -> {
					brand.setId(id);
					return ResponseEntity.ok(brandService.saveBrand(brand));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
		if (!brandService.getBrandById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		brandService.deleteBrand(id);
		return ResponseEntity.noContent().build();
	}
}
