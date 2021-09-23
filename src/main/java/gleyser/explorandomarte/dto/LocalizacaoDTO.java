package gleyser.explorandomarte.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizacaoDTO {
		
	@Min(value = 0, message="Deve possuir valor igual ou maior que 0")
	@NotNull(message = "A coordenada X não pode ser nulo")
    private Integer coordenadaX;
	
	@Min(value = 0, message="Deve possuir valor igual ou maior que 0")
	@NotNull(message = "A coordenada Y não pode ser nulo")
    private Integer coordenadaY;


}
