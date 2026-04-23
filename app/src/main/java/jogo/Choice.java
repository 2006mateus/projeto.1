package jogo;

import java.util.Scanner;

public class Choice extends Evento {
    private String titulo;
    private String descricao;
    private String opcao1;
    private String opcao2;

    public Choice(String titulo, String descricao, String op1, String op2) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.opcao1 = op1;
        this.opcao2 = op2;
    }

    @Override
    public boolean start(Hero explorer, Enemy inimigo, CardsManager ds, Scanner reader, Publisher p, String fileTxt) {
        System.out.println("\n--- " + titulo + " ---");
        System.out.println(descricao);
        System.out.println("1. " + opcao1);
        System.out.println("2. " + opcao2);

        int escolha = reader.nextInt();

        if (titulo.equals("Um Baú Suspeito")) {
            if (escolha == 1) {
                System.out.println("Você abriu o baú e encontrou 50 de ouro! Mas era uma armadilha...");
                explorer.gainGold(explorer.getGold() + 50);
                explorer.setHealth((explorer.getHealth() - 10));
            } else {
                System.out.println("Você ignorou o baú e seguiu em frente com segurança.");
            }
        } 
        else if (titulo.equals("Rato esquisito")){
            if (escolha == 1) {
                System.out.println("O rato te respeita, voce ganhou 20 de ouro!");
                explorer.gainGold(explorer.getGold() + 20);
            } else {
                System.out.println("O rato nao gostou de voce, ele te mordeu e fugiu entre as sombras!");
                explorer.setHealth((explorer.getHealth() - 7));
            }
        }
        else if (titulo.equals("Hater do Kanye West")){
            if (escolha == 1) {
                System.out.println("Voce foi enganado! Na verdade ele era um fa do Kanye West disfarçado... ele te esfaqueou e fugiu! Voce tomou 50 de dano!");
                explorer.setHealth((explorer.getHealth() - 50));
            } else {
                System.out.println("Parabens! Na verdade ele era um fa do Kanye West disfarçado... ele te reconhece como um verdadeiro fa e te entrega 200 de ouro!");
                explorer.gainGold(200);
            }
        }

        return true;
    }
}
