public class CartaDano {
    String nome;
    int custo;
    int dano;

    public void usar(Inimigo inimigo, Heroi heroi){
        inimigo.vida -= dano;
        heroi.energia -= custo;
    }
}
