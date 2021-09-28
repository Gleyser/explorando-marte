package gleyser.explorandomarte.enums;

import gleyser.explorandomarte.entity.Localizacao;
import gleyser.explorandomarte.entity.Sonda;

public enum Acao {
    L {
        public Sonda executaAcao(Sonda sonda){
            sonda.viraParaEsquerda();
            return sonda;
        }

    }
    ,
    R {
        public Sonda executaAcao(Sonda sonda){
            sonda.viraParaDireita();
            return sonda;
        }

    }
    ,
    M {
        public Sonda executaAcao(Sonda sonda){
            sonda.mover();
            return sonda;
        }

    };

    public abstract Sonda executaAcao(Sonda sonda);

}
