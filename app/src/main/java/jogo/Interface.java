package jogo;

import java.util.Scanner;

/**
 * Classe que gerencia o fluxo de execução do jogo.
 * <p>
 * Responsável por inicializar os personagens (herói e inimigo), gerenciar o sistema
 * de cartas através do {@link CardsManager}, processar os turnos de combate e
 * capturar as entradas do usuário via console.
 * </p>
 */
public class Interface {

    /**
     * Ponto de entrada do aplicativo. Contém o loop principal de combate.
     * * @param args Argumentos de linha de comando 
     */
    public static void run(Hero explorador, Enemy inimigo, Scanner scanner, CardsManager deckSystem, Publisher publisher, String fileTxt) {
        int commands = -1;
        boolean playing = true;
        /** Limite máximo de cartas permitidas na mão do jogador. */
        final int MAX_CARTAS = 4;

        // Embaralhar antes de começar
        deckSystem.recycleDeck();

        /**
         * Loop principal do combate.
         * O jogo continua enquanto 'playing' for verdadeiro e o herói estiver vivo.
         */
        while (playing) {
            ConsoleUI.clearScreen();

            explorador.setShield(0);
            explorador.setEnergy(10);
            System.out.println("====================");
            System.out.println(inimigo.getName() + " irá atacar causando " + inimigo.getDamage() + " de dano");
            System.out.println("Selecione 1 para comprar cartas ou 2 para não. " + deckSystem.getQuantityDeck() + " restantes");

            commands = scanner.nextInt();

            if (commands < 1 || commands > 2) {
                System.out.println("Opção inválida, escolha novamente!");
                continue;
            }

            // Lógica de compra de cartas
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

            // Sub-loop do turno do jogador (uso de energia)
            while (commands != 0 && explorador.getEnergy() != 0) {
                exibirStatus(explorador, inimigo, fileTxt);

                System.out.println(ConsoleUI.YELLOW + explorador.getEnergy() + "/10 de Energia disponivel" + ConsoleUI.RESET);
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

            if (!inimigo.isAlive()) break;
            if (commands == 0) {
                System.out.println(explorador.getName() + " saiu do jogo!");
                System.exit(0);
                break;
            }

            // Turno do Inimigo
            executarTurnoInimigo(inimigo, explorador, publisher, fileTxt);

            if (!explorador.isAlive()) {
                playing = false;
                System.out.println(explorador.getName() + " foi derrotado!");
                break;
            }
            deckSystem.clearHand();
        }
    }

    /**
     * Exibe no console os pontos de vida e escudo dos combatentes.
     */
    public static void exibirStatus(Hero h, Enemy e, String fileTxt) {
        // Limpa a tela toda vez que os status forem mostrados para não poluir o terminal
        ConsoleUI.clearScreen();
        
        ConsoleUI.printAsciiArt(fileTxt);

        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀             " + ConsoleUI.RED + e.getName() + " (" + e.getHealth() + "/" + e.MAX_HEALTH + ")" + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                      " + ConsoleUI.BLUE + h.getName() + " (" + h.getHealth() + "/100) Escudo: (" + h.getShield() + "/20)" + ConsoleUI.RESET);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Gerencia a interface de escolha e uso de cartas da mão do jogador.
     */
    public static void processarUsoDeCarta(CardsManager ds, Hero h, Enemy e, Publisher p, Scanner s, String fileTxt) {
        if (ds.emptyDeck()) {
            System.out.println("Mão vazia!");
            ConsoleUI.pause(1000);
        } else {
            ds.printHand();
            System.out.println("Selecione o número da carta ou 0 para fechar");
            int choice = s.nextInt();
            if (choice != 0) {
                ds.useCard(choice - 1, h, e, p);
            }
        }
    }

    /**
     * Executa as ações do inimigo e notifica efeitos de status.
     */
    public static void executarTurnoInimigo(Enemy e, Hero h, Publisher p, String fileTxt) {
        ConsoleUI.clearScreen();
        ConsoleUI.printAsciiArt(fileTxt); // Imprime o desenho do rato gigante
        
        System.out.println(ConsoleUI.RED + "--- Turno do Inimigo ---" + ConsoleUI.RESET);
        System.out.println(e.getName() + " atacou ferozmente!");
        
        e.atack(h, p);
        p.notifySubscribers();
        
        // Pausa por 2 segundos (4000 milissegundos) para o jogador conseguir ler o dano
        ConsoleUI.pause(3000); 
    }
}