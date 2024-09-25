package com.dev.javafiles.controller;

import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.service.DiretorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diretorios")
@Tag(name = "Diretório", description = "API para operações de diretórios")
public class DiretorioController {

    @Autowired
    private DiretorioService diretorioService;

    @PostMapping
    @Operation(summary = "Criar um novo diretório", description = "Cria um novo diretório")
    public ResponseEntity<Diretorio> criarDiretorio(@RequestBody Diretorio diretorio) {
        Diretorio novoDiretorio = diretorioService.criarDiretorio(diretorio);
        return ResponseEntity.ok(novoDiretorio);
    }

    @GetMapping
    @Operation(summary = "Listar todos os diretórios", description = "Retorna uma lista de todos os diretórios cadastrados")
    public ResponseEntity<List<Diretorio>> listarDiretorios() {
        return ResponseEntity.ok(diretorioService.listarDiretorios());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar diretório por ID", description = "Retorna um diretório específico pelo seu ID")
    public ResponseEntity<Diretorio> buscarDiretorio(@PathVariable Long id) {
        return diretorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar diretório", description = "Remove um diretório específico pelo seu ID")
    public ResponseEntity<Void> deletarDiretorio(@PathVariable Long id) {
        diretorioService.deletarDiretorio(id);
        return ResponseEntity.noContent().build();
    }
}
