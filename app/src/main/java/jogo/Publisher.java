package jogo;

import java.util.ArrayList;

/**
 * Gerencia a comunicação entre os eventos do jogo e os efeitos ativos.
 * Implementa o padrão de projeto Observer (Publicador/Assinante), permitindo que 
 * diferentes efeitos se inscrevam para receber notificações quando um evento ocorre 
 * (ex: passagem de turno).
 */
public class Publisher {
    /** Lista de efeitos que estão "ouvindo" os eventos do jogo. */
    private ArrayList<Effects> subscribers = new ArrayList<>();

    /**
     * Registra um novo efeito para receber notificações de eventos.
     *
     * @param efeito O objeto de efeito que deseja se inscrever.
     */
    public void subscribe(Effects efeito) {
        subscribers.add(efeito);
    }

    /**
     * Remove um efeito da lista de notificações. 
     * Geralmente chamado quando um efeito expira ou é removido da entidade.
     *
     * @param efeito O objeto de efeito a ser removido.
     */
    public void unsubscribe(Effects efeito) {
        subscribers.remove(efeito);
    }

    public void clearPublisher() {
        subscribers.clear();
    }

    /**
     * Notifica todos os assinantes registrados, disparando o método de atualização 
     * de cada efeito ({@code getNotify}).
     * <p>Este método percorre a lista de inscritos e executa a lógica individual 
     * de cada status ativo no jogo.</p>
     */
    public void notifySubscribers() {
        for (Effects effect : subscribers) {
             effect.getNotify();
        }
        subscribers.removeIf(effect -> effect.getStacks() <= 0);
    }
}