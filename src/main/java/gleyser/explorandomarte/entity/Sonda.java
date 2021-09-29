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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacaoAtual;

    @Enumerated(EnumType.STRING)
    private Direcao direcao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "malha_id")
    private Malha malha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public void viraParaEsquerda(){
        this.direcao = this.direcao.viraParaEsquerda();
    }

    public void viraParaDireita(){
        this.direcao = this.direcao.viraParaDireita();
    }

    public void mover(){
        this.localizacaoAtual = this.direcao.movimenta(this.localizacaoAtual);
    }

    public Malha getMalha() {
        return malha;
    }

    public void setMalha(Malha malha) {
        this.malha = malha;
    }
}
