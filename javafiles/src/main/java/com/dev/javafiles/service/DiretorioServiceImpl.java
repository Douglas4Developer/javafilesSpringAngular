package com.dev.javafiles.service.impl;

import com.dev.javafiles.exception.ResourceNotFoundException;
import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.repository.DiretorioRepository;
import com.dev.javafiles.service.DiretorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiretorioServiceImpl implements DiretorioService {

    @Autowired
    private DiretorioRepository diretorioRepository;

    @Override
    public Diretorio criarDiretorio(Diretorio diretorio) {
        // Adicionando validações se necessário
        return diretorioRepository.save(diretorio);
    }

    @Override
    public List<Diretorio> listarDiretorios() {
        return diretorioRepository.findAll();
    }

    @Override
    public Optional<Diretorio> buscarPorId(Long id) {
        // Lançando exceção personalizada se o diretório não for encontrado
        return Optional.ofNullable(diretorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diretório não encontrado com o ID: " + id)));
    }

    @Override
    public void deletarDiretorio(Long id) {
        // Verifica se o diretório existe antes de deletar, se não, lança exceção
        if (!diretorioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Diretório não encontrado com o ID: " + id);
        }
        diretorioRepository.deleteById(id);
    }
}
