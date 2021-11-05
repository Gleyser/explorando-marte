package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.entity.Malha;
import gleyser.explorandomarte.entity.Sonda;
import gleyser.explorandomarte.enums.Acao;

import gleyser.explorandomarte.exception.ColisaoException;
import gleyser.explorandomarte.exception.MalhaNaoEncontradaException;

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
    private final MalhaService malhaService;

    private final SondaMapper sondaMapper = SondaMapper.INSTANCE;

    @Autowired
    public SondaService(SondaRepository sondaRepository, MalhaService malhaService) {
        this.sondaRepository = sondaRepository;
        this.malhaService = malhaService;
    }

    public List<SondaDTO> retornaSondas() {
        List<Sonda> todasAsSondas = this.sondaRepository.findAll();
        return todasAsSondas.stream()
                .map(this.sondaMapper::toDTO)
                .collect(Collectors.toList());

    }

    public SondaDTO cadastrarSonda(SondaDTO sondaDTO) throws MalhaNaoEncontradaException, ColisaoException {
        Long idDaMalha = sondaDTO.getIdMalha();
        Malha malha = this.malhaService.retornaMalhaPeloId(idDaMalha);
        Sonda sondaParaSalvar = this.sondaMapper.toModel(sondaDTO);
        sondaParaSalvar.setMalha(malha);
        malha.salvarSonda(sondaParaSalvar);
        Sonda sondaSalva = this.sondaRepository.save(sondaParaSalvar);
        this.malhaService.salvaMalha(malha);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaSalva);
        return sondaRetorno;

    }

    public SondaDTO retornaSondaDTOPeloId(Long id) throws SondaNaoEncontradaException {
       Sonda sondaRecuperada = retornaSondaPeloId(id);
       SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaRecuperada);
       return sondaRetorno;
    }

    public void deletaSondaPeloId(Long id) throws SondaNaoEncontradaException, MalhaNaoEncontradaException {
        Sonda sondaRecuperada = retornaSondaPeloId(id);
        Malha malha = this.malhaService.retornaMalhaPeloId(sondaRecuperada.getMalha().getId());
        malha.removerSonda(sondaRecuperada);
        this.malhaService.salvaMalha(malha);
        this.sondaRepository.delete(sondaRecuperada);

    }

    public SondaDTO viraSondaParaEsquerda(Long id) throws SondaNaoEncontradaException {
        Sonda sondaASerVirada = retornaSondaPeloId(id);
        sondaASerVirada.viraParaEsquerda();
        Sonda sondaAtualizada = this.sondaRepository.save(sondaASerVirada);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaAtualizada);
        return sondaRetorno;
    }

    public SondaDTO viraSondaParaDireita(Long id) throws SondaNaoEncontradaException {
        Sonda sondaASerVirada = retornaSondaPeloId(id);
        sondaASerVirada.viraParaDireita();
        Sonda sondaAtualizada = this.sondaRepository.save(sondaASerVirada);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaASerVirada);
        return sondaRetorno;
    }

    public SondaDTO moverASonda(Long id) throws SondaNaoEncontradaException {
        Sonda sondaASerMovida = retornaSondaPeloId(id);
        sondaASerMovida.mover();
        sondaASerMovida.getLocalizacao().incrementaCoordenadaX();


        Sonda sondaMovida = this.sondaRepository.save(sondaASerMovida);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaMovida);
        return sondaRetorno;
    }

    public SondaDTO processarInstrucoes(Long id, List<String> instrucoes) throws SondaNaoEncontradaException {
        List<Acao> acoes = instrucoes.stream().map(x -> Acao.valueOf(x)).collect(Collectors.toList());
        Sonda sondaASerMovida = retornaSondaPeloId(id);
        for (Acao acao : acoes){
            acao.executaAcao(sondaASerMovida);
        }
        Sonda sondaMovida = this.sondaRepository.save(sondaASerMovida);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaMovida);
        return sondaRetorno;
    }

    private Sonda retornaSondaPeloId(Long id) throws SondaNaoEncontradaException {
        return this.sondaRepository.findById(id).
                orElseThrow(() -> new SondaNaoEncontradaException());
    }

}
