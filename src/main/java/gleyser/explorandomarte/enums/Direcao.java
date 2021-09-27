package gleyser.explorandomarte.enums;

public enum Direcao {
    NORTH {
        public Direcao moveParaDireita() {
            return Direcao.EAST;
        }

        public Direcao moveParaEsquerda() {
            return Direcao.WEST;
        }
    }
    ,
    WEST {
        public Direcao moveParaDireita() {
            return Direcao.NORTH;
        }

        public Direcao moveParaEsquerda() {
            return Direcao.SOUTH;
        }

    }
    ,
    SOUTH {
        public Direcao moveParaDireita() {
            return Direcao.WEST;
        }

        public Direcao moveParaEsquerda() {
            return Direcao.EAST;
        }

    }
    ,
    EAST {
        public Direcao moveParaDireita() {
            return Direcao.SOUTH;
        }

        public Direcao moveParaEsquerda() {
            return Direcao.NORTH;
        }

    };

    public abstract Direcao moveParaDireita();
    public abstract Direcao moveParaEsquerda();

}
