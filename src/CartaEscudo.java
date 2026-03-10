public class CartaEscudo {
    String nome;
    int escudo;
    int custo;

    public void usar(Heroi heroi){
        heroi.escudo += escudo;
    }
}
