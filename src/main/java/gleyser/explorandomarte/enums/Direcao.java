package gleyser.explorandomarte.enums;

import gleyser.explorandomarte.entity.Localizacao;

public enum Direcao {
    NORTH {
        public Direcao viraParaDireita() {
            return Direcao.EAST;
        }

        public Direcao viraParaEsquerda() {
            return Direcao.WEST;
        }

        public Localizacao movimenta(Localizacao localizacao) {
            return
        }
    }
    ,
    WEST {
        public Direcao viraParaDireita() {
            return Direcao.NORTH;
        }

        public Direcao viraParaEsquerda() {
            return Direcao.SOUTH;
        }

        public Localizacao movimenta(Localizacao localizacao) {
            return
        }

    }
    ,
    SOUTH {
        public Direcao viraParaDireita() {
            return Direcao.WEST;
        }

        public Direcao viraParaEsquerda() {
            return Direcao.EAST;
        }

        public Localizacao movimenta(Localizacao localizacao) {
            return
        }

    }
    ,
    EAST {
        public Direcao viraParaDireita() {
            return Direcao.SOUTH;
        }

        public Direcao viraParaEsquerda() {
            return Direcao.NORTH;
        }

        public Localizacao movimenta(Localizacao localizacao) {
            return
        }

    };

    public abstract Direcao viraParaDireita();
    public abstract Direcao viraParaEsquerda();
    public abstract Localizacao movimenta(Localizacao localizacao);

}
