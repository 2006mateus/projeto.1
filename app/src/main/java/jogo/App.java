package jogo;

import java.util.Scanner;
import javax.swing.tree.DefaultMutableTreeNode;

public class App {
    
    // Corrigido: adicionado o static
    public static void main(String[] args){

        String nome;

        Scanner reader = new Scanner(System.in);
        CardsManager deckSystem = new CardsManager();
        Publisher publisher = new Publisher();

        System.out.println("Digite o nome de seu heroi:");
        nome = reader.next();
        
        Hero explorador = new Hero(nome, 100, 0, 10, 20);
        
        Enemy rato = new Enemy("Rato de academia", 5, 0, 20, 0);
        Enemy urso = new Enemy("Urso", 1, 0, 30, 0);
        Enemy cabra = new Enemy("Cabra", 7, 0, 10, 0);
        Enemy kanye = new Enemy("GOAT", 1, 0, 30, 0);
        Enemy cobra = new Enemy("Cobra", 5, 0, 30, 0);
        Enemy macaco = new Enemy("Macaco", 1, 0, 20, 0);
        Enemy caraMal = new Enemy("Luquinhas", 2, 0, 40, 0);

        Map gameMap = new Map();
        gameMap.organizeMap(rato, urso, cabra, kanye, cobra, macaco, caraMal);

        DefaultMutableTreeNode salaAtual = gameMap.entrada;
        
        boolean result = true;

        while (result){

            Sala salaAtualDados = (Sala) salaAtual.getUserObject();

            System.out.println("\n-----------------------------------------");
            System.out.println("Você está em: " + salaAtualDados.getNome());
            
            // Verifica se tem monstro na sala e chama a sua classe Battle!
            if (salaAtualDados.getInimigo() != null) {

                result = Battle.startBattle(explorador, salaAtualDados.getInimigo(), deckSystem, reader, publisher, salaAtualDados.getFileTxt());
                
                // Se a batalha retornou false (derrota), encerra o jogo
                if (!result) {
                    break;
                }
            }

            // Verifica se chegou na última sala do chefe (uma folha da árvore)
            if (salaAtual.isLeaf()) {
                System.out.println("\nVOCÊ ZEROU O JOGO! Parabéns!");
                break;
            }

            System.out.println("\nEscolha seu caminho:");
            int numeroDePortas = salaAtual.getChildCount();

            // Mostra os caminhos possíveis dinamicamente
            for (int i = 0; i < numeroDePortas; i++) {
                DefaultMutableTreeNode porta = (DefaultMutableTreeNode) salaAtual.getChildAt(i);
                Sala salaDaPorta = (Sala) porta.getUserObject();
                System.out.println("Digite " + (i + 1) + " para ir para " + salaDaPorta.getNome());
            }

            int choice = reader.nextInt();

            if (choice >= 1 && choice <= numeroDePortas) {
                // Avança para o nó que o jogador escolheu
                salaAtual = (DefaultMutableTreeNode) salaAtual.getChildAt(choice - 1);
            } else {
                System.out.println("Opção inválida! Escolha um dos caminhos.");
            }
        }

        reader.close();
    }
}