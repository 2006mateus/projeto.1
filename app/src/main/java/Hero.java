
public class Hero extends Entity {
    private int energy;

    public Hero(String name, int health, int shield, int energy, int maxShield) {
        super(name, health, shield, maxShield);
        this.energy = energy;
    }

    public void loseEnergy(int custo){
        energy -= custo;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energia) {
        this.energy = 10;
    }
}
