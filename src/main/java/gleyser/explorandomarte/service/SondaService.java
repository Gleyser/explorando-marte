package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.entity.Localizacao;
import gleyser.explorandomarte.entity.Malha;
import gleyser.explorandomarte.entity.Sonda;
import gleyser.explorandomarte.enums.Acao;
import gleyser.explorandomarte.exception.MalhaNaoEncontradaException;
import gleyser.explorandomarte.exception.SondaNaoEncontradaException;
import gleyser.explorandomarte.mapper.SondaMapper;
import gleyser.explorandomarte.repository.LocalizacaoRepository;
import gleyser.explorandomarte.repository.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SondaService {

    private final SondaRepository sondaRepository;
    private final LocalizacaoService localizacaoService;
    private final MalhaService malhaService;

    private final SondaMapper sondaMapper = SondaMapper.INSTANCE;

    @Autowired
    public SondaService(SondaRepository sondaRepository, MalhaService malhaService, LocalizacaoService localizacaoService) {
        this.sondaRepository = sondaRepository;
        this.malhaService = malhaService;
        this.localizacaoService = localizacaoService;
    }

    public List<SondaDTO> retornaSondas() {
        List<Sonda> todasAsSondas = this.sondaRepository.findAll();
        return todasAsSondas.stream()
                .map(this.sondaMapper::toDTO)
                .collect(Collectors.toList());

    }

    public SondaDTO cadastrarSonda(SondaDTO sondaDTO) throws MalhaNaoEncontradaException {
        Malha malha = this.malhaService.retornaMalhaPeloId(sondaDTO.getIdMalha());
        Sonda sondaParaSalvar = this.sondaMapper.toModel(sondaDTO);
        Localizacao localizacaoRecuperada = this.localizacaoService.retornaLocalizacao(sondaDTO.getLocalizacaoAtual());
        if (localizacaoRecuperada != null){
            sondaParaSalvar.setLocalizacaoAtual(localizacaoRecuperada);
        }
        Sonda sondaSalva = this.sondaRepository.save(sondaParaSalvar);
        malha.salvarSonda(sondaSalva);
        this.malhaService.salvarMalha(malha);



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

    public SondaDTO processarInstrucoes(Long id, List<String> instrucoes) throws SondaNaoEncontradaException {
        List<Acao> acoes = instrucoes.stream().map(x -> Acao.valueOf(x)).collect(Collectors.toList());
        Sonda sondaASerMovida = retornaSondaPeloIdAux(id);
        for (Acao acao : acoes){
            acao.executaAcao(sondaASerMovida);
        }
        Sonda sondaMovida = this.sondaRepository.save(sondaASerMovida);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaMovida);
        return sondaRetorno;
    }

    private Sonda retornaSondaPeloIdAux(Long id) throws SondaNaoEncontradaException {
        return this.sondaRepository.findById(id).
                orElseThrow(() -> new SondaNaoEncontradaException());
    }



}
