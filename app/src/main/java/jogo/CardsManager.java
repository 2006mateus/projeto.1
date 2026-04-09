package jogo;

import java.util.*;

/**
 * Gerencia as coleções de cartas durante a partida.
 * Controla o baralho (deck), a mão atual do jogador (hand) e a pilha de descarte (discardPile),
 * além de lidar com as ações de comprar, descartar, reciclar e utilizar cartas.
 */
public class CardsManager {
    private ArrayList<Cards> hand = new ArrayList<>();
    private ArrayList<Cards> deck = new ArrayList<>();
    private ArrayDeque<Cards> discardPile = new ArrayDeque<>();

    /**
     * Compra uma carta específica do baralho com base no seu índice e a adiciona à mão.
     * Caso o baralho fique vazio antes ou depois da compra, a pilha de descarte é reciclada.
     *
     * @param num A posição (baseada em 1) da carta no baralho que será comprada.
     */
    public void buyCard(int num) {
        if (deck.isEmpty()) {
            recycleDeck();
        }
        Cards newCard = deck.remove(num - 1);
        hand.add(newCard);
        printDeck();
        System.out.println("Carta " + newCard.getName() + " comprada!");
        if (deck.isEmpty()) {
            recycleDeck();
        }
    }

    /**
     * Adiciona uma carta diretamente à pilha de descarte.
     *
     * @param card A carta a ser descartada.
     */
    public void discardCard(Cards card) {
        discardPile.push(card);
    }

    /**
     * Recicla o baralho transferindo todas as cartas da pilha de descarte de volta 
     * para o baralho e embaralhando-as em seguida.
     */
    public void recycleDeck() {
        deck.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(deck);
    }

    /**
     * Adiciona uma nova carta ao final do baralho.
     *
     * @param card A carta a ser adicionada.
     */
    public void addCard(Cards card) {
        deck.add(card);
    }

    /**
     * Obtém a quantidade de cartas atualmente no baralho.
     *
     * @return O número de cartas no baralho.
     */
    public int getQuantityDeck() {
        return deck.size();
    }

    /**
     * Obtém a quantidade de cartas atualmente na mão do jogador.
     *
     * @return O número de cartas na mão.
     */
    public int getQuantityHand() {
        return hand.size();
    }

    /**
     * Verifica se a estrutura de dados está vazia.
     * * @return true se a estrutura estiver vazia, false caso contrário.
     */
    public boolean emptyDeck() {
        return hand.isEmpty();
    }

    /**
     * Imprime no console as cartas atualmente na mão do jogador,
     * exibindo seu índice, nome, descrição e custo.
     */
    public void printHand() {
        int i = 1;
        for (Cards card : hand) {
            System.out.println(i + " - " + card.name);
            System.out.println("Descrição: " + card.description);
            System.out.println("Custo: " + card.cost);
            i += 1;
        }
    }

    /**
     * Imprime no console as cartas atualmente no baralho,
     * exibindo seu índice, nome, descrição e custo.
     */
    public void printDeck() {
        int i = 1;
        for (Cards card : deck) {
            System.out.println(i + " - " + card.name);
            System.out.println("Descrição: " + card.description);
            System.out.println("Custo: " + card.cost);
            i += 1;
        }
    }

    /**
     * Utiliza uma carta da mão do jogador, aplicando seu efeito no jogo.
     * Verifica se o herói possui energia suficiente antes de executar a ação.
     * Lida com efeitos específicos de cartas especiais (ex: "Dardo", "oculos velhos") 
     * utilizando o padrão Publisher-Subscriber para os efeitos de status.
     * Após o uso, o custo de energia é deduzido e a carta vai para o descarte.
     *
     * @param index     O índice (baseado em 0) da carta na mão a ser utilizada.
     * @param hero      O herói que está utilizando a carta e consumindo energia.
     * @param enemy     O inimigo alvo dos efeitos de ataque ou debuff.
     * @param publisher O gerenciador de eventos (Publisher) para registrar efeitos contínuos.
     */
    public void useCard(int index, Hero hero, Enemy enemy, Publisher publisher) {
        Cards usedCard = hand.get(index);
        if (hero.getEnergy() < usedCard.cost) {
            System.out.println("Não há energia suficiente para utilizar esta carta!");
            return;
        }
        if (usedCard instanceof DamageCard) {

            usedCard.use(hero, enemy, publisher);

            if (usedCard.getName().equalsIgnoreCase("Dardo")) {
                System.out.println("O dardo perfurou o inimigo e aplicou Veneno!");
                
                Venom veneno = new Venom("Veneno do Dardo", enemy, 2, 5);
                
                enemy.applyEffect(veneno, enemy);
                
                publisher.subscribe(veneno); 
            }

            if (usedCard.getName().equalsIgnoreCase("oculos velhos")) {
                System.out.println("Os oculos velhos melhoram sua visao e aumentou seu foco!");
                
                Strength foco = new Strength("Foco", hero, 2, 5);
                
                hero.applyEffect(foco, hero);
                
                publisher.subscribe(foco); 
            }

        } else {
            usedCard.use(hero, enemy, publisher);
        }
        hero.loseEnergy(usedCard.cost);
        hand.remove(index);
        discardPile.add(usedCard);
    }

    /**
     * Descarta todas as cartas atualmente na mão, enviando-as para a pilha de descarte,
     * e limpa a mão do jogador (geralmente usado no fim do turno).
     */
    public void clearHand() {
        discardPile.addAll(hand);
        hand.clear();
    }
}