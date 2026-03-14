public abstract class Entidade {
    private String nome;
    private int vida;
    private int escudo;

    public Entidade(String nome, int vida, int escudo){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }

    public void receber_dano(int dano) {
        if (dano >= (this.vida + this.escudo)){
            this.vida = 0;
        } else if (this.escudo >= dano){ 
            this.escudo -= dano;
        } else {
            this.vida = this.vida + this.escudo - dano;
            this.escudo = 0;
        }
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

    public int getVida(){
        return vida;
    }

    public int getEscudo(){
        return escudo;
    }

    public String getNome(){
        return nome;
    }
}
