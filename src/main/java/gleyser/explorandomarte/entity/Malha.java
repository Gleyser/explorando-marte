package gleyser.explorandomarte.entity;

import gleyser.explorandomarte.exception.ColisaoException;
import gleyser.explorandomarte.exception.SondaNaoEncontradaException;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "malha_sonda_mapping",
			joinColumns = {@JoinColumn(name = "malha_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "sonda_id", referencedColumnName = "id")})
	@MapKeyJoinColumn(name = "localizacao_id")
	private Map<Localizacao, Sonda> sondas;

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

	public Map<Localizacao, Sonda> getSondas() {
		return sondas;
	}

	public void setSondas(Map<Localizacao, Sonda> sondas) {
		this.sondas = sondas;
	}

	public Sonda getSonda(Localizacao localizacao) throws SondaNaoEncontradaException {
		if (!this.sondas.containsKey(localizacao)){
			throw new SondaNaoEncontradaException();
		}
		return this.sondas.get(localizacao);
	}

	public void salvarSonda(Sonda sonda) throws ColisaoException {
		if (this.sondas.containsKey(sonda.getLocalizacaoAtual())){
			throw new ColisaoException();
		}
		this.sondas.put(sonda.getLocalizacaoAtual(), sonda);
	}

	public void removerSonda(Sonda sonda) throws SondaNaoEncontradaException {
		if (!this.sondas.containsKey(sonda.getLocalizacaoAtual())){
			throw new SondaNaoEncontradaException();
		}
		this.sondas.remove(sonda.getLocalizacaoAtual());
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
