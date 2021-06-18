package com.clienteApp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteApp.models.entity.Ciudad;
import com.clienteApp.models.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService {

	@Autowired
	private CiudadRepository ciudadReposit;

	@Override
	public List<Ciudad> listaCiudades() {
		return (List<Ciudad>) ciudadReposit.findAll();
	}

}
