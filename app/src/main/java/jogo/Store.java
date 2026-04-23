package jogo;

import java.util.ArrayList;
import java.util.Scanner;

public class Store extends Evento {
    // Lista de cartas que a loja tem para vender nesta visita
    private ArrayList<Cards> stock = new ArrayList<>();

    public Store(Cards... itensVender) {
        for (Cards c : itensVender){
            this.stock.add(c);
        }
    }

    /**
     * Adiciona uma carta ao estoque da loja com um preço definido.
     * Dica: Você precisará garantir que a classe Card tenha um atributo 'price'.
     */
    public void addCardToStock(Cards card) {
        stock.add(card);
    }

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

    private void sellMenu(Hero explorer, CardsManager deckSystem, Scanner reader) {
        // Aqui você acessa as cartas que o jogador já tem
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
            deckSystem.removeCard(sold); // Você precisará desse método no CardsManager
            System.out.println("Vendeu " + sold.getName() + " por " + profit + "g.");
        }
    }
}
