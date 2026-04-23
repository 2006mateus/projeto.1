package jogo;

import java.util.Scanner;

public class Bonfire extends Evento {

    private String titulo;
    private String descricao;

    // Construtor: Aqui definimos o "corpo" da fogueira
    public Bonfire() {
        this.titulo = "Fogueira";
        this.descricao = "O calor das brasas renova suas energias. O que deseja fazer?";
    }

    @Override
    public boolean start(Hero explorer, Enemy inimigo, CardsManager ds, Scanner reader, Publisher p, String fileTxt) {
        System.out.println("\n[" + this.titulo + "] Você encontra um lugar seguro para descansar...");
        System.out.println(this.descricao);
        System.out.println("==========================================================");
        System.out.println("1. Descanso Profundo: Recupere +30% da vida máxima");
        System.out.println("2. Cardio: Aumente sua energia máxima em 10%");
        System.out.println("==========================================================");

        boolean choiceMade = false;
        
        while (!choiceMade) {
            System.out.print("Escolha (1-2): ");
            
            // Verificação simples para evitar erro se o cara digitar letra em vez de número
            String input = reader.next();

            if (input.equals("1")) {
                // Cálculo baseado no HP Máximo do herói
                double cura = 0.3 * explorer.getMaxHealth(); 
                explorer.gainHealth(cura);
                System.out.println("Você dormiu profundamente. Vida recuperada!");
                choiceMade = true;
            } 
            else if (input.equals("2")) {
                // Aumento de energia permanente ou para a próxima run
                double novaEnergia = explorer.getMaxEnergy() * 1.10;
                explorer.setMaxEnergy(novaEnergia);
                System.out.println("Você se sente mais disposto! Energia máxima aumentada.");
                choiceMade = true;
            } 
            else {
                System.out.println("Comando inválido! Digite apenas 1 ou 2.");
            }
        }

        return true;
    }
}