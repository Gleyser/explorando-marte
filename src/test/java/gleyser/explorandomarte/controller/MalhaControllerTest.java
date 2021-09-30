package gleyser.explorandomarte.controller;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.exception.MalhaNaoEncontradaException;
import gleyser.explorandomarte.service.MalhaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@WebMvcTest(MalhaController.class)
class MalhaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MalhaService malhaService;

    @Autowired
    private JacksonTester<MalhaDTO> json;

    private MalhaDTO malhaDTO;

    @BeforeEach
    public void setUp () {
        malhaDTO = new MalhaDTO();
        malhaDTO.setPontoInferiorEsquerdo(new LocalizacaoDTO(0, 0));
        malhaDTO.setPontoSuperiorDireito(new LocalizacaoDTO(10, 10));

    }
    @Test
    public void cadastraMalha_MalhaOK_Sucesso() throws Exception {

    }

    @Test
    public void cadastraMalha_pontoInferiorEsquerdoNulo_Excecao() throws Exception {
        // Given: promovendo o estado sob teste
        malhaDTO.setPontoInferiorEsquerdo(null);

        // When: acionando a unidade de trabalho ou método
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/malhas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                json.write(malhaDTO).getJson()
                        )
        )
                .andReturn()
                .getResponse();

        // Then: verificando o comportamento desejado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void cadastraMalha_pontoSuperiorDiteiroNulo_Excecao() throws Exception {
        // Given: promovendo o estado sob teste
        malhaDTO.setPontoSuperiorDireito(null);

        // When: acionando a unidade de trabalho ou método
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/malhas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                json.write(malhaDTO).getJson()
                        )
        )
                .andReturn()
                .getResponse();

        // Then: verificando o comportamento desejado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void cadastraMalha_pontoInferiorEsquerdoComCoordenadaNegativa_Excecao() throws Exception {

        // Given: promovendo o estado sob teste
        Integer coordenadaNegativa = -2;
        malhaDTO.setPontoInferiorEsquerdo(new LocalizacaoDTO(coordenadaNegativa, 0));

        // When: acionando a unidade de trabalho ou método
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/malhas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                json.write(malhaDTO).getJson()
                        )
        )
                .andReturn()
                .getResponse();

        // Then: verificando o comportamento desejado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        // Given: promovendo o estado sob teste
        malhaDTO.setPontoInferiorEsquerdo(new LocalizacaoDTO(0, coordenadaNegativa));

        // When: acionando a unidade de trabalho ou método
        response = mvc.perform(
                post("/api/v1/malhas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                json.write(malhaDTO).getJson()
                        )
        )
                .andReturn()
                .getResponse();

        // Then: verificando o comportamento desejado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void cadastraMalha_pontoSuperiorDireitoComCoordenadaNegativa_Excecao() throws Exception {

        // Given: promovendo o estado sob teste
        Integer coordenadaNegativa = -2;
        malhaDTO.setPontoSuperiorDireito(new LocalizacaoDTO(coordenadaNegativa, 0));

        // When: acionando a unidade de trabalho ou método
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/malhas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                json.write(malhaDTO).getJson()
                        )
        )
                .andReturn()
                .getResponse();

        // Then: verificando o comportamento desejado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        // Given: promovendo o estado sob teste
        malhaDTO.setPontoSuperiorDireito(new LocalizacaoDTO(0, coordenadaNegativa));

        // When: acionando a unidade de trabalho ou método
        response = mvc.perform(
                post("/api/v1/malhas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                json.write(malhaDTO).getJson()
                        )
        )
                .andReturn()
                .getResponse();

        // Then: verificando o comportamento desejado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}