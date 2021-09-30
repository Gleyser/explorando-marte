package gleyser.explorandomarte.dto;

import gleyser.explorandomarte.enums.Direcao;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SondaDTO {
    private Long id;

    @NotNull(message = "A direcao não pode ser nulo")
    private Direcao direcao;

    @NotNull(message = "A posicao Atual não pode ser nulo")
    @Valid
    private LocalizacaoDTO localizacaoAtual;

    @NotNull(message = "O id da malha não pode ser nulo")
    @Valid
    private Long idMalha;


}
