import java.util.ArrayList;

public class Enemy extends Entity {
    private int damage;

    public Enemy(String name, int health, int shield, int damage, int maxShield, int maxHealth, ArrayList<Effects> effects){
        super(name, health, shield, maxShield, maxHealth, effects);
        this.damage = damage;
    }

    public void atack(Hero hero){
        hero.takeDamage(damage);
    }

    public int getDamage() {
        return this.damage;
    }
}
