package com.apidavid.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apidavid.app.entity.Producto;


@Repository
public interface ProductRepository extends JpaRepository<Producto, Long>{

}
