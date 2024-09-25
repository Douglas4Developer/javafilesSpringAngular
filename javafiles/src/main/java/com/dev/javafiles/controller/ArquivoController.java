package com.dev.javafiles.controller;

import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.service.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arquivos")
@Tag(name = "Arquivo", description = "API para operações de arquivos")
@CrossOrigin(origins = "http://localhost:4200")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping
    @Operation(
            summary = "Criar um novo arquivo",
            description = "Cria um arquivo associado a um diretório existente",
            requestBody = @RequestBody(
                    description = "Objeto Arquivo a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n  \"nome\": \"file1.txt\",\n  \"diretorio\": { \"id\": 1 }\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Arquivo criado com sucesso",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Arquivo.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"id\": 1,\n  \"nome\": \"file1.txt\",\n  \"diretorio\": { \"id\": 1, \"nome\": \"Diretorio1\" }\n}"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Falha ao criar o arquivo", responseCode = "400")
            }
    )
    public ResponseEntity<Arquivo> criarArquivo(@RequestBody Arquivo arquivo) {
        Arquivo novoArquivo = arquivoService.criarArquivo(arquivo);
        return ResponseEntity.ok(novoArquivo);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os arquivos",
            description = "Retorna uma lista de todos os arquivos cadastrados no sistema",
            responses = @ApiResponse(
                    description = "Lista de arquivos",
                    responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Arquivo.class),
                            examples = @ExampleObject(
                                    value = "[\n  {\"id\": 1, \"nome\": \"file1.txt\", \"diretorio\": {\"id\": 1, \"nome\": \"Diretorio1\"}},\n  {\"id\": 2, \"nome\": \"file2.jpg\", \"diretorio\": {\"id\": 2, \"nome\": \"Diretorio2\"}}\n]"
                            )
                    )
            )
    )
    public ResponseEntity<List<Arquivo>> listarArquivos() {
        return ResponseEntity.ok(arquivoService.listarArquivos());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar arquivo por ID",
            description = "Retorna um arquivo específico pelo seu ID",
            responses = {
                    @ApiResponse(
                            description = "Arquivo encontrado",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Arquivo.class),
                                    examples = @ExampleObject(
                                            value = "{\n  \"id\": 1,\n  \"nome\": \"file1.txt\",\n  \"diretorio\": { \"id\": 1, \"nome\": \"Diretorio1\" }\n}"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Arquivo não encontrado", responseCode = "404")
            }
    )
    public ResponseEntity<Arquivo> buscarArquivo(@PathVariable Long id) {
        return arquivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar arquivo",
            description = "Remove um arquivo específico pelo seu ID",
            responses = {
                    @ApiResponse(description = "Arquivo deletado com sucesso", responseCode = "204"),
                    @ApiResponse(description = "Arquivo não encontrado", responseCode = "404")
            }
    )
    public ResponseEntity<Void> deletarArquivo(@PathVariable Long id) {
        arquivoService.deletarArquivo(id);
        return ResponseEntity.noContent().build();
    }
}
