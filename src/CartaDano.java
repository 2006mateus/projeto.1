public class CartaDano {
    String nome;
    int custo;
    int dano;

    public void usar(Inimigo inimigo){
        inimigo.vida -= dano;
    }
}
