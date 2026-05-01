package jogo;

import java.util.Scanner;

/**
 * Classe abstrata fundamental que define a estrutura de um evento no jogo.
 * <p>
 * Um {@code Evento} é qualquer situação interativa que ocorre quando o herói 
 * entra em uma {@link Sala}. Pode representar combates ({@link Battle}), 
 * áreas de descanso ({@link Bonfire}) ou decisões narrativas ({@link Choice}).
 * </p>
 */
public abstract class Evento {
    
    /**
     * Método abstrato que inicia a execução do evento.
     * <p>
     * Cada subclasse deve implementar este método para definir o que acontece 
     * quando o evento é disparado (ex: abrir menu, iniciar rodadas de luta ou 
     * aplicar modificadores de status).
     * </p>
     * * @param explorador O herói que está participando do evento.
     * @param inimigo    O inimigo associado ao local (pode ser {@code null} em eventos não hostis).
     * @param ds         O gerenciador de cartas para manipulação do deck durante o evento.
     * @param s          O scanner para leitura de entradas do jogador via console.
     * @param p          O publicador de eventos para notificações de sistema e efeitos.
     * @param fileTxt    O caminho do arquivo de recurso (como arte ASCII) associado ao evento.
     * @return {@code true} se o evento foi concluído com sucesso; 
     * {@code false} se o evento resultou em algo que interrompa o progresso (ex: derrota).
     */
    public abstract boolean start(Hero explorador, Enemy inimigo, CardsManager ds, Scanner s, Publisher p, String fileTxt);
}