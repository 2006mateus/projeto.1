public class Heroi {
    String nome;
    int vida;
    int escudo;
    boolean vivo;
    int energia;

    public void receber_dano(int dano, Heroi heroi) {
        if (dano >= (heroi.vida + heroi.escudo)){
            heroi.vida = 0;
        } else if (heroi.escudo >= dano){ 
            heroi.escudo -= dano;
        } else {
            heroi.vida = heroi.vida + heroi.escudo - dano;
        }
    }

    public void ganhar_escudo(int escudo, Heroi heroi) {
        heroi.escudo += escudo;
    }

    public void esta_vivo(Heroi heroi) {
        if (heroi.vida <= 0){
            heroi.vivo = false;
        } else {
            heroi.vivo = true;
        }
    }
}
