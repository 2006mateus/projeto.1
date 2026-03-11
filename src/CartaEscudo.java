public class CartaEscudo {
    private String nome;
    private int escudo;
    private int custo;

    public CartaEscudo(String nome, int escudo, int custo){
        this.nome = nome;
        this.escudo = escudo;
        this.custo = custo;
    }

    public String getnome(){
        return nome;
    }

    public int getescudo(){
        return escudo;
    }

    public int getcusto(){
        return custo;
    }

    public void usar(Heroi heroi){
        heroi.perderEnergia(custo);
    }
}
