public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int ataque;

    public Inimigo(String nome, int vida, int escudo, boolean vivo, int ataque){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.ataque = ataque;
    }

    public String getnome(){
        return nome;
    }

    public int getvida(Inimigo inimigo){
        return vida;
    }

    public int getescudo(Inimigo inimigo){
        return escudo;
    }

    public int getataque(Inimigo inimigo){
        return ataque;
    }

    public void setVida(int vida){
        this.vida = vida;
    }

    public void receber_dano_inimigo(int dano) {
        if (dano >= (vida + escudo)) {
            vida = 0;
        } else if (escudo >= dano) { 
            escudo -= dano;
        } else {
            vida = vida + escudo - dano;
        }
    }

    public void atacar(Heroi heroi){
        heroi.receber_dano(ataque, heroi);
    }

    public boolean esta_vivo_inimigo(){
        if (vida <= 0){
            return false;
        } else {
            return true;
        }
    }
}
