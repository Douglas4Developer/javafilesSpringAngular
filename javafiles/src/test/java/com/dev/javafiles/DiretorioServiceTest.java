package com.dev.javafiles.service;

import com.dev.javafiles.model.Diretorio;
import com.dev.javafiles.repository.DiretorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiretorioServiceTest {

	@InjectMocks
	private DiretorioService diretorioService;

	@Mock
	private DiretorioRepository diretorioRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCriarDiretorio() {
		Diretorio diretorio = new Diretorio(null, "Diretorio Teste", null, null, null);
		when(diretorioRepository.save(diretorio)).thenReturn(new Diretorio(1L, "Diretorio Teste", null, null, null));

		Diretorio created = diretorioService.criarDiretorio(diretorio);

		assertNotNull(created.getId());
		assertEquals("Diretorio Teste", created.getNome());
	}

	@Test
	void testListarDiretorios() {
		when(diretorioRepository.findAll()).thenReturn(Arrays.asList(
				new Diretorio(1L, "Diretorio1", null, null, null),
				new Diretorio(2L, "Diretorio2", null, null, null)
		));

		assertEquals(2, diretorioService.listarDiretorios().size());
	}

	@Test
	void testBuscarPorId() {
		when(diretorioRepository.findById(1L)).thenReturn(Optional.of(new Diretorio(1L, "Diretorio1", null, null, null)));

		Optional<Diretorio> result = diretorioService.buscarPorId(1L);

		assertTrue(result.isPresent());
		assertEquals("Diretorio1", result.get().getNome());
	}

	@Test
	void testDeletarDiretorio() {
		diretorioService.deletarDiretorio(1L);
		verify(diretorioRepository, times(1)).deleteById(1L);
	}
}
