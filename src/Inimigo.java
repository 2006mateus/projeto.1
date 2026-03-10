public class Inimigo {
    String nome;
    int vida;
    int escudo;
    boolean vivo;
    int ataque;

    public void receber_dano_inimigo(int dano, Inimigo inimigo) {
        if (dano >= (inimigo.vida + inimigo.escudo)) {
            inimigo.vida = 0;
        } else if (inimigo.escudo >= dano) { 
            inimigo.escudo -= dano;
        } else {
            inimigo.vida = inimigo.vida + inimigo.escudo - dano;
        }
    }

    public void atacar(Heroi heroi, int atacar){
        heroi.vida -= atacar;
    }

    public void esta_vivo_inimigo(Inimigo inimigo){
        if (inimigo.vida <= 0){
            inimigo.vivo = false;
        } else {
            inimigo.vivo = true;
        }
    }
}
