package com.autobots.automanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepositorio repositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	public Telefone obterTelefone(long id) {
		return repositorio.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado"));
	}

	public List<Telefone> obterTelefones() {
		return repositorio.findAll();
	}

	public Telefone adicionarTelefone(long clienteId, Telefone telefone) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		cliente.getTelefones().add(telefone);
		clienteRepositorio.save(cliente);
		return telefone;
	}

	public Telefone atualizarTelefone(Telefone atualizacao) {
		Telefone telefone = obterTelefone(atualizacao.getId());
		TelefoneAtualizador atualizador = new TelefoneAtualizador();
		atualizador.atualizar(telefone, atualizacao);
		return repositorio.save(telefone);
	}

	public void excluirTelefone(long clienteId, long id) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		Telefone telefone = obterTelefone(id);
		cliente.getTelefones().remove(telefone);
		clienteRepositorio.save(cliente);
	}
}
