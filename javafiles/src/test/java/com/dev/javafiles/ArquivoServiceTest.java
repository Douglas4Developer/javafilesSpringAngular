package com.dev.javafiles;

import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.repository.ArquivoRepository;
import com.dev.javafiles.service.ArquivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArquivoServiceTest {

    @InjectMocks
    private ArquivoService arquivoService;

    @Mock
    private ArquivoRepository arquivoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarArquivo() {
        Diretorio diretorio = new Diretorio(1L, "Diretorio Teste", null, null, null);
        Arquivo arquivo = new Arquivo(null, "file1.txt", diretorio);
        when(arquivoRepository.save(arquivo)).thenReturn(new Arquivo(1L, "file1.txt", diretorio));

        Arquivo created = arquivoService.criarArquivo(arquivo);

        assertNotNull(created.getId());
        assertEquals("file1.txt", created.getNome());
    }

    @Test
    void testListarArquivos() {
        Diretorio diretorio = new Diretorio(1L, "Diretorio Teste", null, null, null);
        when(arquivoRepository.findAll()).thenReturn(Arrays.asList(
                new Arquivo(1L, "file1.txt", diretorio),
                new Arquivo(2L, "file2.txt", diretorio)
        ));

        assertEquals(2, arquivoService.listarArquivos().size());
    }

    @Test
    void testBuscarPorId() {
        Diretorio diretorio = new Diretorio(1L, "Diretorio Teste", null, null, null);
        when(arquivoRepository.findById(1L)).thenReturn(Optional.of(new Arquivo(1L, "file1.txt", diretorio)));

        Optional<Arquivo> result = arquivoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("file1.txt", result.get().getNome());
    }

    @Test
    void testDeletarArquivo() {
        arquivoService.deletarArquivo(1L);
        verify(arquivoRepository, times(1)).deleteById(1L);
    }
}
