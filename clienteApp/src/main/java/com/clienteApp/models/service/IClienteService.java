package com.clienteApp.models.service;

import java.util.List;

import com.clienteApp.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> listarCliente();
	public void guardar(Cliente cliente);
	public Cliente buscarPorId(Long id);
	public void eliminar(Long id);

}
