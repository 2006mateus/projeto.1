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
        
        // primeiros inimigos
        Enemy rato = new Enemy("Rato de academia", 20, 0, 15, 0);
        Enemy cabra = new Enemy("Cabra", 25, 0, 10, 0);
        Enemy cobra = new Enemy("Cobra", 30, 0, 20, 0);

        // inimigos intermediários
        Enemy urso = new Enemy("Urso", 50, 0, 25, 0);
        Enemy kanye = new Enemy("GOAT", 55, 0, 25, 0);
        Enemy macaco = new Enemy("Macaco", 45, 0, 20, 0);

        // boss
        Enemy elefante = new Enemy("Elefante", 120, 0, 35, 0);

        /** Limite máximo de cartas permitidas na mão do jogador. */

        // Criação do catálogo de cartas disponíveis
        DamageCard bastao = new DamageCard("bastao", "Um bastao enferrujado, ele aparenta estar bem proximo de quebrar.", 3, 10);
        DamageCard faca = new DamageCard("faca", "Uma faca de cozinha comum, provavelmente já foi muito utilizada na cozinha", 4, 12);
        VenomCard Dardo = new VenomCard("Dardo", "veneno de dardo", "Um dardo de caça proveniente de tribos da regiao, aparenta ser venenoso.", 5, 2, 5, 3);
        StrengthCard oculos = new StrengthCard("oculos velhos", "foco", "Um oculos de grau danificado, apesar de sua aparencia funciona perfeitamente...", 1, 5, 1);
        DamageCard pistola = new DamageCard("pistola", "uma pistola praticamente emperrada, contém apenas uma bala", 5, 15);
        ShieldCard luva = new ShieldCard("luva velha", "Uma luva velha, aparenta ter sido para algum esporte ha muito tempo.", 3, 10);
        ShieldCard capacete = new ShieldCard("capacete", "Um capacete de construção encontrado em uma obra", 4, 15);
        ShieldCard colete = new ShieldCard("colete", "um colete a prova de balas remendado", 5, 20);
        HealingCard bandagem = new HealingCard("bandagem", "Uma bandagem relativamente suja", 2, 12);
        HealingCard medkit = new HealingCard("medkit", "Um kit médico quebrado, ainda deve servir", 5, 30);
        PassiveHealingCard injecao = new PassiveHealingCard("injecao", "analgesico", "uma injecao de analgesico, parece que pode ajudar", 3, 5, 3);


        // Populando o baralho inicial
        for (int i = 0; i < 2; i += 1) {
            deckSystem.addCard(luva);
            deckSystem.addCard(faca);
            deckSystem.addCard(bastao);
            deckSystem.addCard(capacete);
            deckSystem.addCard(Dardo);
            deckSystem.addCard(oculos);
            deckSystem.addCard(pistola);
            deckSystem.addCard(colete);
            deckSystem.addCard(bandagem);
            deckSystem.addCard(medkit);
            deckSystem.addCard(injecao);
        }

        Map gameMap = new Map();
        gameMap.organizeMap(rato, urso, cabra, kanye, cobra, macaco, elefante);

        DefaultMutableTreeNode salaAtual = gameMap.entrada;
        
        boolean result = true;

        while (result){

            Sala salaAtualDados = (Sala) salaAtual.getUserObject();

            System.out.println("\n-----------------------------------------");
            System.out.println("Você está em: " + salaAtualDados.getNome());
            ConsoleUI.pause(1000);
            
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
                ConsoleUI.pause(1000);
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
                ConsoleUI.pause(1000);
            }
        }

        reader.close();
    }
}