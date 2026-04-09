package jogo;

public class VenomCard extends Cards {
    private int damage;
    private int hitPoints;
    private int stacks;
    private String effectName;

    public VenomCard(String name, String effectName, String description, int cost, int damage, int hitPoints, int stacks) {
        super(name, description, cost);
        this.damage = damage;
        this.hitPoints = hitPoints;
        this.effectName = effectName;
        this.stacks = stacks;
    }

    public void use(Entity user, Entity enemy, Publisher publisher) {
        System.out.println(this.name + " atingiu o inimigo e aplicou Veneno!");
        int bonus = user.getStrengthBonus();
        int finalDamage = this.damage + bonus;
        enemy.takeDamage(finalDamage);
        Venom venom = new Venom(effectName, enemy, stacks, hitPoints);
        user.applyEffect(venom, enemy);
        publisher.subscribe(venom);
    }
}
