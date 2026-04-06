import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int commands = -1;
        boolean playing = true;
        final int MAX_CARTAS = 4; /* máximo de cartas que o jogador pode ter na mão ao mesmo tempo */

        Scanner scanner = new Scanner (System.in);

        System.out.println("Digite o nome do heroi");
        String name = scanner.nextLine();

        Hero explorador = new Hero(name, 1000, 0, 10, 20);
        Enemy rato = new Enemy("rato bebe", 70, 0, 15, 0);

        DamageCard bastao = new DamageCard("bastao", "Um bastao enferrujado, ele aparenta estar bem proximo de quebrar.", 3, 10);
        DamageCard faca = new DamageCard("faca", "Uma faca de cozinha comum, provavelmente já foi muito utilizada na cozinha", 4, 12);
        DamageCard Dardo = new DamageCard("Dardo", "Um dardo de caça proveniente de tribos da regiao, aparenta ser venenoso.", 6, 2);
        DamageCard oculos = new DamageCard("oculos velhos", "Um oculos de grau danificado, apesar de sua aparencia funciona perfeitamente...", 5, 0);
        ShieldCard luva = new ShieldCard("luva velha", "Uma luva velha, aparenta ter sido para algum esporte ha muito tempo.", 10, 3);
        ShieldCard capacete = new ShieldCard("capacete", "Um capacete de construção encontrado em uma obra", 15, 4);

        Publisher publisher = new Publisher();

        CardsManager deckSystem = new CardsManager();
        for (int i = 0; i < 2; i += 1) {
            deckSystem.addCard(luva);
            deckSystem.addCard(faca);
            deckSystem.addCard(bastao);
            deckSystem.addCard(capacete);
            deckSystem.addCard(Dardo);
            deckSystem.addCard(oculos);
        }
        deckSystem.recycleDeck(); /* embaralhar antes de começar */

        while(playing == true) {
            explorador.setShield(0);
            explorador.setEnergy(10);
            System.out.println("====================");
            System.out.println(rato.getName() + " irá atacar causando " + rato.getDamage() + " de dano");
            System.out.println("Selecione 1 se deseja comprar cartas no baralho ou 2 se não. " + deckSystem.getQuantityDeck() + " cartas restantes");

            commands = scanner.nextInt();

            if (commands == 1) {
                deckSystem.printDeck();
                while (true) {
                    System.out.println("Selecione o número das cartas a serem compradas ou 0 para sair do baralho");
                    int num = scanner.nextInt();
                    if (deckSystem.getQuantityHand() == MAX_CARTAS) {
                        System.out.println("Máximo de cartas na mão atingida!");
                        break;
                    }
                    if (num == 0) {
                        break;
                    }
                    deckSystem.buyCard(num);
                }
            }

            while (commands != 0 && explorador.getEnergy() != 0) {
                System.out.println(explorador.getName() + " (" + explorador.getHealth() + "/100)" + "   (" + explorador.getShield() + "/20)");
                System.out.println("vs");
                System.out.println(rato.getName() + " (" + rato.getHealth() + "/70)");

                System.out.println();

                System.out.println(explorador.getEnergy() + "/" + "10 de Energia disponivel");
                System.out.println("1 - Abrir deck de cartas. " + deckSystem.getQuantityHand() + " cartas no deck");
                System.out.println("2 - Encerrar turno");
                System.out.println("0 - Sair do jogo");

                commands = scanner.nextInt();

                if (commands == 1) {
                    if (deckSystem.emptyDeck()) {
                        System.out.println("Nao ha cartas no seu inventario!");
                    } else {
                        deckSystem.printHand();
                        System.out.println("Selecione o número da carta a ser usada ou 0 para fechar o deck de cartas");

                        commands = scanner.nextInt();

                        if (commands == 0) {
                            commands = -1;
                            continue;
                        }
                        deckSystem.useCard(commands - 1, explorador, rato, publisher);

                        if (rato.isAlive() == false) {
                            System.out.println(explorador.getName() + " aniquilou " + rato.getName() + " e consquistou a vitória!");
                            break;
                        }
                    }
                } else if (commands == 2) {
                    break;
                }
            }
            if (rato.isAlive() == false) {
                break;
            }
            if (commands == 0) {
                System.out.println(explorador.getName() + " saiu do jogo!");
                break;
            }

            System.out.println("--- Turno do Inimigo ---");
            System.out.println(rato.getName() + " atacou causando " + rato.getDamage() + " de dano!");

            rato.atack(explorador, publisher); 
            
            publisher.notifySubscribers();

            if (explorador.isAlive() == false) {
                commands = 0;
                playing = false;
                System.out.println(explorador.getName() + " foi derrotado por " + rato.getName() + " e foi para casa!");
                break;
            }
            deckSystem.clearHand();
        }

        scanner.close();
    }
}