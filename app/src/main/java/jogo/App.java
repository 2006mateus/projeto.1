package jogo;

import java.util.Scanner;

/**
 * Classe principal que gerencia o fluxo de execução do jogo.
 * <p>
 * Responsável por inicializar os personagens (herói e inimigo), gerenciar o sistema
 * de cartas através do {@link CardsManager}, processar os turnos de combate e
 * capturar as entradas do usuário via console.
 * </p>
 */
public class App {

    /**
     * Ponto de entrada do aplicativo. Contém o loop principal de combate.
     * * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        int commands = -1;
        boolean playing = true;
        
        /** Limite máximo de cartas permitidas na mão do jogador. */
        final int MAX_CARTAS = 4;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do heroi");
        String name = scanner.nextLine();

        // Inicialização das entidades de combate
        Hero explorador = new Hero(name, 100, 0, 10, 20);
        Enemy rato = new Enemy("rato bebe", 70, 0, 15, 0);

        // Criação do catálogo de cartas disponíveis
        DamageCard bastao = new DamageCard("bastao", "Um bastao enferrujado, ele aparenta estar bem proximo de quebrar.", 3, 10);
        DamageCard faca = new DamageCard("faca", "Uma faca de cozinha comum, provavelmente já foi muito utilizada na cozinha", 4, 12);
        DamageCard Dardo = new DamageCard("Dardo", "Um dardo de caça proveniente de tribos da regiao, aparenta ser venenoso.", 6, 2);
        DamageCard oculos = new DamageCard("oculos velhos", "Um oculos de grau danificado, apesar de sua aparencia funciona perfeitamente...", 5, 0);
        DamageCard pistola = new DamageCard("pistola", "uma pistola praticamente emperrada, contém apenas uma bala", 5, 15);
        ShieldCard luva = new ShieldCard("luva velha", "Uma luva velha, aparenta ter sido para algum esporte ha muito tempo.", 3, 10);
        ShieldCard capacete = new ShieldCard("capacete", "Um capacete de construção encontrado em uma obra", 4, 15);
        ShieldCard colete = new ShieldCard("colete", "um colete a prova de balas remendado", 5, 20);
        HealingCard bandagem = new HealingCard("bandagem", "Uma bandagem relativamente suja", 2, 12);
        HealingCard medkit = new HealingCard("medkit", "Um kit médico quebrado, ainda deve servir", 4, 30);
        PassiveHealingCard injecao = new PassiveHealingCard("injecao", "analgesico", "uma injecao de analgesico, parece que pode ajudar", 3, 5, 3);

        Publisher publisher = new Publisher();
        CardsManager deckSystem = new CardsManager();

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
        }
        deckSystem.addCard(bandagem);
        deckSystem.addCard(medkit);
        deckSystem.addCard(injecao);
        
        // Embaralhar antes de começar
        deckSystem.recycleDeck();

        /**
         * Loop principal do combate.
         * O jogo continua enquanto 'playing' for verdadeiro e o herói estiver vivo.
         */
        while (playing) {
            explorador.setShield(0);
            explorador.setEnergy(10);
            System.out.println("====================");
            System.out.println(rato.getName() + " irá atacar causando " + rato.getDamage() + " de dano");
            System.out.println("Selecione 1 para comprar cartas ou 2 para não. " + deckSystem.getQuantityDeck() + " restantes");

            commands = scanner.nextInt();

            // Lógica de compra de cartas
            if (commands == 1) {
                deckSystem.printDeck();
                while (true) {
                    System.out.println("Selecione o número da carta para comprar ou 0 para sair");
                    int num = scanner.nextInt();
                    if (deckSystem.getQuantityHand() == MAX_CARTAS) {
                        System.out.println("Máximo de cartas na mão atingida!");
                        break;
                    }
                    if (num == 0) break;
                    deckSystem.buyCard(num);
                }
            }

            // Sub-loop do turno do jogador (uso de energia)
            while (commands != 0 && explorador.getEnergy() != 0) {
                exibirStatus(explorador, rato);

                System.out.println(explorador.getEnergy() + "/10 de Energia disponivel");
                System.out.println("1 - Abrir mão. " + deckSystem.getQuantityHand() + " cartas");
                System.out.println("2 - Encerrar turno");
                System.out.println("0 - Sair do jogo");

                commands = scanner.nextInt();

                if (commands == 1) {
                    processarUsoDeCarta(deckSystem, explorador, rato, publisher, scanner);
                    if (!rato.isAlive()) {
                        System.out.println(explorador.getName() + " conquistou a vitória!");
                        break;
                    }
                } else if (commands == 2) {
                    break;
                }
            }

            if (!rato.isAlive()) break;
            if (commands == 0) {
                System.out.println(explorador.getName() + " saiu do jogo!");
                break;
            }

            // Turno do Inimigo
            executarTurnoInimigo(rato, explorador, publisher);

            if (!explorador.isAlive()) {
                playing = false;
                System.out.println(explorador.getName() + " foi derrotado!");
                break;
            }
            deckSystem.clearHand();
        }
        scanner.close();
    }

    /**
     * Exibe no console os pontos de vida e escudo dos combatentes.
     * * @param h O herói do jogador.
     * @param e O inimigo.
     */
    private static void exibirStatus(Hero h, Enemy e) {
        System.out.println(h.getName() + " (" + h.getHealth() + "/1000) (" + h.getShield() + "/20)");
        System.out.println("vs");
        System.out.println(e.getName() + " (" + e.getHealth() + "/70)");
    }

    /**
     * Gerencia a interface de escolha e uso de cartas da mão do jogador.
     */
    private static void processarUsoDeCarta(CardsManager ds, Hero h, Enemy e, Publisher p, Scanner s) {
        if (ds.emptyDeck()) {
            System.out.println("Mão vazia!");
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
    private static void executarTurnoInimigo(Enemy e, Hero h, Publisher p) {
        System.out.println("--- Turno do Inimigo ---");
        System.out.println(e.getName() + " atacou!");
        e.atack(h, p);
        p.notifySubscribers();
    }
}