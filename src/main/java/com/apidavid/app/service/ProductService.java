package com.apidavid.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apidavid.app.entity.Producto;


public interface ProductService {

public Iterable<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Optional<Producto> findById(Long id);
	
	public Producto save(Producto user);
	
	public void deleteById(Long id);
}
