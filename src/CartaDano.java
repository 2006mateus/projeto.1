public class CartaDano {
    private String nome;
    private int custo;
    private int dano;

    public CartaDano(String nome, int custo, int dano){
        this.nome = nome;
        this.custo = custo;
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
