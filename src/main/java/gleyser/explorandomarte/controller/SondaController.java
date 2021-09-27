package gleyser.explorandomarte.controller;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.dto.SondaDTO;
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
    public SondaDTO cadastrarSonda(@Valid @RequestBody SondaDTO sondaDTO) {
        return this.sondaService.cadastrarSonda(sondaDTO);
    }

    @GetMapping("/{id}")
    public SondaDTO recuperaSondaPeloId(@PathVariable Long id) throws SondaNaoEncontradaException {
        return this.sondaService.recuperaMalhaPeloId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletaSondaPeloId(@PathVariable Long id) throws SondaNaoEncontradaException {
        this.sondaService.deletaMalhaPeloId(id);
    }

    @PutMapping("/{id}")
    public SondaDTO atualizaMalhaPeloId(@PathVariable Long id, @Valid @RequestBody SondaDTO sondaDTO) throws SondaNaoEncontradaException {
        return this.sondaService.atualizaSondaPeloId(id, sondaDTO);
    }

    @PutMapping("/{id}/paraesquerda")
    public SondaDTO viraSondaParaEsquerda(@PathVariable Long id) throws SondaNaoEncontradaException {
        return this.sondaService.viraSondaParaEsquerda(id);
    }

    @PutMapping("/{id}/paradireita")
    public SondaDTO viraSondaParaDireita(@PathVariable Long id) throws SondaNaoEncontradaException {
        return this.sondaService.viraSondaParaDireita(id);
    }


}
