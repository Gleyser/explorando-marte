package gleyser.explorandomarte.service;

import gleyser.explorandomarte.entity.Malha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.mapper.MalhaMapper;
import gleyser.explorandomarte.repository.MalhaRepository;

@Service
public class MalhaService {	
	
	private final MalhaRepository malhaRepository;
	
	private final MalhaMapper malhaMapper = MalhaMapper.INSTANCE;
	 
	@Autowired 
	public MalhaService(MalhaRepository malhaRepository) {		
		this.malhaRepository = malhaRepository;
	}


	public MalhaDTO cadastrarMalha(MalhaDTO malhaDTO) {
		Malha malhaParaSalvar = this.malhaMapper.toModel(malhaDTO);
		Malha malhaSalva = this.malhaRepository.save(malhaParaSalvar);
		MalhaDTO malhaRetorno = this.malhaMapper.toDTO(malhaSalva);
		return malhaRetorno;
	}

}
