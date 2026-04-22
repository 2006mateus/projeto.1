package jogo;

import java.util.Scanner;

/**
 * Classe responsável por gerenciar o ciclo de vida de um combate.
 * <p>
 * A classe atua como um mediador que inicia o cenário de batalha, 
 * invoca a interface de interação e processa o resultado final (vitória ou derrota),
 * garantindo que o estado do jogo seja resetado para o próximo encontro.
 * </p>
 */
public class Battle extends Evento{

    /**
     * Inicia e controla o fluxo de uma batalha entre o herói e um inimigo.
     * <p>
     * O método executa as seguintes ações:
     * 1. Exibe mensagens de início de combate via console.
     * 2. Delega o controle do turno para a classe {@code Interface.run}.
     * 3. Avalia se o herói sobreviveu para determinar o desfecho.
     * 4. Realiza a limpeza (reset) da mão do jogador, dos efeitos ativos (buffs/debuffs) 
     * em ambos os personagens e dos inscritos no sistema de eventos (Publisher).
     * </p>
     * * @param explorador O objeto do herói controlado pelo jogador.
     * @param inimigo    O inimigo a ser enfrentado nesta batalha.
     * @param ds         O gerenciador de cartas (CardsManager) para manipulação do deck e mão.
     * @param s          O scanner para captura de entradas do usuário.
     * @param p          O publicador de eventos (Publisher) para o sistema de notificações.
     * @param fileTxt    O caminho ou nome do arquivo de texto contendo a arte ASCII ou dados da sala.
     * @return {@code true} se o herói venceu a batalha; {@code false} se o herói foi derrotado.
     */
    public static boolean start(Hero explorador, Enemy inimigo, CardsManager ds, Scanner s, Publisher p, String fileTxt){
        boolean result = false;
        System.out.println(ConsoleUI.RED + "A batalha começa!" + ConsoleUI.RESET);
        ConsoleUI.pause(1000);

        // Chama o motor de interface que processa os turnos
        Interface.run(explorador, inimigo, s, ds, p, fileTxt);

        // Verificação do estado de vida para definir o retorno
        if (explorador.isAlive()){
            result = true;
            System.out.println(ConsoleUI.GREEN + "Vitoria!" + ConsoleUI.RESET);
            
        } else {
            result = false;
            System.out.println(ConsoleUI.GREEN + "Derrota! Tente denovo outra vez..." + ConsoleUI.RESET);
        }

        // Limpeza de estado pós-combate
        ds.clearHand();                 // Descarta as cartas restantes na mão
        explorador.effectsList.clear(); // Remove venenos, buffs de força, etc. do Herói
        inimigo.effectsList.clear();    // Remove efeitos ativos do Inimigo
        p.clearPublisher();             // Limpa os observadores de eventos

        return result;
    }


    public void recompensa(){

    }
}