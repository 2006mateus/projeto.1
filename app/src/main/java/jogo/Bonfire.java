package jogo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Classe que representa o evento de Fogueira (Bonfire).
 * <p>
 * A fogueira serve como um local de descanso e preparação, permitindo que o herói 
 * escolha entre diferentes estratégias de recuperação ou melhoria de atributos.
 * Implementa o padrão de projeto {@code Strategy} para gerenciar as opções disponíveis.
 * </p>
 * @version 1.0
 */
public class Bonfire extends Evento {
    
    /** Lista de estratégias (opções) disponíveis na fogueira. */
    private ArrayList<BonfireStrategy> opcoes = new ArrayList<>();

    /**
     * Construtor da classe Bonfire.
     * <p>
     * Inicializa a fogueira com as opções padrão: {@link RestStrategy} para cura 
     * e {@link EnergyStrategy} para aumento de energia.
     * </p>
     */
    public Bonfire() {
        opcoes.add(new RestStrategy());
        opcoes.add(new EnergyStrategy());
    }

    /**
     * Inicia a interação do jogador com a fogueira.
     * <p>
     * Exibe as opções disponíveis no console e aguarda uma entrada numérica válida 
     * do usuário. Uma vez selecionada, a estratégia correspondente é executada 
     * sobre o herói.
     * </p>
     * * @param explorer O herói que utilizará a fogueira.
     * @param enemy    Referência ao inimigo (geralmente {@code null} neste evento).
     * @param ds       Gerenciador de cartas (não utilizado diretamente aqui).
     * @param scanner  Scanner para leitura da opção escolhida pelo usuário.
     * @param p        Publicador de eventos (não utilizado diretamente aqui).
     * @param str      Caminho para arquivo de arte ASCII (se houver).
     * @return {@code true} indicando que o evento foi concluído com sucesso.
     */
    @Override
    public boolean start(Hero explorer, Enemy enemy, CardsManager ds, Scanner scanner, Publisher p, String str) {
        System.out.println("Bem-vindo a fogueira, escolha uma opcao para sua recuperacao:");
        System.out.println("============================================================");

        // Exibe as opções baseadas nas descrições das estratégias
        for (BonfireStrategy strategy : opcoes) {
            System.out.println(strategy.getInt() + " - " + strategy.getDescription());
        }

        boolean correct = false;

        // Loop de validação de entrada
        while (!correct) {
            if (scanner.hasNextInt()) {
                int comando = scanner.nextInt();

                for (BonfireStrategy s : opcoes) {
                    if (comando == s.getInt()) {
                        s.execute(explorer);
                        correct = true;
                        break;
                    }
                }
            } else {
                scanner.next(); // Limpa buffer se não for inteiro
            }

            if (!correct) {
                System.out.println("Escreva um comando valido!");
            }
        }
        return true;
    }
}