package com.autobots.automanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepositorio repositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	public Documento obterDocumento(long id) {
		return repositorio.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado"));
	}

	public List<Documento> obterDocumentos() {
		return repositorio.findAll();
	}

	public Documento adicionarDocumento(long clienteId, Documento documento) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		cliente.getDocumentos().add(documento);
		clienteRepositorio.save(cliente);
		return documento;
	}

	public Documento atualizarDocumento(Documento atualizacao) {
		Documento documento = obterDocumento(atualizacao.getId());
		DocumentoAtualizador atualizador = new DocumentoAtualizador();
		atualizador.atualizar(documento, atualizacao);
		return repositorio.save(documento);
	}

	public void excluirDocumento(long clienteId, long id) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		Documento documento = obterDocumento(id);
		cliente.getDocumentos().remove(documento);
		clienteRepositorio.save(cliente);
	}
}
