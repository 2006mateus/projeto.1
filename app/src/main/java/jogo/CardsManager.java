package jogo;

import java.util.*;

/**
 * Gerenciador do ciclo de vida das cartas durante a partida.
 * <p>
 * Esta classe controla as quatro coleções principais de cartas:
 * <ul>
 * <li><b>Deck:</b> O baralho principal de onde as cartas são sorteadas.</li>
 * <li><b>Purchasable:</b> A seleção de cartas disponíveis para o jogador escolher no turno.</li>
 * <li><b>Hand:</b> As cartas que o herói possui atualmente para jogar.</li>
 * <li><b>Discard Pile:</b> A pilha de descarte para onde as cartas vão após o uso ou fim de turno.</li>
 * </ul>
 */
public class CardsManager {
    
    /** Lista de cartas atualmente na mão do jogador. */
    private ArrayList<Cards> hand = new ArrayList<>();
    
    /** Baralho principal de cartas. */
    private ArrayList<Cards> deck = new ArrayList<>();
    
    /** Subconjunto de cartas retiradas do deck que estão disponíveis para compra/escolha. */
    private ArrayList<Cards> purchasable = new ArrayList<>();
    
    /** Pilha de descarte (LIFO) que armazena cartas utilizadas ou descartadas. */
    private ArrayDeque<Cards> discardPile = new ArrayDeque<>();

    /**
     * Transfere uma carta da seleção de compra para a mão do jogador.
     * <p>
     * Se o baralho principal ficar vazio após a operação, a pilha de descarte 
     * é automaticamente reciclada e embaralhada.
     * </p>
     *
     * @param num O índice (iniciado em 1) da carta na lista de compráveis.
     */
    public void buyCard(int num) {
        if (num <= 0 || num > purchasable.size()) {
            System.out.println("Seleção inválida!");
            return;
        }
        // Remove da lista de seleção e do deck físico
        Cards newCard = purchasable.remove(num - 1);
        deck.remove(newCard); 
        hand.add(newCard);
        
        System.out.println("Carta " + newCard.getName() + " comprada!");
        
        if (!purchasable.isEmpty()) {
            ConsoleUI.clearScreen();
            printPurchasable();
        } else {
            System.out.println("Todas as cartas da rodada foram processadas!");
        }
        
        if (deck.isEmpty()) {
            recycleDeck();
        }
    }

    /**
     * Adiciona manualmente uma carta à pilha de descarte.
     *
     * @param card A carta a ser enviada ao descarte.
     */
    public void discardCard(Cards card) {
        discardPile.push(card);
    }

    /**
     * Move todas as cartas da pilha de descarte de volta para o baralho e as embaralha.
     */
    public void recycleDeck() {
        deck.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(deck);
    }

    /**
     * Adiciona uma nova carta ao baralho principal.
     *
     * @param card A carta a ser registrada no deck.
     */
    public void addCard(Cards card) {
        deck.add(card);
    }

    /**
     * Retorna a quantidade total de cartas no baralho.
     *
     * @return O tamanho atual do {@code deck}.
     */
    public int getQuantityDeck() {
        return deck.size();
    }

    /**
     * Retorna a quantidade de cartas que o jogador está segurando.
     *
     * @return O tamanho atual da {@code hand}.
     */
    public int getQuantityHand() {
        return hand.size();
    }

    /**
     * Verifica se o baralho principal está sem cartas.
     * * @return {@code true} se o deck estiver vazio, {@code false} caso contrário.
     */
    public boolean emptyDeck() {
        return deck.isEmpty();
    }

    /**
     * Exibe no console os detalhes de todas as cartas na mão do jogador.
     */
    public void printHand() {
        int i = 1;
        for (Cards card : hand) {
            System.out.println(i + " - " + card.getName());
            System.out.println("Descrição: " + card.getDescription());
            System.out.println("Custo: " + card.getCost());
            i += 1;
        }
    }

    /**
     * Exibe no console as opções de cartas disponíveis para serem movidas para a mão.
     */
    public void printPurchasable() {
        for (int i = 0; i < purchasable.size(); i += 1) {
            System.out.println((i + 1) + " - " + purchasable.get(i).getName());
            System.out.println("Descrição: " + purchasable.get(i).getDescription());
            System.out.println("Custo: " + purchasable.get(i).getCost());
        }
    }

    /**
     * Executa a lógica de jogar uma carta da mão.
     * <p>
     * Verifica se o herói tem energia suficiente. Se sim, aplica o efeito da carta,
     * deduz a energia do herói, remove a carta da mão e a envia para o descarte.
     * </p>
     *
     * @param index     O índice da carta na mão (0 a hand.size() - 1).
     * @param hero      O usuário da carta.
     * @param enemy     O alvo da carta.
     * @param publisher O sistema de eventos para processamento de efeitos.
     */
    public void useCard(int index, Hero hero, Enemy enemy, Publisher publisher) {
        Cards usedCard = hand.get(index);
        if (hero.getEnergy() < usedCard.getCost()) {
            System.out.println("Não há energia suficiente para utilizar esta carta!");
            return;
        }
        usedCard.use(hero, enemy, publisher);
        hero.loseEnergy(usedCard.getCost());
        hand.remove(index);
        discardPile.add(usedCard);
    }

    /**
     * Limpa a mão do jogador, movendo todas as cartas presentes para a pilha de descarte.
     */
    public void clearHand() {
        discardPile.addAll(hand);
        hand.clear();
    }

    /**
     * Seleciona até 4 cartas do topo do baralho e as disponibiliza na lista de compras.
     */
    public void moveToPurchasable() {
        int limit = Math.min(4, deck.size());
        for (int i = 0; i < limit; i += 1) {
            purchasable.add(deck.get(i));
        }
    }

    /**
     * Retorna o número de cartas disponíveis para compra na rodada atual.
     *
     * @return Quantidade de itens em {@code purchasable}.
     */
    public int getPurchasableQuantity() {
        return purchasable.size();
    }

    /**
     * Limpa a seleção de cartas disponíveis e embaralha o deck principal.
     */
    public void clearPurchasableAndShuffle() {
        purchasable.clear(); 
        Collections.shuffle(deck); 
        System.out.println("O deck foi re-embaralhado com as cartas restantes!");
    }
}