package gleyser.explorandomarte.entity;

import gleyser.explorandomarte.enums.Direcao;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Sonda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
    private Localizacao posicaoAtual;

    @Enumerated(EnumType.STRING)
    private Direcao direcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Localizacao getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Localizacao posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
