package gleyser.explorandomarte.controller;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.service.MalhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/malhas")
public class MalhaController {
	
	private final MalhaService malhaService;
	
	@Autowired
	public MalhaController(MalhaService malhaService) {		
		this.malhaService = malhaService;
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MalhaDTO cadastrarMalha(@Valid @RequestBody MalhaDTO malhaDTO) {
        return this.malhaService.cadastrarMalha(malhaDTO);       		
        		
    }

	@GetMapping
    public List<MalhaDTO> retornaMalhas() {
		return this.malhaService.retornaMalhas();
       
        		
        		
    }

}
