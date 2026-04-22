package jogo;

import java.util.Scanner;
import java.util.ArrayList;

public class Bonfire extends Evento {
    private ArrayList<BonfireStrategy> opcoes = new ArrayList<>();

    public Bonfire() {
        opcoes.add(new RestStrategy());
        opcoes.add(new EnergyStrategy());
    }

    public void start(Hero explorer, CardsManager ds, Scanner scanner) {
        System.out.println("Bem-vindo a fogueira, escolha uma opcao para sua recuperacao:");
        System.out.println("============================================================");

        for (BonfireStrategy strategy : opcoes) {
            System.out.println("- " + strategy.getDescription());
        }

        Scanner scan = new Scanner(System.in);
        boolean correct = false;

        while (!correct) {

            String comando = scan.nextLine();

            for (BonfireStrategy s : opcoes) {
                if (comando.equalsIgnoreCase(s.getDescription())) {
                    s.execute(explorer);
                    correct = true;
                    break;
                }
            }

            if (!correct) {
                System.out.println("Escreva um comando valido!");
            }
        }
        scan.close();
    }
}