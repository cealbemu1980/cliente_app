package com.clienteApp.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clienteApp.models.entity.Ciudad;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
