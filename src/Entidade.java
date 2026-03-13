public abstract class Entidade {
    private String nome;
    private int vida;
    private int escudo;
    private int dano;

    public Entidade(String nome, int vida, int escudo, int dano){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.dano = dano;
    }

    public void receber_dano(Entidade entidade) {
        if (dano >= (entidade.vida + entidade.escudo)){
            entidade.vida = 0;
        } else if (entidade.escudo >= dano){ 
            entidade.escudo -= dano;
        } else {
            entidade.vida = entidade.vida + entidade.escudo - dano;
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
}
