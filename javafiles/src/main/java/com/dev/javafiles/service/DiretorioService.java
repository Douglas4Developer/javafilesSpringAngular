package com.dev.javafiles.service;

import com.dev.javafiles.model.Diretorio;

import java.util.List;
import java.util.Optional;

public interface DiretorioService {
    Diretorio criarDiretorio(Diretorio diretorio);
    List<Diretorio> listarDiretorios();
    Optional<Diretorio> buscarPorId(Long id);
    void deletarDiretorio(Long id);
}
