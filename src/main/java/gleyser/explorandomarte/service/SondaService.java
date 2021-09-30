package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.entity.Localizacao;
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

import java.util.ArrayList;
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

    public SondaDTO cadastrarSonda(SondaDTO sondaDTO) throws MalhaNaoEncontradaException, ColisaoException {
        Malha malha = this.malhaService.retornaMalhaPeloId(sondaDTO.getIdMalha());
        Sonda sondaParaSalvar = this.sondaMapper.toModel(sondaDTO);
        sondaParaSalvar.setMalha(malha);
        Localizacao localizacaoRecuperada = this.localizacaoService.retornaLocalizacao(sondaDTO.getLocalizacaoAtual());
        if (localizacaoRecuperada != null){
            sondaParaSalvar.setLocalizacaoAtual(localizacaoRecuperada);
        }
        malha.salvarSonda(sondaParaSalvar);
        Sonda sondaSalva = this.sondaRepository.save(sondaParaSalvar);
        this.malhaService.salvarMalha(malha);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaSalva);
        return sondaRetorno;

    }

    public SondaDTO recuperaSondaPeloId(Long id) throws SondaNaoEncontradaException {
       Sonda sondaRecuperada = retornaSondaPeloIdAux(id);
       SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaRecuperada);
       return sondaRetorno;
    }

    public void deletaSondaPeloId(Long id) throws SondaNaoEncontradaException, MalhaNaoEncontradaException {
        Sonda sondaRecuperada = retornaSondaPeloIdAux(id);
        Malha malha = this.malhaService.retornaMalhaPeloId(sondaRecuperada.getMalha().getId());
        malha.removerSonda(sondaRecuperada);
        this.malhaService.salvarMalha(malha);
        this.sondaRepository.delete(sondaRecuperada);

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
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaASerVirada);
        return sondaRetorno;
    }

    public SondaDTO moverASonda(Long id) throws SondaNaoEncontradaException, ColisaoException, MalhaNaoEncontradaException {
        //Sonda sondaASerMovida = retornaSondaPeloIdAux(id);
        //Malha malha = sondaASerMovida.getMalha();
        //System.out.println(sondaASerMovida.getLocalizacaoAtual());
        //sondaASerMovida.mover();
        //System.out.println(sondaASerMovida.getLocalizacaoAtual());
        //malha.salvarSonda(sondaASerMovida);
        //this.malhaService.salvarMalha(malha);
        //SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaASerMovida);
        List<String> acoes = new ArrayList<String>();
        acoes.add(Acao.M.toString());
        return processarInstrucoes(id, acoes);
    }

    public SondaDTO processarInstrucoes(Long id, List<String> instrucoes) throws SondaNaoEncontradaException, ColisaoException, MalhaNaoEncontradaException {
        List<Acao> acoes = instrucoes.stream().map(x -> Acao.valueOf(x)).collect(Collectors.toList());
        Sonda sondaASerMovida = retornaSondaPeloIdAux(id);
        Malha malha = this.malhaService.retornaMalhaPeloId(sondaASerMovida.getMalha().getId());
        for (Acao acao : acoes){
            //System.out.println(sondaASerMovida.getLocalizacaoAtual().toString());
            sondaASerMovida = acao.executaAcao(sondaASerMovida);
            //System.out.println(sondaASerMovida.getLocalizacaoAtual().toString());
            malha.salvarSonda(sondaASerMovida);
        }
        this.malhaService.salvarMalha(malha);
        SondaDTO sondaRetorno = this.sondaMapper.toDTO(sondaASerMovida);
        return sondaRetorno;
    }

    private Sonda retornaSondaPeloIdAux(Long id) throws SondaNaoEncontradaException {
        return this.sondaRepository.findById(id).
                orElseThrow(() -> new SondaNaoEncontradaException());
    }



}
