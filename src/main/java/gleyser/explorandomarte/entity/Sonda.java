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

    //@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,  CascadeType.PERSIST, CascadeType.REMOVE})
    //@Transient
    //Usar Embedded (verificar a ordem correta)
    private Localizacao localizacao;

    @Enumerated(EnumType.STRING)
    private Direcao direcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "malha_id")
    private Malha malha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
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
        this.localizacao = this.direcao.movimenta(this.localizacao);
    }

    public Malha getMalha() {
        return malha;
    }

    public void setMalha(Malha malha) {
        this.malha = malha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sonda sonda = (Sonda) o;

        if (id != null ? !id.equals(sonda.id) : sonda.id != null) return false;
        if (localizacao != null ? !localizacao.equals(sonda.localizacao) : sonda.localizacao != null)
            return false;
        if (direcao != sonda.direcao) return false;
        return malha != null ? malha.equals(sonda.malha) : sonda.malha == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (localizacao != null ? localizacao.hashCode() : 0);
        result = 31 * result + (direcao != null ? direcao.hashCode() : 0);
        result = 31 * result + (malha != null ? malha.hashCode() : 0);
        return result;
    }

}
