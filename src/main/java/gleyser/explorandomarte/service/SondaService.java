package gleyser.explorandomarte.service;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import gleyser.explorandomarte.dto.SondaDTO;
import gleyser.explorandomarte.enums.Direcao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SondaService {

    public List<SondaDTO> retornaSondas() {
        SondaDTO sonda = new SondaDTO();
        sonda.setDirecao(Direcao.W);
        sonda.setLocalizacaoAtual(new LocalizacaoDTO(10, 10));

        SondaDTO sonda1 = new SondaDTO();
        sonda1.setDirecao(Direcao.W);
        sonda1.setLocalizacaoAtual(new LocalizacaoDTO(100, 100));

        List<SondaDTO> sondas = new ArrayList<>();
        sondas.add(sonda);
        sondas.add(sonda1);
        return sondas;

    }
}
