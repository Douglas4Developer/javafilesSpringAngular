package com.dev.javafiles.service;

import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.repository.DiretorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiretorioService {

    @Autowired
    private DiretorioRepository diretorioRepository;

    public Diretorio criarDiretorio(Diretorio diretorio) {
        return diretorioRepository.save(diretorio);
    }

    public List<Diretorio> listarDiretorios() {
        return diretorioRepository.findAll();
    }

    public Optional<Diretorio> buscarPorId(Long id) {
        return diretorioRepository.findById(id);
    }

    public void deletarDiretorio(Long id) {
        diretorioRepository.deleteById(id);
    }
}
