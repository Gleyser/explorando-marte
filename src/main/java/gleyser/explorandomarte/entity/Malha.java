package gleyser.explorandomarte.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Malha {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
    private Localizacao pontoInferiorEsquerdo;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
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
