package com.dev.javafiles.service.impl;

import com.dev.javafiles.exception.ResourceNotFoundException;
import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.repository.ArquivoRepository;
import com.dev.javafiles.repository.DiretorioRepository;
import com.dev.javafiles.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArquivoServiceImpl implements ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private DiretorioRepository diretorioRepository;

    @Override

    public Arquivo criarArquivo(Arquivo arquivo) {
        // Verifique se o diretório existe no banco de dados
        Diretorio diretorio = diretorioRepository.findById(arquivo.getDiretorio().getId())
                .orElseThrow(() -> new IllegalArgumentException("Diretório inválido ou não informado."));

        // Associe o diretório ao arquivo
        arquivo.setDiretorio(diretorio);

        // Agora salve o arquivo no banco de dados
        return arquivoRepository.save(arquivo);
    }



    @Override
    public List<Arquivo> listarArquivos() {
        return arquivoRepository.findAll();
    }

    @Override
    public Optional<Arquivo> buscarPorId(Long id) {
        // Lança exceção personalizada caso o arquivo não seja encontrado
        return Optional.ofNullable(arquivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arquivo não encontrado com o ID: " + id)));
    }

    @Override
    public void deletarArquivo(Long id) {
        // Verifica se o arquivo existe antes de deletar, caso contrário lança exceção
        if (!arquivoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Arquivo não encontrado com o ID: " + id);
        }
        arquivoRepository.deleteById(id);
    }
}
