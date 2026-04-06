
import java.util.ArrayList;

public abstract class Entity {
    protected String name;
    protected int health;
    protected int shield;
    protected final int MAX_HEALTH;
    protected final int MAX_SHIELD;
    protected ArrayList<Effects> effectsList = new ArrayList<>();

    public Entity(String name, int health, int shield, int maxShield) {
        this.name = name;
        this.health = health;
        this.shield = shield;
        this.MAX_HEALTH = health;
        this.MAX_SHIELD = maxShield;
    }

    public void takeDamage(int damage) {
        if (damage >= (this.health + this.shield)){
            this.health = 0;
        } else if (this.shield >= damage){ 
            this.shield -= damage;
        } else {
            this.health = this.health + this.shield - damage;
            this.shield = 0;
        }
    }

    public void gainShield(int shield) {
        if (this.shield + shield > this.MAX_SHIELD) {
            this.shield = MAX_SHIELD;
        } else {
            this.shield += shield;
        }
    }

    public void applyEffect(Effects effect, Entity npc){
        boolean exist = false;
        for (int i = 0; i < npc.effectsList.size(); i++){
            if (npc.effectsList.get(i).name.equals(effect.name)){
                npc.effectsList.get(i).stacks += effect.stacks;
                exist = true;
                break;
            }
        }

        if (!exist){
            npc.effectsList.add(effect);
        }
    }

    public Boolean isAlive() {
        if (this.health <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getShield() {
        return this.shield;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getStrengthBonus() {
    for (Effects e : this.effectsList) {
        if (e instanceof Strength) {
            return ((Strength) e).getStrengthening();
        }
    }
    return 0;
    }
}