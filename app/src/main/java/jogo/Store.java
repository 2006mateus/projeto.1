package jogo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o evento de Loja (Store) no jogo.
 * <p>
 * A loja permite que o herói utilize seu ouro acumulado para adquirir novas cartas 
 * para o baralho ou venda cartas existentes (removendo-as do deck) para obter ouro extra.
 * </p>
 * * @author [Seu Nome/Equipe]
 * @version 1.0
 */
public class Store extends Evento {
    
    /** Lista de cartas disponíveis para venda nesta instância específica da loja. */
    private ArrayList<Cards> stock = new ArrayList<>();

    /**
     * Construtor da Loja que inicializa o estoque com itens variados.
     * * @param itensVender Sequência de objetos {@link Cards} que comporão o estoque inicial.
     */
    public Store(Cards... itensVender) {
        for (Cards c : itensVender){
            this.stock.add(c);
        }
    }

    /**
     * Adiciona uma nova carta ao estoque atual da loja.
     * * @param card O objeto {@link Cards} a ser disponibilizado para venda.
     */
    public void addCardToStock(Cards card) {
        stock.add(card);
    }

    /**
     * Inicia a interface de interação com a loja.
     * <p>
     * Apresenta um menu principal onde o jogador pode escolher entre comprar, 
     * vender ou sair. O método permanece em loop até que a opção de saída seja selecionada.
     * </p>
     * * @param explorer   O herói que realizará as transações.
     * @param inimigo    Referência ao inimigo (geralmente {@code null} neste evento).
     * @param deckSystem O gerenciador de baralho para adicionar ou remover cartas.
     * @param reader     Scanner para leitura das opções do menu.
     * @param p          Publicador de eventos (não utilizado diretamente aqui).
     * @param fileTxt    Caminho para arquivo de arte ASCII da loja.
     * @return {@code true} ao finalizar a visita à loja.
     */
    @Override
    public boolean start(Hero explorer, Enemy inimigo, CardsManager deckSystem, Scanner reader, Publisher p, String fileTxt) {
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n--- [LOJA] ---");
            System.out.println("Seu Ouro: " + explorer.getGold() + "g");
            System.out.println("O que deseja fazer?");
            System.out.println("1. Comprar Cartas");
            System.out.println("2. Vender Cartas (Remover do seu baralho)");
            System.out.println("3. Sair da Loja");

            String choice = reader.next();

            switch (choice) {
                case "1":
                    buyMenu(explorer, deckSystem, reader);
                    break;
                case "2":
                    sellMenu(explorer, deckSystem, reader);
                    break;
                case "3":
                    System.out.println("Obrigado pela visita! Boa sorte na jornada.");
                    shopping = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        return true;
    }

    /**
     * Gerencia o menu de compra de cartas.
     * <p>
     * Verifica se o herói possui ouro suficiente com base no {@code price} da carta.
     * Caso a compra seja bem-sucedida, a carta é movida do estoque da loja para o 
     * baralho do herói.
     * </p>
     * * @param explorer   O herói comprador.
     * @param deckSystem O sistema de gerenciamento de cartas.
     * @param reader     Scanner para seleção do item.
     */
    private void buyMenu(Hero explorer, CardsManager deckSystem, Scanner reader) {
        if (stock.isEmpty()) {
            System.out.println("O estoque está vazio!");
            return;
        }

        System.out.println("\n--- ITENS À VENDA ---");
        for (int i = 0; i < stock.size(); i++) {
            Cards c = stock.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " [" + c.getPrice() + "g]");
        }
        System.out.println((stock.size() + 1) + ". Voltar");

        int option = reader.nextInt();
        if (option > 0 && option <= stock.size()) {
            Cards chosen = stock.get(option - 1);
            if (explorer.getGold() >= chosen.getPrice()) {
                explorer.loseGold(chosen.getPrice());
                deckSystem.addCard(chosen);
                stock.remove(chosen); // Remove da loja após comprar
                System.out.println("Você comprou: " + chosen.getName());
            } else {
                System.out.println("Ouro insuficiente!");
            }
        }
    }

    /**
     * Gerencia o menu de venda de cartas do jogador.
     * <p>
     * Permite que o jogador remova cartas indesejadas do seu deck em troca de 
     * 50% do valor de compra da carta. Útil para "limpar" o baralho de cartas fracas.
     * </p>
     * * @param explorer   O herói que receberá o ouro da venda.
     * @param deckSystem O sistema de gerenciamento de cartas para remoção do deck.
     * @param reader     Scanner para seleção da carta a vender.
     */
    private void sellMenu(Hero explorer, CardsManager deckSystem, Scanner reader) {
        ArrayList<Cards> playerDeck = deckSystem.getDeck();
        
        if (playerDeck.isEmpty()) {
            System.out.println("Você não tem cartas para vender!");
            return;
        }

        System.out.println("\n--- SEU BARALHO (Venda por 50% do valor) ---");
        for (int i = 0; i < playerDeck.size(); i++) {
            Cards c = playerDeck.get(i);
            int sellValue = c.getPrice() / 2;
            System.out.println((i + 1) + ". " + c.getName() + " [Ganha " + sellValue + "g]");
        }
        System.out.println((playerDeck.size() + 1) + ". Voltar");

        int option = reader.nextInt();
        if (option > 0 && option <= playerDeck.size()) {
            Cards sold = playerDeck.get(option - 1);
            int profit = sold.getPrice() / 2;
            
            explorer.gainGold(profit);
            deckSystem.removeCard(sold);
            System.out.println("Vendeu " + sold.getName() + " por " + profit + "g.");
        }
    }
}