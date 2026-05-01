package jogo;

import java.util.Scanner;

/**
 * Classe que representa um evento de múltipla escolha no jogo.
 * <p>
 * Diferente de uma batalha, este evento apresenta ao jogador um cenário narrativo 
 * com duas opções. Cada escolha pode resultar em diferentes consequências para o 
 * {@link Hero}, como ganho de ouro ou perda de pontos de vida.
 * </p>
 * @version 1.0
 */
public class Choice extends Evento {
    /** O título do evento, utilizado também como identificador para a lógica de consequências. */
    private String titulo;
    
    /** A descrição narrativa do cenário apresentado ao jogador. */
    private String descricao;
    
    /** Texto descritivo da primeira opção de escolha. */
    private String opcao1;
    
    /** Texto descritivo da segunda opção de escolha. */
    private String opcao2;

    /**
     * Construtor para criar um novo evento de escolha.
     * * @param titulo    O título/nome do evento.
     * @param descricao O texto que descreve a situação.
     * @param op1       Texto da opção 1.
     * @param op2       Texto da opção 2.
     */
    public Choice(String titulo, String descricao, String op1, String op2) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.opcao1 = op1;
        this.opcao2 = op2;
    }

    /**
     * Inicia o evento de escolha, processa a entrada do jogador e aplica as consequências.
     * <p>
     * O método exibe o cenário e as opções no console. Dependendo do {@code titulo} 
     * do evento, diferentes ramos lógicos são executados para modificar os atributos 
     * do herói (Vida e Ouro).
     * </p>
     * * @param explorer  O herói que está interagindo com o evento.
     * @param inimigo   Referência ao inimigo (geralmente {@code null} neste tipo de evento).
     * @param ds        Gerenciador de cartas (não utilizado neste evento).
     * @param reader    Scanner para capturar a escolha numérica (1 ou 2) do usuário.
     * @param p         Publicador de eventos (não utilizado diretamente aqui).
     * @param fileTxt   Caminho para arquivo de arte (opcional).
     * @return {@code true} indicando que o evento foi processado e finalizado.
     */
    @Override
    public boolean start(Hero explorer, Enemy inimigo, CardsManager ds, Scanner reader, Publisher p, String fileTxt) {
        System.out.println("\n--- " + titulo + " ---");
        System.out.println(descricao);
        System.out.println("1. " + opcao1);
        System.out.println("2. " + opcao2);

        int escolha = reader.nextInt();

        // Lógica para o evento "Um Baú Suspeito"
        if (titulo.equals("Um Baú Suspeito")) {
            if (escolha == 1) {
                System.out.println("Você abriu o baú e encontrou 50 de ouro! Mas era uma armadilha...");
                explorer.gainGold(50);
                explorer.setHealth((explorer.getHealth() - 10));
            } else {
                System.out.println("Você ignorou o baú e seguiu em frente com segurança.");
            }
        } 
        // Lógica para o evento "Rato esquisito"
        else if (titulo.equals("Rato esquisito")){
            if (escolha == 1) {
                System.out.println("O rato te respeita, voce ganhou 20 de ouro!");
                explorer.gainGold(20);
            } else {
                System.out.println("O rato nao gostou de voce, ele te mordeu e fugiu entre as sombras!");
                explorer.setHealth((explorer.getHealth() - 7));
            }
        }
        // Lógica para o evento "Hater do Kanye West"
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