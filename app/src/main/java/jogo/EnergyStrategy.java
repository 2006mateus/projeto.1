package jogo;

/**
 * Implementação concreta da estratégia de fogueira focada em energia.
 * <p>
 * Esta classe aplica o benefício de "Polimento", que aumenta permanentemente 
 * a capacidade máxima de energia do herói em 10%. É uma das opções 
 * disponíveis no evento {@link Bonfire}.
 * </p>
 * @version 1.0
 */
public class EnergyStrategy implements BonfireStrategy {

    /**
     * Executa a lógica de aumento de energia máxima.
     * <p>
     * O cálculo é feito multiplicando a energia máxima atual por 1.1, 
     * resultando em um acréscimo de 10% sobre o valor base.
     * </p>
     * * @param explorer O herói que receberá o upgrade de energia.
     */
    @Override
    public void execute(Hero explorer) {
        double energyIncrease = (1.1 * explorer.getMaxEnergy());
        explorer.setMaxEnergy(energyIncrease);
        System.out.println("Sua energia maxima foi aumentada em 10%!");
    }

    /**
     * Fornece a descrição amigável desta estratégia para exibição no menu.
     * * @return Uma {@code String} descrevendo o benefício de polimento.
     */
    @Override
    public String getDescription() {
        return "Polimento: Aumente sua energia máxima em 10%";
    }

    /**
     * Retorna o identificador numérico desta opção.
     * * @return O valor {@code 2}, utilizado para seleção no menu da fogueira.
     */
    @Override
    public int getInt() {
        return 2;
    }
}