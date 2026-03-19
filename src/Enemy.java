public class Enemy extends Entity {
    private int damage;

    public Enemy(String name, int health, int shield, int damage, int maxShield){
        super(name, health, shield, maxShield);
        this.damage = damage;
    }

    public void atack(Hero hero){
        hero.takeDamage(damage);
    }

    public int getDamage() {
        return this.damage;
    }
}
