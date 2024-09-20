package com.dev.javafiles.controller;

import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.service.DiretorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diretorios")
public class DiretorioController {

    @Autowired
    private DiretorioService diretorioService;

    @PostMapping
    public ResponseEntity<Diretorio> criarDiretorio(@RequestBody Diretorio diretorio) {
        Diretorio novoDiretorio = diretorioService.criarDiretorio(diretorio);
        return ResponseEntity.ok(novoDiretorio);
    }

    @GetMapping
    public ResponseEntity<List<Diretorio>> listarDiretorios() {
        return ResponseEntity.ok(diretorioService.listarDiretorios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretorio> buscarDiretorio(@PathVariable Long id) {
        return diretorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretorio(@PathVariable Long id) {
        diretorioService.deletarDiretorio(id);
        return ResponseEntity.noContent().build();
    }
}
