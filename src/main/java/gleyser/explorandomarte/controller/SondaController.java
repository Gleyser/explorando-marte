package gleyser.explorandomarte.controller;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.enums.Acao;
import gleyser.explorandomarte.exception.ColisaoException;
import gleyser.explorandomarte.exception.MalhaNaoEncontradaException;
import gleyser.explorandomarte.exception.SondaNaoEncontradaException;
import gleyser.explorandomarte.service.SondaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sondas")
public class SondaController {

    private final SondaService sondaService;

    @Autowired
    public SondaController(SondaService sondaService) {
        this.sondaService = sondaService;
    }

    @GetMapping
    public List<SondaDTO> retornaSondas() {
        return this.sondaService.retornaSondas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
<<<<<<< HEAD
    public SondaDTO cadastrarSonda(@Valid @RequestBody SondaDTO sondaDTO) throws MalhaNaoEncontradaException, ColisaoException {
=======
    public SondaDTO cadastrarSonda(@Valid @RequestBody SondaDTO sondaDTO) {
>>>>>>> parent of e11945c (Associando sonda com malha)
        return this.sondaService.cadastrarSonda(sondaDTO);
    }

    @GetMapping("/{id}")
    public SondaDTO recuperaSondaPeloId(@PathVariable Long id) throws SondaNaoEncontradaException {
        return this.sondaService.recuperaSondaPeloId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletaSondaPeloId(@PathVariable Long id) throws SondaNaoEncontradaException, MalhaNaoEncontradaException {
        this.sondaService.deletaSondaPeloId(id);
    }

    @PutMapping("/{id}/esquerda")
    public SondaDTO virarSondaParaEsquerda(@PathVariable Long id) throws SondaNaoEncontradaException {
        return this.sondaService.viraSondaParaEsquerda(id);
    }

    @PutMapping("/{id}/direita")
    public SondaDTO virarSondaParaDireita(@PathVariable Long id) throws SondaNaoEncontradaException {
        return this.sondaService.viraSondaParaDireita(id);
    }

    @PutMapping("/{id}/mover")
    public SondaDTO moverASonda(@PathVariable Long id) throws SondaNaoEncontradaException, ColisaoException, MalhaNaoEncontradaException {
        return this.sondaService.moverASonda(id);
    }

    @PutMapping("/{id}/instrucoes")
    public SondaDTO processarInstrucoes(@PathVariable Long id, @RequestBody List<String> instrucoes) throws SondaNaoEncontradaException, ColisaoException, MalhaNaoEncontradaException {
        return this.sondaService.processarInstrucoes(id, instrucoes);
    }




}
