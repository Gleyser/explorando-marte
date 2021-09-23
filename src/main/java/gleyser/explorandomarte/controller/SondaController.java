package gleyser.explorandomarte.controller;

import gleyser.explorandomarte.dto.SondaDTO;
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
}
