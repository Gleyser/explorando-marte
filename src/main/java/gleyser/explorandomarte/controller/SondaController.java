package gleyser.explorandomarte.controller;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.service.SondaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
