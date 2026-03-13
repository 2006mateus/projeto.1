public class CartaDano extends Cartas{
    private int dano;

    public CartaDano(int dano){
        this.dano = dano;
    }

    public String getnome(){
        return nome;
    }

    public int getDano(){
        return dano;
    }

    public int getcusto(){
        return custo;
    }

    public void usar(Inimigo inimigo, Heroi heroi){
        inimigo.receber_dano_inimigo(dano);;
        heroi.perderEnergia(custo);
    }
}
