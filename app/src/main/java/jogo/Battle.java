package jogo;

import java.util.Scanner;

/**
 * Classe responsável por gerenciar o ciclo de vida de um combate no jogo.
 * <p>
 * A classe atua como um mediador que inicia o cenário de batalha, 
 * invoca a interface de interação e processa o resultado final (vitória ou derrota),
 * garantindo que o estado das entidades e do sistema de cartas seja resetado 
 * para o próximo encontro.
 * </p>
 * @version 1.0
 */
public class Battle extends Evento {

    /**
     * Inicia e controla o fluxo principal de uma batalha entre o herói e um inimigo.
     * <p>
     * O método executa as seguintes etapas:
     * <ol>
     * <li>Sinaliza o início do combate visualmente.</li>
     * <li>Delega a execução dos turnos para o motor de interface {@link Interface#run}.</li>
     * <li>Verifica a condição de sobrevivência do herói após o término do loop de combate.</li>
     * <li>Realiza o "cleanup" (limpeza) de estado: limpa a mão do jogador, 
     * remove efeitos de status de ambos os lutadores e limpa os inscritos no {@link Publisher}.</li>
     * </ol>
     * </p>
     * * @param explorador O objeto do herói controlado pelo jogador.
     * @param inimigo    O inimigo a ser enfrentado nesta batalha.
     * @param ds         O gerenciador de cartas (CardsManager) para manipulação do deck e mão.
     * @param s          O scanner para captura de entradas do usuário via console.
     * @param p          O publicador de eventos (Publisher) para o sistema de notificações de efeitos.
     * @param fileTxt    O nome do arquivo de texto contendo a arte ASCII do inimigo.
     * @return {@code true} se o herói venceu a batalha; {@code false} se o herói foi derrotado.
     */
    @Override
    public boolean start(Hero explorador, Enemy inimigo, CardsManager ds, Scanner s, Publisher p, String fileTxt) {
        boolean result = false;
        System.out.println(ConsoleUI.RED + "A batalha começa!" + ConsoleUI.RESET);
        ConsoleUI.pause(1000);

        // Chama o motor de interface que processa os turnos
        Interface.run(explorador, inimigo, s, ds, p, fileTxt);

        // Verificação do estado de vida para definir o retorno
        if (explorador.isAlive()) {
            result = true;
            System.out.println(ConsoleUI.GREEN + "Vitoria!" + ConsoleUI.RESET);
            
        } else {
            result = false;
            System.out.println(ConsoleUI.RED + "Derrota! Tente denovo outra vez..." + ConsoleUI.RESET);
        }

        // Limpeza de estado pós-combate
        ds.clearHand();                 // Descarta as cartas restantes na mão
        explorador.effectsList.clear(); // Remove venenos, buffs de força, etc. do Herói
        inimigo.effectsList.clear();    // Remove efeitos ativos do Inimigo
        p.clearPublisher();             // Limpa os observadores de eventos

        return result;
    }

    /**
     * Concede as recompensas financeiras ao herói após uma vitória confirmada.
     * <p>
     * Atualmente, a recompensa padrão é de 50 moedas de ouro.
     * </p>
     * * @param explorer O herói que receberá o ouro.
     */
    public void recompensa(Hero explorer) {
        System.out.println("\n--- RECOMPENSA DE VITÓRIA ---");
        explorer.gainGold(50);
        System.out.println("\n--- Parabens! Voce recebeu 50 de ouro! ---");
    }
}