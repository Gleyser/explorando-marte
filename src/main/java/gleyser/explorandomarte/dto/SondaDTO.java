package gleyser.explorandomarte.dto;

import gleyser.explorandomarte.enums.Direcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SondaDTO {
    private Long id;

    @NotNull(message = "A posicao Atual não pode ser nulo")
    @Valid
    private LocalizacaoDTO posicaoAtual;

    @NotNull(message = "A direcao não pode ser nulo")
    private Direcao direcao;
}
