import java.util.ArrayList;

public class Hero extends Entity {
    private int energy;

    public Hero(String name, int health, int shield, int energy, int maxShield, int maxHealth, ArrayList<Effects> effects) {
        super(name, health, shield, maxShield, maxHealth, effects);
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
