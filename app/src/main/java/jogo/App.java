package jogo;

import java.util.Scanner;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe principal que orquestra a execução do jogo.
 * <p>
 * Esta classe é responsável por inicializar os componentes do sistema, 
 * configurar o herói, os inimigos, o catálogo de cartas e gerenciar o loop 
 * principal de gameplay, incluindo a navegação entre salas e o início de batalhas.
 * </p>
 */
public class App {
    
    /**
     * Ponto de entrada do aplicativo.
     * <p>
     * O método realiza as seguintes etapas:
     * 1. Solicita o nome do Herói ao usuário.
     * 2. Instancia os inimigos e o catálogo de cartas (Dano, Cura, Escudo, Veneno, Força).
     * 3. Configura o baralho inicial (deckSystem) e o mapa do jogo.
     * 4. Gerencia o fluxo de navegação através de uma estrutura de árvore (DefaultMutableTreeNode).
     * 5. Aciona o sistema de combate em salas que possuem inimigos.
     * </p>
     * * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args){

        String nome;

        Scanner reader = new Scanner(System.in);
        CardsManager deckSystem = new CardsManager();
        Publisher publisher = new Publisher();

        System.out.println("Digite o nome de seu heroi:");
        nome = reader.next();
        
        // Inicialização do Herói com atributos: Nome, HP, XP, Atk base, Def base.
        Hero explorador = new Hero(nome, 100, 0, 10, 20, 10);
        
        // Definição do bestiário (Inimigos iniciais, intermediários e o Boss Final)
        Enemy rato = new Enemy("Rato de academia", 20, 0, 15, 0);
        Enemy cabra = new Enemy("Cabra", 25, 0, 10, 0);
        Enemy cobra = new Enemy("Cobra", 30, 0, 20, 0);

        Enemy urso = new Enemy("Urso", 50, 0, 25, 0);
        Enemy kanye = new Enemy("GOAT", 55, 0, 25, 0);
        Enemy macaco = new Enemy("Macaco", 45, 0, 20, 0);

        Enemy elefante = new Enemy("Elefante", 120, 0, 35, 0);

        // Instanciação das cartas de diferentes tipos para o inventário/baralho
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

        // Preenchimento do baralho inicial do jogador com cópias das cartas disponíveis
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

        // Organização da estrutura lógica do mapa (árvore de salas)
        Map gameMap = new Map();
        gameMap.organizeMap(rato, urso, cabra, kanye, cobra, macaco, elefante);

        // Apontador para a posição atual do jogador na árvore do mapa
        DefaultMutableTreeNode salaAtual = gameMap.entrada;
        
        boolean result = true;

        /**
         * Loop principal do jogo.
         * Continua enquanto o jogador estiver vivo (result == true) 
         * ou até atingir o estado final (folha da árvore).
         */
        while (result){

            Sala salaAtualDados = (Sala) salaAtual.getUserObject();

            System.out.println("\n-----------------------------------------");
            System.out.println("Você está em: " + salaAtualDados.getNome());
            ConsoleUI.pause(1000);
            
            // Lógica de Combate: Acionada se a sala possuir um inimigo instanciado
            if (salaAtualDados.getInimigo() != null) {

                result = Battle.start(explorador, salaAtualDados.getInimigo(), deckSystem, reader, publisher, salaAtualDados.getFileTxt());
                
                // Se a batalha resultar em derrota, encerra o loop
                if (!result) {
                    break;
                }
            }

            // Condição de Vitória: O jogador chegou a uma sala sem saídas (Folha)
            if (salaAtual.isLeaf()) {
                System.out.println("\nVOCÊ ZEROU O JOGO! Parabéns!");
                break;
            }

            // Lógica de Navegação: Apresenta as opções de caminhos (nós filhos)
            System.out.println("\nEscolha seu caminho:");
            int numeroDePortas = salaAtual.getChildCount();

            for (int i = 0; i < numeroDePortas; i++) {
                ConsoleUI.pause(1000);
                DefaultMutableTreeNode porta = (DefaultMutableTreeNode) salaAtual.getChildAt(i);
                Sala salaDaPorta = (Sala) porta.getUserObject();
                System.out.println("Digite " + (i + 1) + " para ir para " + salaDaPorta.getNome());
            }

            int choice = reader.nextInt();

            // Validação de entrada e atualização do nó atual
            if (choice >= 1 && choice <= numeroDePortas) {
                salaAtual = (DefaultMutableTreeNode) salaAtual.getChildAt(choice - 1);
            } else {
                System.out.println("Opção inválida! Escolha um dos caminhos.");
                ConsoleUI.pause(1000);
            }
        }

        reader.close();
    }
}