package jogo;

/**
 * Classe base para todos os objetos que desejam ser notificados sobre eventos do jogo.
 * No padrão de projeto Observer, o {@code Subscriber} define o comportamento comum 
 * para os "assinantes" que reagem a disparos feitos pelo {@link Publisher}.
 * <p>Esta classe deve ser estendida por qualquer componente que precise executar 
 * ações periódicas (ex: {@link Effects}).</p>
 */
public class Subscriber {
    
    /**
     * Método chamado pelo {@link Publisher} para notificar o assinante de que 
     * um evento ocorreu (ex: o turno passou).
     * <p>Nas subclasses, este método deve ser sobrescrito com a lógica específica 
     * que deve ser executada no momento da notificação.</p>
     */
    public void getNotify() {
        // Implementação vazia por padrão; deve ser sobrescrita nas classes filhas.
    }
}