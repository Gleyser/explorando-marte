package gleyser.explorandomarte.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.service.MalhaService;

@RestController
@RequestMapping("/api/v1/malhas")
public class MalhaController {
	
	private final MalhaService malhaService;
	
	@Autowired
	public MalhaController() {
		this.malhaService = new MalhaService();
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MalhaDTO cadastrarMalha(@Valid @RequestBody MalhaDTO malhaDTO) {
        return this.malhaService.cadastrarMalha(malhaDTO);       		
        		
    }
	
	@GetMapping
    public MalhaDTO retornaMalha() {
		LocalizacaoDTO l1 = new LocalizacaoDTO(0, 0);
		LocalizacaoDTO l2 = new LocalizacaoDTO(10, 10);		
		MalhaDTO malha = new MalhaDTO();
		malha.setPontoInferiorEsquerdo(l1);
		malha.setPontoSuperiorDireito(l2);
		return malha;
       
        		
        		
    }

}
