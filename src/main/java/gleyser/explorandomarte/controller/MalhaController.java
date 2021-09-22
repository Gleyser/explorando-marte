package gleyser.explorandomarte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

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
    public String cadastrarMalha() {
        return this.malhaService.cadastrarMalha();
        		
        		
    }

}
