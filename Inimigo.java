public class Inimigo {
    String nome;
    int vida;
    int escudo;
    int vivo;
    int ataque;

    public static void receber_dano_inimigo(int dano, Inimigo inimigo){
        if (dano >= (inimigo.vida + inimigo.escudo)){
            inimigo.vida = 0;
        } else if (inimigo.escudo >= dano){ 
            inimigo.escudo -= dano;
        } else {
            inimigo.vida = inimigo.vida + inimigo.escudo - dano;
        }
    }

    public static void ganhar_escudo_inimigo(int escudo, Inimigo inimigo){
        inimigo.escudo += escudo;
    }
}
