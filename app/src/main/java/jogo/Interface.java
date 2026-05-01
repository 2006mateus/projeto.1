package jogo;

import java.util.Scanner;

/**
 * Motor de interface e controle de fluxo do combate.
 * <p>
 * Esta classe gerencia a lógica de turnos, o processamento de entradas do usuário,
 * a exibição de status visual (incluindo artes ASCII) e a interação entre 
 * o herói, o inimigo e o sistema de cartas (CardsManager).
 * </p>
 */
public class Interface {

    /**
     * Executa o loop principal de uma partida de combate.
     * <p>
     * O fluxo segue a seguinte ordem:
     * 1. Reset de atributos temporários (Energia e Escudo) do Herói.
     * 2. Fase de Compra: O jogador seleciona cartas do deck para sua mão.
     * 3. Fase de Ação: O jogador utiliza cartas consumindo energia.
     * 4. Fase do Inimigo: O oponente ataca e os efeitos de status (Publisher) são processados.
     * </p>
     * * @param explorador O objeto do herói controlado pelo jogador.
     * @param inimigo    O inimigo atual na sala.
     * @param scanner    Scanner para captura de comandos no console.
     * @param deckSystem Gerenciador das listas de cartas (deck, mão, descarte).
     * @param publisher  Sistema de eventos para processar efeitos como veneno ou força.
     * @param fileTxt    Caminho do arquivo de texto com a arte ASCII do inimigo.
     */
    public static void run(Hero explorador, Enemy inimigo, Scanner scanner, CardsManager deckSystem, Publisher publisher, String fileTxt) {
        int commands = -1;
        boolean playing = true;
        /** Limite máximo de cartas permitidas na mão do jogador. */
        final int MAX_CARTAS = 4;

        // Garante que o baralho esteja pronto antes do início
        deckSystem.recycleDeck();

        /**
         * Loop principal do combate.
         * Mantém o jogo ativo enquanto ambos os combatentes estiverem vivos e o jogador não sair.
         */
        while (playing) {
            
            if (!inimigo.isAlive() || !explorador.isAlive()) {
                break;
            }
            ConsoleUI.clearScreen();

            // Lógica de início de turno: Reset de escudo e energia
            explorador.setShield(0);
            explorador.setEnergy(explorador.getMaxEnergy());
            System.out.println("====================");
            System.out.println(inimigo.getName() + " irá atacar causando " + inimigo.getDamage() + " de dano");
            System.out.println("Selecione 1 para comprar cartas ou 2 para não. " + deckSystem.getQuantityDeck() + " restantes");

            commands = scanner.nextInt();

            if (commands < 1 || commands > 2) {
                System.out.println("Opção inválida, escolha novamente!");
                continue;
            }

            // Gerenciamento da Fase de Compra (Draft de cartas)
            if (commands == 1) {
                deckSystem.moveToPurchasable();
                deckSystem.printPurchasable();
                while (true) {
                    if (deckSystem.getPurchasableQuantity() == 0) {
                        System.out.println("Não há mais cartas disponíveis para compra nesta rodada.");
                        break; 
                    }
                    System.out.println("Selecione o número da carta para comprar ou 0 para sair");
                    int num = scanner.nextInt();
                    if (deckSystem.getQuantityHand() == MAX_CARTAS) {
                        System.out.println("Máximo de cartas na mão atingida!");
                        break;
                    }
                    if (num == 0) break;
                    deckSystem.buyCard(num);
                }
                deckSystem.clearPurchasableAndShuffle();
            }

            // Loop de ações do Jogador: Ocorre enquanto houver energia ou o jogador não encerrar turno
            while (commands != 0 && explorador.getEnergy() != 0) {
                exibirStatus(explorador, inimigo, fileTxt);

                System.out.println(ConsoleUI.YELLOW + explorador.getEnergy() + "/" + explorador.getMaxEnergy() + " de Energia disponivel" + ConsoleUI.RESET);
                System.out.println(ConsoleUI.GREEN + "1 - Abrir mão. " + deckSystem.getQuantityHand() + " cartas" + ConsoleUI.RESET);
                System.out.println(ConsoleUI.GREEN + "2 - Encerrar turno" + ConsoleUI.RESET);
                System.out.println(ConsoleUI.GREEN + "0 - Sair do jogo" + ConsoleUI.RESET);

                commands = scanner.nextInt();

                if (commands < 0 || commands > 2) {
                    System.out.println("Opção inválida, escolha novamente!");
                    continue;
                }

                if (commands == 1) {
                    processarUsoDeCarta(deckSystem, explorador, inimigo, publisher, scanner, fileTxt);
                    if (!inimigo.isAlive()) {
                        System.out.println(explorador.getName() + " conquistou a vitória!");
                        break;
                    }
                } else if (commands == 2) {
                    break;
                }
            }

            // Verificação de saída do jogo ou vitória antes do turno do inimigo
            if (!inimigo.isAlive()) break;
            if (commands == 0) {
                System.out.println(explorador.getName() + " saiu do jogo!");
                System.exit(0);
                break;
            }

            // Execução das ações automáticas do oponente
            executarTurnoInimigo(inimigo, explorador, publisher, fileTxt);

            if (!explorador.isAlive()) {
                playing = false;
                System.out.println(explorador.getName() + " foi derrotado!");
                break;
            }
            
            // Limpa a mão ao fim do turno para a próxima rodada
            deckSystem.clearHand();
        }
    }

