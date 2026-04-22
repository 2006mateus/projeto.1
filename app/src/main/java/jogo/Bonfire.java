package jogo;

import java.util.Scanner;

public class Bonfire extends Evento{
    public void start(Hero explorer){
        System.out.println("Bem-vindo a fogueira, faça uma escolha para ajudar na sua recuperaçao");
        System.out.println("====================================================================================================");
        System.out.println("Descanso profundo: Recupere cerca de 30% da sua vida maxima");
        System.out.println("Cardio: Aumente sua energia maxima em 10%");

        Scanner scan = new Scanner(System.in);
        String comando;
        double life_increase = 0.3 * explorer.MAX_HEALTH;
        double energyIncrease = (1.1 * explorer.getMaxEnergy());

        comando = scan.next();
        boolean correct = false;

        while (correct == false){
            if (comando == "Descanso profundo"){
                explorer.gainHealth(life_increase);
                System.out.println("Sua vida foi aumentada em 30%!");
                correct = true;
            } else if (comando == "Polimento"){
                explorer.setMaxEnergy(energyIncrease);
                System.out.println("Sua energia maxima foi aumentada em 10%!");
                correct = true;
            } else {
                System.out.println("Escreva um comando valido!");
            }
        }

        scan.close();
    }
}
