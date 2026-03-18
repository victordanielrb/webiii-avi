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

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.service.TelefoneService;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

	@Autowired
	private TelefoneService servico;

	@GetMapping("/{id}")
	public ResponseEntity<Telefone> obterTelefone(@PathVariable long id) {
		return ResponseEntity.ok(servico.obterTelefone(id));
	}

	@GetMapping("/telefones")
	public ResponseEntity<List<Telefone>> obterTelefones() {
		return ResponseEntity.ok(servico.obterTelefones());
	}

	@PostMapping("/cadastro/{clienteId}")
	public ResponseEntity<Telefone> adicionarTelefone(@PathVariable long clienteId, @RequestBody Telefone telefone) {
		Telefone salvo = servico.adicionarTelefone(clienteId, telefone);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Telefone> atualizarTelefone(@RequestBody Telefone atualizacao) {
		return ResponseEntity.ok(servico.atualizarTelefone(atualizacao));
	}

	@DeleteMapping("/excluir/{clienteId}/{id}")
	public ResponseEntity<Void> excluirTelefone(@PathVariable long clienteId, @PathVariable long id) {
		servico.excluirTelefone(clienteId, id);
		return ResponseEntity.noContent().build();
	}
}
