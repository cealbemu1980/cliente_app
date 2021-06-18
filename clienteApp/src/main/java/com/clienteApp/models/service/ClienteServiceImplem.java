package com.clienteApp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteApp.models.entity.Cliente;
import com.clienteApp.models.repository.ClienteRepository;

@Service
public class ClienteServiceImplem implements IClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarCliente() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		clienteRepository.save(cliente);

	}

	@Override
	public Cliente buscarPorId(Long id) {
		// orElse  para que retorne un mensaje null
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		clienteRepository.deleteById(id);

	}

}
