package gleyser.explorandomarte.entity;

import gleyser.explorandomarte.exception.ColisaoException;
import gleyser.explorandomarte.exception.SondaNaoEncontradaException;
import lombok.NoArgsConstructor;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.List;
import java.util.Map;
=======
>>>>>>> parent of e11945c (Associando sonda com malha)

@Entity
@NoArgsConstructor
public class Malha {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Localizacao pontoInferiorEsquerdo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Localizacao pontoSuperiorDireito;

	@OneToMany(mappedBy = "malha", cascade = {CascadeType.ALL})
	private List<Sonda> sondas;

=======
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
    private Localizacao pontoInferiorEsquerdo;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
    private Localizacao pontoSuperiorDireito;

>>>>>>> parent of e11945c (Associando sonda com malha)
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

<<<<<<< HEAD
	public List<Sonda> getSondas() {
		return sondas;
	}

	public void adicionaSonda(Sonda sonda) throws ColisaoException {
		this.sondas.add(sonda);
	}

	public void removerSonda(Sonda sonda) throws SondaNaoEncontradaException {
		this.sondas.remove(sonda);
	}

=======
>>>>>>> parent of e11945c (Associando sonda com malha)
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
