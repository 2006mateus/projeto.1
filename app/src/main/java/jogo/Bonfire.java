package jogo;

import java.util.Scanner;
import java.util.ArrayList;

public class Bonfire extends Evento {
    private ArrayList<BonfireStrategy> opcoes = new ArrayList<>();

    public Bonfire() {
        opcoes.add(new RestStrategy());
        opcoes.add(new EnergyStrategy());
    }

    public boolean start(Hero explorer, Enemy enemy, CardsManager ds, Scanner scanner, Publisher p, String str) {
        System.out.println("Bem-vindo a fogueira, escolha uma opcao para sua recuperacao:");
        System.out.println("============================================================");

        for (BonfireStrategy strategy : opcoes) {
            System.out.println(strategy.getInt() + " - " + strategy.getDescription());
        }

        boolean correct = false;

        while (!correct) {

            int comando = scanner.nextInt();

            for (BonfireStrategy s : opcoes) {
                if (comando == s.getInt()) {
                    s.execute(explorer);
                    correct = true;
                    break;
                }
            }

            if (!correct) {
                System.out.println("Escreva um comando valido!");
            }
        }
        return true;
    }
}