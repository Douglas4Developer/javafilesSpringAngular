package com.dev.javafiles.service;

import com.dev.javafiles.model.Arquivo;
import java.util.List;
import java.util.Optional;

public interface ArquivoService {
    Arquivo criarArquivo(Arquivo arquivo);
    List<Arquivo> listarArquivos();
    Optional<Arquivo> buscarPorId(Long id);
    void deletarArquivo(Long id);
}
