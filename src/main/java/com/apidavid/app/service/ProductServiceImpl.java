package com.apidavid.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apidavid.app.entity.Producto;
import com.apidavid.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findAll() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	@Transactional
	public Producto save(Producto product) {
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteById(Long codigo) {
		productRepository.deleteById(codigo);
		
	}

}
