import java.util.*;

public class CardsManager{
    private ArrayList<Cards> deck = new ArrayList<>();
    private ArrayDeque<Cards> discardPile = new ArrayDeque<>();

    public void buyCard(){
        if (deck.isEmpty()){
            recycleDeck();
        }
        Cards newCard = deck.remove(deck.size() - 1);
        System.out.println("Carta" + newCard.getName() + "comprada!");
    }

    public void discardCard(Cards card){
        discardPile.push(card);
    }

    public void recycleDeck(){
        deck.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(deck);
    }

    public void addCard(Cards card){
        deck.add(card);
    }
}
