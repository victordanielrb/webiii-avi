package com.autobots.automanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.ClienteAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepositorio repositorio;

	public Cliente obterCliente(long id) {
		return repositorio.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	public List<Cliente> obterClientes() {
		return repositorio.findAll();
	}

	public Cliente cadastrarCliente(Cliente cliente) {
		return repositorio.save(cliente);
	}

	public Cliente atualizarCliente(Cliente atualizacao) {
		Cliente cliente = obterCliente(atualizacao.getId());
		ClienteAtualizador atualizador = new ClienteAtualizador();
		atualizador.atualizar(cliente, atualizacao);
		return repositorio.save(cliente);
	}

	public void excluirCliente(long id) {
		Cliente cliente = obterCliente(id);
		repositorio.delete(cliente);
	}
}
