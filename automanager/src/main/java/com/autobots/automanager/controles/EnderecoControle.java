package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

	@Autowired
	private EnderecoService servico;

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> obterEndereco(@PathVariable long id) {
		return ResponseEntity.ok(servico.obterEndereco(id));
	}

	@GetMapping("/enderecos")
	public ResponseEntity<List<Endereco>> obterEnderecos() {
		return ResponseEntity.ok(servico.obterEnderecos());
	}

	@PostMapping("/cadastro/{clienteId}")
	public ResponseEntity<Endereco> adicionarEndereco(@PathVariable long clienteId, @RequestBody Endereco endereco) {
		Endereco salvo = servico.adicionarEndereco(clienteId, endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Endereco> atualizarEndereco(@RequestBody Endereco atualizacao) {
		return ResponseEntity.ok(servico.atualizarEndereco(atualizacao));
	}

	@DeleteMapping("/excluir/{clienteId}")
	public ResponseEntity<Void> excluirEndereco(@PathVariable long clienteId) {
		servico.excluirEndereco(clienteId);
		return ResponseEntity.noContent().build();
	}
}
