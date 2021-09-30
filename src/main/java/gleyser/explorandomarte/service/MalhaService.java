package gleyser.explorandomarte.service;

import gleyser.explorandomarte.entity.Malha;
import gleyser.explorandomarte.exception.MalhaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.mapper.MalhaMapper;
import gleyser.explorandomarte.repository.MalhaRepository;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<MalhaDTO> retornaMalhas() {
		List<Malha> todasAsMalhas = this.malhaRepository.findAll();
		return todasAsMalhas.stream()
				.map(this.malhaMapper::toDTO)
				.collect(Collectors.toList());

	}

	public MalhaDTO recuperaMalhaPeloId(Long id) throws MalhaNaoEncontradaException {
		Malha malhaRecuperada = retornaMalhaPeloId(id);
		MalhaDTO malhaRetorno = this.malhaMapper.toDTO(malhaRecuperada);
		return malhaRetorno;
	}

	public void deletaMalhaPeloId(Long id) throws MalhaNaoEncontradaException {
		Malha malhaRecuperada = retornaMalhaPeloId(id);
		this.malhaRepository.delete(malhaRecuperada);
	}

	public MalhaDTO atualizaMalhaPeloId(Long id, MalhaDTO malhaDTO) throws MalhaNaoEncontradaException {
		retornaMalhaPeloId(id);
		Malha malhaASerAtualizada = this.malhaMapper.toModel(malhaDTO);
		malhaASerAtualizada.setId(id);
		Malha malhaAtualizada = this.malhaRepository.save(malhaASerAtualizada);
		MalhaDTO malhaRetorno = this.malhaMapper.toDTO(malhaAtualizada);
		return malhaRetorno;

	}

	protected Malha retornaMalhaPeloId(Long id) throws MalhaNaoEncontradaException {
		return this.malhaRepository.findById(id).
				orElseThrow(() -> new MalhaNaoEncontradaException());
	}

	protected Malha salvarMalha(Malha malha){
		return this.malhaRepository.save(malha);
	}

}
