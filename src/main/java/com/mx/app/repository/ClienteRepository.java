package com.mx.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.app.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
	Cliente findByNombre(String nombre);

}
