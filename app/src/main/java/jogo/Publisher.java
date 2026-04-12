package jogo;

import java.util.ArrayList;

/**
 * Gerenciador de notificações e eventos baseado no padrão de projeto <b>Observer</b>.
 * <p>
 * Atua como o "Sujeito" (Subject) que mantém uma lista de assinantes ({@link Effects}).
 * Sua principal função é disparar gatilhos de atualização para todos os efeitos de status 
 * ativos sempre que uma ação relevante ocorre no jogo (como o fim de um turno).
 * </p>
 */
public class Publisher {
    
    /** Lista de efeitos de status que estão "ouvindo" as notificações do jogo. */
    private ArrayList<Effects> subscribers = new ArrayList<>();

    /**
     * Registra um novo efeito na lista de notificações.
     * <p>
     * Uma vez inscrito, o efeito passará a reagir aos chamados do método {@link #notifySubscribers()}.
     * </p>
     *
     * @param efeito O objeto de efeito (buff ou debuff) que deseja se inscrever.
     */
    public void subscribe(Effects efeito) {
        subscribers.add(efeito);
    }

    /**
     * Remove manualmente um efeito da lista de notificações. 
     * <p>
     * Geralmente utilizado quando um efeito é curado ou removido por uma habilidade específica 
     * antes de sua expiração natural.
     * </p>
     *
     * @param efeito O objeto de efeito a ser removido.
     */
    public void unsubscribe(Effects efeito) {
        subscribers.remove(efeito);
    }

    /**
     * Limpa completamente a lista de assinantes.
     * <p>
     * Este método é crucial para evitar que efeitos de uma batalha anterior persistam 
     * ou causem erros de memória (Memory Leaks) em novos encontros.
     * </p>
     */
    public void clearPublisher() {
        subscribers.clear();
    }

    /**
     * Notifica todos os efeitos assinantes para que executem suas respectivas lógicas.
     * <p>
     * O processo ocorre em duas etapas:
     * 1. Percorre a lista executando o método {@code getNotify()} de cada efeito.
     * 2. Remove automaticamente da lista qualquer efeito cujas cargas (stacks) tenham chegado a 0 ou menos.
     * </p>
     */
    public void notifySubscribers() {
        // Dispara a lógica de cada efeito (ex: aplicar dano de veneno ou reduzir turnos de força)
        for (Effects effect : subscribers) {
             effect.getNotify();
        }
        
        // Limpeza automática: remove assinantes expirados
        subscribers.removeIf(effect -> effect.getStacks() <= 0);
    }
}