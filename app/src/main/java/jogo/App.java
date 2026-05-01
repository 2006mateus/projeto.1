package jogo;

import java.util.Scanner;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe principal que orquestra a execução do jogo.
 * <p>
 * Esta classe é o ponto central (entry point) do sistema, responsável por:
 * <ul>
 * <li>Inicializar os componentes globais (Scanner, CardsManager, Publisher).</li>
 * <li>Configurar as entidades (Herói e Inimigos) e o catálogo de cartas.</li>
 * <li>Construir a estrutura do mapa e gerenciar o fluxo de navegação entre salas.</li>
 * <li>Controlar o ciclo de vida da partida (Combate, Eventos e Condição de Vitória).</li>
 * </ul>
 * </p>
 * @version 1.0
 */
public class App {
    
    /**
     * Ponto de entrada do aplicativo (Método Main).
     * <p>
     * O método executa o fluxo principal divido em:
     * 1. <b>Setup:</b> Inicializa o herói com atributos base (HP, Energia, Gold) e cria o bestiário.
     * 2. <b>Deck Building:</b> Preenche o baralho do jogador com cartas de diferentes categorias.
     * 3. <b>World Building:</b> Invoca o {@link Map} para organizar as salas em uma estrutura de árvore.
     * 4. <b>Game Loop:</b> Mantém o jogador em um ciclo de navegação, processando eventos e batalhas 
     * até que a vida do herói chegue a zero ou o boss final seja derrotado.
     * </p>
     * * @param args Argumentos de linha de comando (não utilizados neste projeto).
     */
    public static void main(String[] args){

        String nome;

        // Inicialização de utilitários de sistema e gerenciadores
        Scanner reader = new Scanner(System.in);
        CardsManager deckSystem = new CardsManager();
        Publisher publisher = new Publisher();
        Battle battle = new Battle();

        System.out.println("Digite o nome de seu heroi:");
        nome = reader.next();
        
        /** * Inicialização do Herói.
         * Parâmetros: Nome, HP, Shield, Energy, MaxShield, MaxEnergy, Gold.
         */
        Hero explorador = new Hero(nome, 100, 0, 10, 20, 10, 0);
        
        /** * Definição do bestiário.
         * Cria instâncias de inimigos para serem distribuídas nas salas do mapa.
         */
        Enemy rato = new Enemy("Rato de academia", 20, 0, 15, 0);
        Enemy cabra = new Enemy("Cabra", 25, 0, 10, 0);
        Enemy cobra = new Enemy("Cobra", 30, 0, 20, 0);
        Enemy urso = new Enemy("Urso", 50, 0, 25, 0);
        Enemy kanye = new Enemy("GOAT", 55, 0, 25, 0);
        Enemy macaco = new Enemy("Macaco", 45, 0, 20, 0);
        Enemy elefante = new Enemy("Elefante", 120, 0, 35, 0);

        /**
         * Criação do catálogo de cartas.
         * Inclui cartas de Dano, Veneno, Força, Escudo e Cura.
         */
        DamageCard bastao = new DamageCard("bastao", "Um bastao enferrujado...", 3, 10, 30);
        DamageCard faca = new DamageCard("faca", "Uma faca de cozinha comum...", 4, 12, 40);
        VenomCard Dardo = new VenomCard("Dardo", "veneno de dardo", "Dardo de caça...", 5, 2, 5, 3, 40);
        StrengthCard oculos = new StrengthCard("oculos velhos", "foco", "Oculos de grau...", 1, 5, 1, 40);
        DamageCard pistola = new DamageCard("pistola", "pistola emperrada...", 5, 15, 30);
        ShieldCard luva = new ShieldCard("luva velha", "Luva de esporte...", 3, 10, 30);
        ShieldCard capacete = new ShieldCard("capacete", "Capacete de construção...", 4, 15, 20);
        ShieldCard colete = new ShieldCard("colete", "Colete remendado...", 5, 20, 30);
        HealingCard bandagem = new HealingCard("bandagem", "Bandagem suja...", 2, 12, 40);
        HealingCard medkit = new HealingCard("medkit", "Kit médico quebrado...", 5, 30, 40);
        PassiveHealingCard injecao = new PassiveHealingCard("injecao", "analgesico", "Injeção de analgésico...", 3, 5, 3, 40);

        // Cartas exclusivas para o inventário da loja
        DamageCard machado = new DamageCard("Machado de Batalha", "Lâmina dupla devastadora.", 6, 25, 80);
        ShieldCard escudoTatico = new ShieldCard("Escudo Tático", "Policarbonato resistente.", 5, 30, 80);
        HealingCard soro = new HealingCard("Soro Militar", "Biotecnologia regenerativa.", 4, 40, 100);

        /**
         * Preenchimento do baralho inicial.
         * Adiciona cópias das cartas instanciadas ao sistema de gerenciamento de deck.
         */
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

        /**
         * Inicialização do Mapa.
         * Constrói a árvore de salas e posiciona os inimigos e itens nos nós.
         */
        Map gameMap = new Map();
        gameMap.organizeMap(rato, urso, cabra, kanye, cobra, macaco, elefante, machado, escudoTatico, soro);

        // Define o ponto de partida do jogador (raiz da árvore)
        DefaultMutableTreeNode salaAtual = gameMap.entrada;
        
        boolean result = true;

        /**
         * Ciclo de Vida Principal (Game Loop).
         * O loop é interrompido se o herói morrer ou se o jogador vencer o Boss final.
         */
        while (result){

            Sala salaAtualDados = (Sala) salaAtual.getUserObject();

            System.out.println("\n-----------------------------------------");
            System.out.println("Você está em: " + salaAtualDados.getNome());
            ConsoleUI.pause(1000);
            
            /** * Processamento de Eventos.
             * Verifica se a sala contém um evento não-combate (ex: Loja, Fogueira).
             */
            if (salaAtualDados.getEvent() != null) {
                result = salaAtualDados.getEvent().start(explorador, null, deckSystem, reader, publisher, "");
            }
            
            /** * Processamento de Combate.
             * Acionado se a sala possuir um inimigo. A batalha define o valor de 'result'.
             */
            if (salaAtualDados.getInimigo() != null) {

                result = battle.start(explorador, salaAtualDados.getInimigo(), deckSystem, reader, publisher, salaAtualDados.getFileTxt());
                
                // Encerra o loop em caso de derrota
                if (!result) {
                    break;
                } else {
                    battle.recompensa(explorador);
                }
            }

            /** * Verificação de Condição de Vitória.
             * Uma sala folha (leaf) sem filhos representa o fim do jogo.
             */
            if (salaAtual.isLeaf()) {
                System.out.println("\nVOCÊ ZEROU O JOGO! Parabéns!");
                break;
            }

            /** * Gerenciamento de Navegação.
             * Lista os nós filhos da sala atual para o jogador escolher o próximo destino.
             */
            System.out.println("\nEscolha seu caminho:");
            int numeroDePortas = salaAtual.getChildCount();

            for (int i = 0; i < numeroDePortas; i++) {
                ConsoleUI.pause(1000);
                DefaultMutableTreeNode porta = (DefaultMutableTreeNode) salaAtual.getChildAt(i);
                Sala salaDaPorta = (Sala) porta.getUserObject();
                System.out.println("Digite " + (i + 1) + " para ir para " + salaDaPorta.getNome());
            }

            // Captura da escolha do usuário e movimentação na árvore
            int choice = reader.nextInt();

            if (choice >= 1 && choice <= numeroDePortas) {
                salaAtual = (DefaultMutableTreeNode) salaAtual.getChildAt(choice - 1);
            } else {
                System.out.println("Opção inválida! Escolha um dos caminhos.");
                ConsoleUI.pause(1000);
            }
        }

        // Encerramento do recurso de leitura
        reader.close();
    }
}