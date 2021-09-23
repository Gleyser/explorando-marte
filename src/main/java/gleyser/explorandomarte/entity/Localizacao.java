package gleyser.explorandomarte.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Localizacao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private Integer coordenadaX;
	
	@Column(nullable = false)
	private Integer coordenadaY;

	public Long getId() {
		return id;
	}	

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}		

}
