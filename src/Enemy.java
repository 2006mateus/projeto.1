import java.util.Random;

public class Enemy extends Entity {
    private int damage;
    private Random aleatoryChoice = new Random();

    public Enemy(String name, int health, int shield, int damage, int maxShield){
        super(name, health, shield, maxShield);
        this.damage = damage;
    }

    public void act(Hero hero){
        int choice = aleatoryChoice.nextInt(3);

        if (choice == 0) {
            hero.takeDamage(damage);
        } else if (choice == 1) {
            hero.applyEffect(new Venom("Veneno", hero, 2, 10), hero);
        } else if (choice == 2) {
            this.applyEffect(new Strength("Foco", this, 3, 5), this);
        }
    }

    public int getDamage() {
        return this.damage;
    }
}
