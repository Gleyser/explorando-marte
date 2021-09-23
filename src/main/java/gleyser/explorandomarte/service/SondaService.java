package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.entity.Sonda;
import gleyser.explorandomarte.mapper.SondaMapper;
import gleyser.explorandomarte.repository.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SondaService {

    private final SondaRepository sondaRepository;

    private final SondaMapper sondaMapper = SondaMapper.INSTANCE;

    @Autowired
    public SondaService(SondaRepository sondaRepository) {
        this.sondaRepository = sondaRepository;
    }

    public List<SondaDTO> retornaSondas() {
        List<Sonda> todasAsSondas = this.sondaRepository.findAll();
        return todasAsSondas.stream()
                .map(this.sondaMapper::toDTO)
                .collect(Collectors.toList());

    }

    public SondaDTO cadastrarSonda(SondaDTO sondaDTO) {
        Sonda sondaParaSalvar = this.sondaMapper.toModel(sondaDTO);
        Sonda sondaSalva = this.sondaRepository.save(sondaParaSalvar);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaSalva);
        return sondaRetorno;

    }
}
