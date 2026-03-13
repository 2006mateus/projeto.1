public class CartaEscudo extends Cartas{
    private int escudo;

    public CartaEscudo(String nome, String descricao, int escudo, int custo){
        super(nome, descricao, custo);
        this.escudo = escudo;
    }

    public int getEscudo(){
        return escudo;
    }
}
