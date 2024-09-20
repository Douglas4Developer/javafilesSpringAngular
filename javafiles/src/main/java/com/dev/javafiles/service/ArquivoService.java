package com.dev.javafiles.service;

import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    public Arquivo criarArquivo(Arquivo arquivo) {
        return arquivoRepository.save(arquivo);
    }

    public List<Arquivo> listarArquivos() {
        return arquivoRepository.findAll();
    }

    public Optional<Arquivo> buscarPorId(Long id) {
        return arquivoRepository.findById(id);
    }

    public void deletarArquivo(Long id) {
        arquivoRepository.deleteById(id);
    }
}
