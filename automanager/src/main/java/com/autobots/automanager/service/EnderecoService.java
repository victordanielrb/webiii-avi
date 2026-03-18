package com.autobots.automanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepositorio repositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	public Endereco obterEndereco(long id) {
		return repositorio.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
	}

	public List<Endereco> obterEnderecos() {
		return repositorio.findAll();
	}

	public Endereco adicionarEndereco(long clienteId, Endereco endereco) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		cliente.setEndereco(endereco);
		clienteRepositorio.save(cliente);
		return endereco;
	}

	public Endereco atualizarEndereco(Endereco atualizacao) {
		Endereco endereco = obterEndereco(atualizacao.getId());
		EnderecoAtualizador atualizador = new EnderecoAtualizador();
		atualizador.atualizar(endereco, atualizacao);
		return repositorio.save(endereco);
	}

	public void excluirEndereco(long clienteId) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		cliente.setEndereco(null);
		clienteRepositorio.save(cliente);
	}
}
