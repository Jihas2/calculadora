package app.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import app.dto.Entrada;
import app.entity.Calculo;
import app.service.CalculoService;
import app.controller.CalculoController;

@WebMvcTest(CalculoController.class)
public class CalculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculoService calculoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalcular_Sucesso200() throws Exception {
        List<Integer> lista = Arrays.asList(1, 2, 3);
        Entrada entrada = new Entrada(lista);

        Calculo resposta = new Calculo();
        resposta.setId(1L);
        resposta.setLista(lista);
        resposta.setSoma(6);
        resposta.setMedia(2.0);
        resposta.setMediana(2.0);

        when(calculoService.calcular(any(Entrada.class))).thenReturn(resposta);

        mockMvc.perform(post("/api/calculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.soma").value(6))
                .andExpect(jsonPath("$.media").value(2.0))
                .andExpect(jsonPath("$.mediana").value(2.0));
    }
//teste
    @Test
    public void testCalcular_Erro400() throws Exception {
        List<Integer> lista = Arrays.asList(1, 2);
        Entrada entrada = new Entrada(lista);
        when(calculoService.calcular(any(Entrada.class)))
                .thenThrow(new RuntimeException("Erro de teste"));

        mockMvc.perform(post("/api/calculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro de teste"));
    }
}