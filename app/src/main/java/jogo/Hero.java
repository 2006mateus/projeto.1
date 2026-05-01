package jogo;

/**
 * Representa o protagonista controlado pelo jogador.
 * <p>
 * Além dos atributos herdados de {@link Entity}, o Herói possui recursos específicos 
 * como Energia (consumida para jogar cartas) e Ouro (utilizado para transações e recompensas).
 * </p>
 * @version 1.0
 */
public class Hero extends Entity {
    /** A quantidade atual de energia disponível para o turno. */
    private double energy;
    /** O limite máximo de energia que o herói pode ter. */
    private double maxEnergy;
    /** A quantidade de dinheiro acumulada pelo herói. */
    private int gold;

    /**
     * Construtor para criar um novo herói com atributos completos.
     *
     * @param name      O nome do herói.
     * @param health    Vida inicial e máxima.
     * @param shield    Valor inicial de escudo.
     * @param energy    Quantidade inicial de energia.
     * @param maxShield Limite máximo de escudo permitido.
     * @param maxEnergy Limite máximo de energia permitido.
     * @param gold      Quantidade inicial de ouro.
     */
    public Hero(String name, int health, int shield, int energy, int maxShield, int maxEnergy, int gold) {
        super(name, health, shield, maxShield);
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.gold = gold;
    }

    /**
     * Reduz a energia atual do herói com base no custo de uma ação ou carta.
     * <p>
     * O método verifica se o herói possui energia suficiente antes de subtrair.
     * </p>
     *
     * @param custo O valor a ser subtraído da energia atual.
     */
    public void loseEnergy(int custo) {
        if (this.energy - custo < 0) {
            return;
        }
        energy -= custo;
    }

    /**
     * Obtém a quantidade de energia atual disponível para o herói.
     *
     * @return A energia atual.
     */
    public double getEnergy() {
        return this.energy;
    }

    /**
     * Define a energia atual do herói para um valor específico.
     *
     * @param energia O novo valor de energia.
     */
    public void setEnergy(double energia) {
        this.energy = energia;
    }

    /**
     * Retorna o limite máximo de energia do herói.
     *
     * @return A energia máxima permitida.
     */
    public double getMaxEnergy(){
        return this.maxEnergy;
    }

    /**
     * Define um novo limite máximo de energia para o herói.
     *
     * @param newMaxEnergy O novo valor máximo de energia.
     */
    public void setMaxEnergy(double newMaxEnergy){
        this.maxEnergy = newMaxEnergy;
    }

    /**
     * Retorna a quantidade de ouro atual do herói.
     *
     * @return O total de ouro acumulado.
     */
    public int getGold(){
        return this.gold;
    }

    /**
     * Adiciona uma quantia de ouro ao saldo atual do herói.
     *
     * @param ouro O valor a ser adicionado.
     */
    public void gainGold(int ouro){
        this.gold += ouro;
    }

    /**
     * Retorna o valor de vida máxima (capacidade total) do herói.
     *
     * @return A vida máxima (MAX_HEALTH).
     */
    public int getMaxHealth(){
        return this.MAX_HEALTH;
    }

    /**
     * Subtrai uma quantia de ouro do saldo atual do herói.
     *
     * @param ouro O valor a ser removido.
     */
    public void loseGold(int ouro){
        this.gold -= ouro;
    }
}