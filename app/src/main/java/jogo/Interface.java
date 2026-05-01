package jogo;

import java.util.Scanner;

/**
 * Motor de interface e controle de fluxo do combate.
 * <p>
 * Esta classe gerencia o ciclo de vida de uma batalha, incluindo a lógica de turnos, 
 * o processamento de entradas do usuário via console, a exibição de status visual 
 * (com suporte a artes ASCII e cores) e a coordenação entre o {@link Hero}, 
 * o {@link Enemy} e o {@link CardsManager}.
 * </p>
 * @version 1.0
 */
public class Interface {

    /**
     * Executa o loop principal de uma partida de combate até que haja uma condição de vitória, 
     * derrota ou saída voluntária.
     * <p>
     * O fluxo de cada rodada segue esta ordem:
     * <ol>
     * <li><b>Início de Turno:</b> Reset de escudo e restauração de energia do Herói.</li>
     * <li><b>Fase de Compra:</b> O jogador escolhe até {@value #MAX_CARTAS} cartas da seleção disponível.</li>
     * <li><b>Fase de Ação:</b> O jogador abre a mão e utiliza cartas enquanto houver energia.</li>
     * <li><b>Fase do Inimigo:</b> O oponente executa sua ação e os efeitos de status (veneno, etc) são processados via {@link Publisher}.</li>
     * </ol>
     * </p>
     * * @param explorador  O objeto do herói controlado pelo jogador.
     * @param inimigo     O inimigo atual instanciado para a batalha.
     * @param scanner     Scanner para captura de comandos e escolhas do usuário.
     * @param deckSystem  Gerenciador das listas de cartas (deck, mão, descarte e compra).
     * @param publisher   Sistema de eventos para processar e notificar efeitos de status.
     * @param fileTxt     Caminho ou nome do arquivo .txt contendo a arte ASCII do inimigo.
     */
    public static void run(Hero explorador, Enemy inimigo, Scanner scanner, CardsManager deckSystem, Publisher publisher, String fileTxt) {
        int commands = -1;
        boolean playing = true;
        
        /** Limite máximo de cartas permitidas na mão do jogador simultaneamente. */
        final int MAX_CARTAS = 4;

        // Garante que o baralho esteja pronto antes do início
        deckSystem.recycleDeck();

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

            // Loop de ações do Jogador
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
     * Atualiza o console com as informações vitais da batalha em tempo real.
     * <p>
     * O método limpa a tela, imprime a arte ASCII do inimigo e formata uma linha de status 
     * contendo o Nome e HP do inimigo, bem como Nome, HP e Escudo atual do herói.
     * </p>
     * * @param h       O objeto do herói para extração de atributos.
     * @param e       O objeto do inimigo para extração de atributos.
     * @param fileTxt Nome do arquivo contendo a arte visual da sala/inimigo.
     */
    public static void exibirStatus(Hero h, Enemy e, String fileTxt) {
        ConsoleUI.clearScreen();
        ConsoleUI.printAsciiArt(fileTxt);

        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀             " + ConsoleUI.RED + e.getName() + " (" + e.getHealth() + "/" + e.MAX_HEALTH + ")" + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                      " + ConsoleUI.BLUE + h.getName() + " (" + h.getHealth() + "/100) Escudo: (" + h.getShield() + "/20)" + ConsoleUI.RESET);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Gerencia o subset de interface para o uso de cartas da mão do jogador.
     * <p>
     * Exibe as cartas disponíveis e processa a escolha numérica do usuário. 
     * Se a carta for válida, delega o efeito para o método {@link CardsManager#useCard}.
     * </p>
     * * @param ds      Gerenciador de cartas para acesso à mão e lógica de uso.
     * @param h       O herói que está utilizando a carta.
     * @param e       O inimigo alvo da carta.
     * @param p       O publicador para notificação de gatilhos de cartas.
     * @param s       Scanner para leitura da escolha da carta.
     * @param fileTxt Nome do arquivo de arte (utilizado para consistência visual).
     */
    public static void processarUsoDeCarta(CardsManager ds, Hero h, Enemy e, Publisher p, Scanner s, String fileTxt) {
        if (ds.getQuantityHand() == 0) {
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
     * Processa a fase de ataque do inimigo e a atualização de efeitos de status globais.
     * <p>
     * Este método realiza três ações críticas:
     * <ol>
     * <li>Sinaliza visualmente a mudança de turno.</li>
     * <li>Invoca o ataque do inimigo contra o herói.</li>
     * <li>Notifica os inscritos do {@link Publisher} para resolver efeitos recorrentes 
     * (como dano de veneno ou expiração de buffs).</li>
     * </ol>
     * </p>
     * * @param e       O inimigo que está realizando a ação.
     * @param h       O herói que receberá o ataque.
     * @param p       O publicador responsável pela manutenção dos efeitos de status.
     * @param fileTxt Nome do arquivo de arte para redesenhar o cenário.
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