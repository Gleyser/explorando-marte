package gleyser.explorandomarte.enums;

public enum Direcao {
    NORTH {
        public Direcao viraParaDireita() {
            return Direcao.EAST;
        }

        public Direcao viraParaEsquerda() {
            return Direcao.WEST;
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

    }
    ,
    SOUTH {
        public Direcao viraParaDireita() {
            return Direcao.WEST;
        }

        public Direcao viraParaEsquerda() {
            return Direcao.EAST;
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

    };

    public abstract Direcao viraParaDireita();
    public abstract Direcao viraParaEsquerda();

}
