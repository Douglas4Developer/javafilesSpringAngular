package com.dev.javafiles;

import com.dev.javafiles.exception.ResourceNotFoundException;
import com.dev.javafiles.model.Arquivo;
import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.repository.ArquivoRepository;
import com.dev.javafiles.repository.DiretorioRepository;
import com.dev.javafiles.service.impl.ArquivoServiceImpl;
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
    private ArquivoServiceImpl arquivoService;

    @Mock
    private ArquivoRepository arquivoRepository;

    @Mock
    private DiretorioRepository diretorioRepository; // Adicionado mock para DiretorioRepository

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarArquivo() {
        // Simulando a busca do diretório pelo id no repositório
        Diretorio diretorio = new Diretorio(1L, "Diretorio Teste", null, null, null);
        when(diretorioRepository.findById(1L)).thenReturn(Optional.of(diretorio));

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
    void testBuscarPorId_NotFound() {
        when(arquivoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            arquivoService.buscarPorId(1L);
        });

        String expectedMessage = "Arquivo não encontrado com o ID: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeletarArquivo() {
        when(arquivoRepository.existsById(1L)).thenReturn(true);

        arquivoService.deletarArquivo(1L);
        verify(arquivoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarArquivo_NotFound() {
        when(arquivoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            arquivoService.deletarArquivo(1L);
        });

        String expectedMessage = "Arquivo não encontrado com o ID: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
