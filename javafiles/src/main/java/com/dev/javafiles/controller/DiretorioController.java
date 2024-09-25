package com.dev.javafiles.controller;

import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.service.DiretorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/diretorios")
@Tag(name = "Diretório", description = "API para operações de diretórios")
@CrossOrigin(origins = "http://localhost:4200")
public class DiretorioController {

    @Autowired
    private DiretorioService diretorioService;

    @PostMapping
    @Operation(
            summary = "Criar um novo diretório",
            description = "Cria um novo diretório",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto Diretório a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n  \"nome\": \"Novo Diretorio\",\n  \"diretorioPai\": { \"id\": 1 }\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Diretório criado com sucesso",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Diretorio.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"id\": 1,\n  \"nome\": \"Novo Diretorio\",\n  \"diretorioPai\": { \"id\": 1, \"nome\": \"DiretorioPai\" }\n}"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Falha ao criar o diretório", responseCode = "400")
            }
    )
    public ResponseEntity<Diretorio> criarDiretorio(@RequestBody Diretorio diretorio) {
        Diretorio novoDiretorio = diretorioService.criarDiretorio(diretorio);
        return ResponseEntity.ok(novoDiretorio);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os diretórios",
            description = "Retorna uma lista de todos os diretórios cadastrados",
            responses = @ApiResponse(
                    description = "Lista de diretórios",
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Diretorio.class),
                            examples = @ExampleObject(
                                    value = "[\n  {\"id\": 1, \"nome\": \"Diretorio1\", \"subdiretorios\": [{ \"id\": 2, \"nome\": \"Subdiretorio1\" }], \"arquivos\": []},\n  {\"id\": 3, \"nome\": \"Diretorio2\", \"subdiretorios\": [], \"arquivos\": []}\n]"
                            )
                    )
            )
    )
    public ResponseEntity<List<Diretorio>> listarDiretorios() {
        List<Diretorio> todosDiretorios = diretorioService.listarDiretorios();
        List<Diretorio> diretoriosRaiz = todosDiretorios.stream()
                .filter(diretorio -> diretorio.getDiretorioPai() == null)
                .collect(Collectors.toList());
        return ResponseEntity.ok(diretoriosRaiz);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar diretório por ID",
            description = "Retorna um diretório específico pelo seu ID",
            responses = {
                    @ApiResponse(
                            description = "Diretório encontrado",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Diretorio.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"id\": 1,\n  \"nome\": \"Diretorio1\",\n  \"subdiretorios\": [{ \"id\": 2, \"nome\": \"Subdiretorio1\" }], \"arquivos\": []\n}"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Diretório não encontrado", responseCode = "404")
            }
    )
    public ResponseEntity<Diretorio> buscarDiretorio(@PathVariable Long id) {
        return diretorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar diretório",
            description = "Remove um diretório específico pelo seu ID",
            responses = {
                    @ApiResponse(description = "Diretório deletado com sucesso", responseCode = "204"),
                    @ApiResponse(description = "Diretório não encontrado", responseCode = "404")
            }
    )
    public ResponseEntity<Void> deletarDiretorio(@PathVariable Long id) {
        diretorioService.deletarDiretorio(id);
        return ResponseEntity.noContent().build();
    }
}
