public class Heroi extends Entidade{
    private int energia;

    public Heroi(String nome, int vida, int escudo, int energia) {
        super(nome, vida, escudo);
        this.energia = energia;
    }

    public void perderEnergia(int custo){
        energia -= custo;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia){
        this.energia = energia;
    }
}
