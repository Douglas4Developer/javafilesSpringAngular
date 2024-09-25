package com.dev.javafiles.controller;

import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.service.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arquivos")
@Tag(name = "Arquivo", description = "API para operações de arquivos")  // Anotação para descrever o grupo de rotas
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping
    @Operation(summary = "Criar um novo arquivo", description = "Cria um arquivo associado a um diretório existente")  // Anotação para documentar a rota
    public ResponseEntity<Arquivo> criarArquivo(@RequestBody Arquivo arquivo) {
        Arquivo novoArquivo = arquivoService.criarArquivo(arquivo);
        return ResponseEntity.ok(novoArquivo);
    }

    @GetMapping
    @Operation(summary = "Listar todos os arquivos", description = "Retorna uma lista de todos os arquivos cadastrados no sistema")
    public ResponseEntity<List<Arquivo>> listarArquivos() {
        return ResponseEntity.ok(arquivoService.listarArquivos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar arquivo por ID", description = "Retorna um arquivo específico pelo seu ID")
    public ResponseEntity<Arquivo> buscarArquivo(@PathVariable Long id) {
        return arquivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar arquivo", description = "Remove um arquivo específico pelo seu ID")
    public ResponseEntity<Void> deletarArquivo(@PathVariable Long id) {
        arquivoService.deletarArquivo(id);
        return ResponseEntity.noContent().build();
    }
}
