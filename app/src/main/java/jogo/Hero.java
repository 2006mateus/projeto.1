package jogo;

/**
 * Representa o protagonista controlado pelo jogador.
 * Além dos atributos de uma {@link Entity}, o Herói possui o recurso de Energia,
 * que é consumido para a execução de cartas e habilidades durante o combate.
 */
public class Hero extends Entity {
    /** A quantidade atual de energia disponível para o turno. */
    private double energy;
    private double maxEnergy;
    private int gold;

    /**
     * Construtor para criar um novo herói.
     *
     * @param name      O nome do herói.
     * @param health    Vida inicial e máxima.
     * @param shield    Valor inicial de escudo.
     * @param energy    Quantidade inicial de energia.
     * @param maxShield Limite máximo de escudo permitido.
     */
    public Hero(String name, int health, int shield, int energy, int maxShield, int maxEnergy, int gold) {
        super(name, health, shield, maxShield);
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.gold = gold;
    }

    /**
     * Reduz a energia atual do herói com base no custo de uma ação ou carta.
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
     * Obtém a quantidade de energia atual do herói.
     *
     * @return A energia disponível.
     */
    public double getEnergy() {
        return this.energy;
    }

    /**
     * Define a energia do herói. 
     * <p>Nota: Atualmente, este método redefine a energia para o valor fixo de 10,
     * independentemente do parâmetro passado.</p>
     *
     * @param energia O valor de energia.
     */
    public void setEnergy(double energia) {
        this.energy = energia;
    }

    public double getMaxEnergy(){
        return this.maxEnergy;
    }

    public void setMaxEnergy(double newMaxEnergy){
        this.maxEnergy = newMaxEnergy;
    }

    public int getGold(){
        return this.gold;
    }

    public void gainGold(int ouro){
        this.gold += ouro;
    }

    public int getMaxHealth(){
        return this.MAX_HEALTH;
    }

    public void loseGold(int ouro){
        this.gold -= ouro;
    }
}