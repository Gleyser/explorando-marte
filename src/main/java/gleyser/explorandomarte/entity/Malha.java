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

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Malha malha = (Malha) o;

		if (id != null ? !id.equals(malha.id) : malha.id != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (pontoInferiorEsquerdo != null ? pontoInferiorEsquerdo.hashCode() : 0);
		result = 31 * result + (pontoSuperiorDireito != null ? pontoSuperiorDireito.hashCode() : 0);
		return result;
	}
}
