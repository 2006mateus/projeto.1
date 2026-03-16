import java.util.*;

public class Gerenciador_de_Cartas{
    private ArrayList<Cartas> pilhaDeCompra = new ArrayList<>();
    private ArrayDeque<Cartas> pilhaDeDescarte = new ArrayDeque<>();

    public void comprarCarta(){
        if (pilhaDeCompra.isEmpty()){
            reclicarBaralho();
        }
        Cartas cartaNova = pilhaDeCompra.remove(pilhaDeCompra.size() - 1);
        System.out.println("Carta" + cartaNova.getNome() + "comprada!");
    }

    public void descartarCarta(Cartas carta){
        pilhaDeDescarte.push(carta);
    }

    public void reclicarBaralho(){
        pilhaDeCompra.addAll(pilhaDeDescarte);
        pilhaDeDescarte.clear();
        Collections.shuffle(pilhaDeCompra);
    }

    public void adicionarCarta(Cartas carta){
        pilhaDeCompra.add(carta);
    }
}
