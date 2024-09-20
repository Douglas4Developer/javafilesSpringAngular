package com.dev.javafiles.controller;

import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping
    public ResponseEntity<Arquivo> criarArquivo(@RequestBody Arquivo arquivo) {
        Arquivo novoArquivo = arquivoService.criarArquivo(arquivo);
        return ResponseEntity.ok(novoArquivo);
    }

    @GetMapping
    public ResponseEntity<List<Arquivo>> listarArquivos() {
        return ResponseEntity.ok(arquivoService.listarArquivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arquivo> buscarArquivo(@PathVariable Long id) {
        return arquivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArquivo(@PathVariable Long id) {
        arquivoService.deletarArquivo(id);
        return ResponseEntity.noContent().build();
    }
}
