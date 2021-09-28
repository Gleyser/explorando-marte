package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.MalhaDTO;
import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.entity.Malha;
import gleyser.explorandomarte.entity.Sonda;
import gleyser.explorandomarte.exception.SondaNaoEncontradaException;
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

    public SondaDTO recuperaMalhaPeloId(Long id) throws SondaNaoEncontradaException {
       Sonda sondaRecuperada = retornaSondaPeloIdAux(id);
       SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaRecuperada);
       return sondaRetorno;
    }

    public void deletaMalhaPeloId(Long id) throws SondaNaoEncontradaException {
        Sonda sondaRecuperada = retornaSondaPeloIdAux(id);
        this.sondaRepository.delete(sondaRecuperada);
    }

    public SondaDTO atualizaSondaPeloId(Long id, SondaDTO sondaDTO) throws SondaNaoEncontradaException {
        retornaSondaPeloIdAux(id);
        Sonda sondaASerAtualizada = this.sondaMapper.toModel(sondaDTO);
        sondaASerAtualizada.setId(id);
        Sonda sondaAtualizada = this.sondaRepository.save(sondaASerAtualizada);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaAtualizada);
        return sondaRetorno;
    }

    public SondaDTO viraSondaParaEsquerda(Long id) throws SondaNaoEncontradaException {
        Sonda sondaASerVirada = retornaSondaPeloIdAux(id);
        sondaASerVirada.viraParaEsquerda();
        Sonda sondaAtualizada = this.sondaRepository.save(sondaASerVirada);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaAtualizada);
        return sondaRetorno;
    }

    public SondaDTO viraSondaParaDireita(Long id) throws SondaNaoEncontradaException {
        Sonda sondaASerVirada = retornaSondaPeloIdAux(id);
        sondaASerVirada.viraParaDireita();
        Sonda sondaAtualizada = this.sondaRepository.save(sondaASerVirada);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaAtualizada);
        return sondaRetorno;
    }

    public SondaDTO moverASonda(Long id) throws SondaNaoEncontradaException {
        Sonda sondaASerMovida = retornaSondaPeloIdAux(id);
        sondaASerMovida.mover();
        Sonda sondaMovida = this.sondaRepository.save(sondaASerMovida);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaMovida);
        return sondaRetorno;
    }

    private Sonda retornaSondaPeloIdAux(Long id) throws SondaNaoEncontradaException {
        return this.sondaRepository.findById(id).
                orElseThrow(() -> new SondaNaoEncontradaException());
    }


}
