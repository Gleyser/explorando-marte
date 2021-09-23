package gleyser.explorandomarte.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import gleyser.explorandomarte.dto.LocalizacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Malha {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;	
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Localizacao pontoInferiorEsquerdo;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Localizacao pontoSuperiorDireito;

	public Long getId() {
		return id;
	}
	
	public Localizacao getPontoInferiorEsquerdo() {
		return pontoInferiorEsquerdo;
	}

	public void setPontoInferiorEsquerdo(Localizacao pontoInferiorEsquerdo) {
		this.pontoInferiorEsquerdo = pontoInferiorEsquerdo;
	}

	public Localizacao getPontoSuperiorDireito() {
		return pontoSuperiorDireito;
	}

	public void setPontoSuperiorDireito(Localizacao pontoSuperiorDireito) {
		this.pontoSuperiorDireito = pontoSuperiorDireito;
	}	

}
