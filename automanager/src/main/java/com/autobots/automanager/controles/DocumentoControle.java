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

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.service.DocumentoService;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

	@Autowired
	private DocumentoService servico;

	@GetMapping("/{id}")
	public ResponseEntity<Documento> obterDocumento(@PathVariable long id) {
		return ResponseEntity.ok(servico.obterDocumento(id));
	}

	@GetMapping("/documentos")
	public ResponseEntity<List<Documento>> obterDocumentos() {
		return ResponseEntity.ok(servico.obterDocumentos());
	}

	@PostMapping("/cadastro/{clienteId}")
	public ResponseEntity<Documento> adicionarDocumento(@PathVariable long clienteId, @RequestBody Documento documento) {
		Documento salvo = servico.adicionarDocumento(clienteId, documento);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Documento> atualizarDocumento(@RequestBody Documento atualizacao) {
		return ResponseEntity.ok(servico.atualizarDocumento(atualizacao));
	}

	@DeleteMapping("/excluir/{clienteId}/{id}")
	public ResponseEntity<Void> excluirDocumento(@PathVariable long clienteId, @PathVariable long id) {
		servico.excluirDocumento(clienteId, id);
		return ResponseEntity.noContent().build();
	}
}
