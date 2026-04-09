package jogo;

/**
 * Representa uma carta de cura direta e instantânea.
 * * <p>Diferente da {@link PassiveHealingCard}, esta carta restaura os pontos de vida 
 * do usuário imediatamente após o uso, sem aplicar efeitos de duração por turnos.</p>
 */
public class HealingCard extends Cards {
    private int healing;

    /**
     * Construtor para a carta de Cura.
     * * @param name        O nome da carta.
     * @param description O texto descritivo informando quanto de vida será restaurado.
     * @param cost        O custo de recursos (energia/mana) para usar a carta.
     * @param healing     O valor bruto de cura fornecido pela carta.
     */
    public HealingCard(String name, String description, int cost, int healing) {
        super(name, description, cost);
        this.healing = healing;
    }

    /**
     * Obtém o valor base de cura da carta.
     * * @return O valor inteiro de cura.
     */
    public int getHealing() {
        return this.healing;
    }

    /**
     * Executa a ação de cura no usuário.
     * * <p>O método invoca a cura na entidade e calcula o valor final restaurado 
     * (considerando possíveis modificadores de cura da entidade) para exibição no console.</p>
     * * @param user      A {@link Entity} que utilizou a carta e será curada.
     * @param enemy     A {@link Entity} adversária (não afetada por esta carta).
     * @param publisher O sistema de eventos (não utilizado diretamente nesta carta, 
     * mas mantido pela assinatura da classe pai).
     */
    @Override
    public void use(Entity user, Entity enemy, Publisher publisher) {
        user.gainHealth(healing);
        int finalHealing = user.getFinalHealing(healing);
        System.out.println(user.name + " ganhou " + finalHealing + " de vida!");
    }
}
