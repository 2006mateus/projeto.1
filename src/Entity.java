public abstract class Entity {
    protected String name;
    protected int health;
    protected int shield;

    public Entity(String name, int health, int shield) {
        this.name = name;
        this.health = health;
        this.shield = shield;
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
        this.shield += shield;
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
}