package gleyser.explorandomarte.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gleyser.explorandomarte.entity.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MalhaDTO {
		
    private Long id;
	
    @NotNull(message = "A ponto inferior esquerdo não pode ser nulo")
	@Valid
	private LocalizacaoDTO pontoInferiorEsquerdo;
	
    @NotNull(message = "O ponto superior direito não pode ser nulo")
	@Valid
	private LocalizacaoDTO pontoSuperiorDireito;

}
