package jogo;
public class DamageCard extends Cards{
    private int damage;

    public DamageCard(String name, String description, int cost, int damage){
        super(name, description, cost);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public void use(Entity user, Entity enemy) {
        int bonus = user.getStrengthBonus();
        int finalDamage = this.damage + bonus;
        enemy.takeDamage(finalDamage);
        System.out.println(enemy.name + " recebeu " + finalDamage + " de dano!");
    }
}
