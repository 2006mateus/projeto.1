package jogo;

/**
 * Implementação concreta da estratégia de fogueira focada em cura.
 * <p>
 * Esta classe aplica o benefício de "Descanso Profundo", que permite ao herói 
 * recuperar uma parcela significativa de sua saúde baseada em um percentual 
 * de sua vida máxima.
 * </p>
 * @version 1.0
 */
public class RestStrategy implements BonfireStrategy {
    
    /**
     * Executa a lógica de cura do herói.
     * <p>
     * O cálculo recupera 30% da {@code MAX_HEALTH} do herói. O valor é 
     * processado pelo método {@code gainHealth} para garantir que não 
     * ultrapasse o limite máximo permitido.
     * </p>
     * * @param explorer O herói que está descansando na fogueira.
     */
    @Override
    public void execute(Hero explorer) {
        double life_increase = 0.3 * explorer.MAX_HEALTH;
        explorer.gainHealth(life_increase);
        System.out.println("Sua vida foi aumentada em 30%!");
    }

    /**
     * Fornece a descrição amigável desta estratégia para exibição no menu.
     * * @return Uma {@code String} descrevendo o benefício de cura.
     */
    @Override
    public String getDescription() {
        return "Descanso profundo: Recupere cerca de 30% da sua vida máxima";
    }

    /**
     * Retorna o identificador numérico desta opção.
     * * @return O valor {@code 1}, utilizado para seleção no menu da fogueira.
     */
    @Override
    public int getInt() {
        return 1;
    }
}