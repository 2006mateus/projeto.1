public class Inimigo extends Entidade{
    private int ataque;

    public Inimigo(String nome, int vida, int escudo, int ataque){
        super(nome, vida, escudo);
        this.ataque = ataque;
    }

    public void atacar(Heroi heroi){
        heroi.receber_dano(this.ataque);
    }

    public int getAtaque(){
        return ataque;
    }
}
