package app.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

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
}
