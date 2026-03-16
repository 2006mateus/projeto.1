import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int commands = -1;
        boolean playing = true;

        Scanner scanner = new Scanner (System.in);

        System.out.println("Digite o nome do heroi");
        String name = scanner.nextLine();

        Hero explorador = new Hero(name, 100, 0, 10);
        Enemy rato = new Enemy("rato bebe", 30, 0, 15);

        DamageCard bastao = new DamageCard("bastao", "Um bastao enferrujado, ele aparenta estar bem proximo de quebrar.", 3, 10);
        ShieldCard luva = new ShieldCard("luva velha", "Uma luva velha, aparenta ter sido para algum esporte ha muito tempo.", 20, 5);

        CardsManager deckSystem = new CardsManager();
        deckSystem.addCard(luva);
        deckSystem.addCard(bastao);


        while(playing == true) {
            explorador.setShield(0);
            explorador.setEnergy(10);

            while (commands != 0 && explorador.getEnergy() != 0) {
                System.out.println(explorador.getName() + " (" + explorador.getHealth() + "/100)" + "   (" + explorador.getShield() + "/20)");
                System.out.println("vs");
                System.out.println(rato.getName() + " (" + rato.getHealth() + "/30)");

                System.out.println();

                System.out.println(explorador.getEnergy() + "/" + "10 de Energia disponivel");
                System.out.println("1 - Abrir deck de cartas");
                System.out.println("2 - Comprar carta no baralho.");
                System.out.println("4 - Encerrar turno");
                System.out.println("0 - Sair do jogo");

                commands = scanner.nextInt();

                if (commands == 1) {
                    if (deckSystem.emptyDeck()) {
                        System.out.println("Nao ha cartas no seu inventario!");
                    } else {
                        
                    }
                } else if (commands == 2) {
                    if (explorador.getEnergy() < luva.getCost()) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        explorador.gainShield(luva.getShield());
                        bastao.use(explorador);
                    }
                } else if (commands == 3) {
                    if (explorador.isAlive() == false) {
                        playing = false;
                    }
                    break;
                }
            }
            if (rato.getHealth() != 30) { /*evita receber ataque de um rato recem-chegado por conta da falta de energia*/
                explorador.takeDamage(rato.getDamage());
            } else if (commands == 3) {
                rato.atack(explorador);
            }
            if (explorador.isAlive() == false) {
                commands = 0;
                playing = false;
                System.out.println(explorador.getName() + " foi derrotado por " + rato.getName() + " e foi para casa!");
                break;
            } else if (commands == 0) {
                System.out.println(explorador.getName() + " saiu do jogo!");
                break;
            }
        }

        scanner.close();
    }
}