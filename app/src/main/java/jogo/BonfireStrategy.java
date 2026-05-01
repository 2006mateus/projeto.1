package jogo;

/**
 * Interface que define o contrato para as estratégias de fogueira.
 * <p>
 * Segue o padrão de projeto <b>Strategy</b>, permitindo que diferentes comportamentos 
 * de bonificação (cura, buffs, melhorias) sejam implementados de forma isolada 
 * e trocados dinamicamente na classe {@link Bonfire}.
 * </p>
 */
public interface BonfireStrategy {
    
    /**
     * Executa a lógica específica da bonificação no herói.
     * * @param h O herói que receberá os benefícios da estratégia.
     */
    void execute(Hero h);

    /**
     * Retorna uma descrição textual da opção para ser exibida no menu.
     * * @return Uma {@code String} contendo o nome ou explicação da opção.
     */
    String getDescription();

    /**
     * Retorna o identificador numérico associado a esta opção de menu.
     * * @return Um valor inteiro que representa a tecla/comando da opção.
     */
    int getInt();
}