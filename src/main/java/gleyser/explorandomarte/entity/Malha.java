package gleyser.explorandomarte.entity;

import gleyser.explorandomarte.exception.ColisaoException;
import gleyser.explorandomarte.exception.SondaNaoEncontradaException;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Malha {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Localizacao pontoInferiorEsquerdo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Localizacao pontoSuperiorDireito;

	@OneToMany(mappedBy = "malha", cascade = {CascadeType.ALL})
	private List<Sonda> sondas;

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

	public List<Sonda> getSondas() {
		return sondas;
	}

	public void adicionaSonda(Sonda sonda) throws ColisaoException {
		this.sondas.add(sonda);
	}

	public void removerSonda(Sonda sonda) throws SondaNaoEncontradaException {
		this.sondas.remove(sonda);
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
