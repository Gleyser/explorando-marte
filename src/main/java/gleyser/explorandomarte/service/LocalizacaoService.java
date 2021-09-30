package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import gleyser.explorandomarte.entity.Localizacao;
import gleyser.explorandomarte.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    public Localizacao retornaLocalizacao(LocalizacaoDTO localizacaoDTO){
        Integer coordenadaX = localizacaoDTO.getCoordenadaX();
        Integer coordenadaY = localizacaoDTO.getCoordenadaY();
        return this.localizacaoRepository.findByCoordenadaXAndCoordenadaY(coordenadaX, coordenadaY);
    }

    public void atualizaLocalizacao(Localizacao localizacao){
        this.localizacaoRepository.save(localizacao);
    }
}
