package com.apidavid.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apidavid.app.entity.Producto;
import com.apidavid.app.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// CREATE A NEW PRODUCT
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Producto product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	// READ AN PRODUCT
	@GetMapping("/{codigo}")
	public ResponseEntity<?> read(@PathVariable(value = "codigo") Long productCodigo) {
		Optional<Producto> oProduct = productService.findById(productCodigo);

		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oProduct);
	}

	// UPDATE AN PRODUCT
	@PutMapping("/{codigo}")
	public ResponseEntity<?> update(@RequestBody Producto productDetails,
			@PathVariable(value = "codigo") Long productCodigo) {
		Optional<Producto> pro = productService.findById(productCodigo);
		if (!pro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		pro.get().setDescripcion(productDetails.getDescripcion());
		pro.get().setPrecio(productDetails.getPrecio());
		pro.get().setCantidad(productDetails.getCantidad());

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(pro.get()));
	}

	// DELETE AN PRODUCT
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long productCodigo) {
		if (!productService.findById(productCodigo).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		productService.deleteById(productCodigo);
		return ResponseEntity.ok().build();
	}

	// READ ALL PRODUCTS
	@GetMapping
	public List<Producto> readAll() {
		List<Producto> pro = StreamSupport.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return pro;
	}
}
