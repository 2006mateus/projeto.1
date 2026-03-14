public class CartaDano extends Cartas{
    private int dano;

    public CartaDano(String nome, String descricao, int custo, int dano){
        super(nome, descricao, custo);
        this.dano = dano;
    }

    public int getDano(){
        return dano;
    }
}
