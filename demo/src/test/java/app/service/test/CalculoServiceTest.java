package app.service.test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import app.dto.Entrada;
import app.entity.Calculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.service.CalculoService;

public class CalculoServiceTest {

	private CalculoService calculoService;

	@BeforeEach
	public void setup() {
		calculoService = new CalculoService();
	}

	@Test
	@DisplayName("Cenário 01: soma com valores válidos")
	void cenario01() {
		List<Integer> lista = Arrays.asList(3, 5, 2);
		int resultado = calculoService.soma(lista);
		assertEquals(10, resultado, "A soma dos elementos deve ser 10");
	}

	@Test
	@DisplayName("Cenário 02: soma com valor nulo deve lançar RuntimeException")
	void cenario02() {
		List<Integer> lista = Arrays.asList(3, null, 2);
		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> calculoService.soma(lista));
		assertEquals("dslçfjakd", ex.getMessage());
	}

	@Test
	@DisplayName("Cenário 03: mediana com número par de elementos")
	void cenario03() {
		List<Integer> lista = Arrays.asList(3, 2, 3, 4);
		double mediana = calculoService.mediana(lista);
		assertEquals(3.0, mediana, "Mediana de [2,3,3,4] deve ser 3");
	}

	@Test
	@DisplayName("Cenário 04: mediana com número ímpar de elementos")
	void cenario04() {
		List<Integer> lista = Arrays.asList(3, 2, 1, 9, 4);
		double mediana = calculoService.mediana(lista);
		assertEquals(3.0, mediana, "Mediana de [1,2,3,4,9] deve ser 3");
	}

	@Test
	@DisplayName("Cenário 05: mediana com número par de elementos")
	void cenario05_medianaPar() {
		List<Integer> lista = Arrays.asList(3, 2, 3, 4);
		double mediana = calculoService.mediana(lista);
		assertEquals(3.0, mediana, "Mediana de [2,3,3,4] deve ser 3");
	}

	@Test
	@DisplayName("Cenário 06: mediana com número ímpar de elementos")
	void cenario06_medianaImpar() {
		List<Integer> lista = Arrays.asList(3, 2, 1, 9, 4);
		double mediana = calculoService.mediana(lista);
		assertEquals(3.0, mediana, "Mediana de [1,2,3,4,9] deve ser 3");
	}

	@Test
	@DisplayName("Cenário 07: mediana com lista nula deve lançar IllegalArgumentException")
	void cenario07_medianaNula() {
		assertThrows(IllegalArgumentException.class,
				() -> calculoService.mediana(null), "Lista nula deve lançar IllegalArgumentException");
	}

	@Test
	@DisplayName("Cenário 08: mediana com lista vazia deve lançar IllegalArgumentException")
	void cenario08_medianaVazia() {
		assertThrows(IllegalArgumentException.class,
				() -> calculoService.mediana(Collections.emptyList()),
				"Lista vazia deve lançar IllegalArgumentException");
	}

	@Test
	@DisplayName("Cenário 09: método calcular preenche corretamente o objeto Calculo")
	void cenario09_calcularCompleto() {
		List<Integer> lista = Arrays.asList(1, 3, 5);
		Entrada entrada = new Entrada(lista);
		Calculo calculo = calculoService.calcular(entrada);

		assertNotNull(calculo, "O objeto Calculo retornado não deve ser nulo");
		assertEquals(lista, calculo.getLista(), "A lista deve ser a mesma da entrada");
		assertEquals(9, calculo.getSoma(), "A soma deve ser 9");
		assertEquals(3.0, calculo.getMedia(), "A média deve ser 3.0");
		assertEquals(3.0, calculo.getMediana(), "A mediana deve ser 3.0");
	}
}

