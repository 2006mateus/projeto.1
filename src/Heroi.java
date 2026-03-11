public class Heroi {
    private String nome;
    private int vida;
    private int escudo;
    private int energia;

    public void receber_dano(int dano, Heroi heroi) {
        if (dano >= (heroi.vida + heroi.escudo)){
            heroi.vida = 0;
        } else if (heroi.escudo >= dano){ 
            heroi.escudo -= dano;
        } else {
            heroi.vida = heroi.vida + heroi.escudo - dano;
        }
    }

    public void perderEnergia(int custo){
        energia -= custo;
    }

    public void ganhar_escudo(int escudo) {
        this.escudo += escudo;
    }

    public Boolean esta_vivo() {
        if (this.vida <= 0){
            return false;
        } else {
            return true;
        }
    }

    public int get_vida() {
        return this.vida;
    }

    public String get_nome() {
        return this.nome;
    }

    public void set_escudo(int escudo) {
        this.escudo = escudo;
    }

    public int get_escudo() {
        return this.escudo;
    }

    public void set_energia(int energia) {
        this.energia = 10;
    }

    public int get_energia() {
        return this.energia;
    }

    public Heroi(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }
}