    /**
     * Atualiza a tela com as informações visuais da batalha.
     * <p>
     * Exibe a arte ASCII do inimigo e os atributos de Vida/Escudo de ambos os lados.
     * </p>
     * * @param h       O herói para exibição de HP e Escudo.
     * @param e       O inimigo para exibição de HP.
     * @param fileTxt O nome do arquivo contendo a arte ASCII.
     */
    public static void exibirStatus(Hero h, Enemy e, String fileTxt) {
        ConsoleUI.clearScreen();
        ConsoleUI.printAsciiArt(fileTxt);

        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀             " + ConsoleUI.RED + e.getName() + " (" + e.getHealth() + "/" + e.MAX_HEALTH + ")" + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                      " + ConsoleUI.BLUE + h.getName() + " (" + h.getHealth() + "/100) Escudo: (" + h.getShield() + "/20)" + ConsoleUI.RESET);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Intermedeia a escolha de uma carta da mão e sua execução.
     * * @param ds      Gerenciador de cartas.
     * @param h       O usuário da carta (Herói).
     * @param e       O alvo da carta (Inimigo).
     * @param p       O publicador para registro de efeitos.
     * @param s       Scanner para leitura do índice da carta escolhida.
     * @param fileTxt Nome do arquivo de arte (para possível atualização de tela).
     */
    public static void processarUsoDeCarta(CardsManager ds, Hero h, Enemy e, Publisher p, Scanner s, String fileTxt) {
        if (ds.getQuantityHand() == 0) { // Pequena correção lógica na verificação de mão vazia
            System.out.println("Mão vazia!");
            ConsoleUI.pause(1000);
        } else {
            ds.printHand();
            System.out.println("Selecione o número da carta ou 0 para fechar");
            int choice = s.nextInt();
            if (choice != 0 && choice <= ds.getQuantityHand()) {
                ds.useCard(choice - 1, h, e, p);
            }
        }
    }

    /**
     * Processa a IA básica do inimigo e a resolução de efeitos de status.
     * <p>
     * Aplica o dano ao herói e notifica todos os inscritos no {@link Publisher} 
     * para que efeitos como veneno sejam processados após o ataque.
     * </p>
     * * @param e       O inimigo atacante.
     * @param h       O herói defensor.
     * @param p       O publicador para notificação de efeitos.
     * @param fileTxt Nome do arquivo de arte.
     */
    public static void executarTurnoInimigo(Enemy e, Hero h, Publisher p, String fileTxt) {
        ConsoleUI.clearScreen();
        ConsoleUI.printAsciiArt(fileTxt);
        
        System.out.println(ConsoleUI.RED + "--- Turno do Inimigo ---" + ConsoleUI.RESET);
        System.out.println(e.getName() + " atacou ferozmente!");
        
        e.atack(h, p);
        p.notifySubscribers();
        
        ConsoleUI.pause(3000); 
    }
}