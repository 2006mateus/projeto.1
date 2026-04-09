package jogo;

public class PassiveHealingCard extends Cards {
    private int healing;
    private int stacks;
    private String effectName;

    public PassiveHealingCard(String name, String effectName, String description, int cost, int healing, int stacks) {
        super(name, description, cost);
        this.healing = healing;
        this.effectName = effectName;
        this.stacks = stacks;
    }

    public void use(Entity user, Entity enemy, Publisher publisher) {
        System.out.println(this.name + " foi utilizada com sucesso!");
        user.gainHealth(healing);
        PassiveHealing passiveHealing = new PassiveHealing(effectName, user, stacks, healing);
        user.applyEffect(passiveHealing, user);
        publisher.subscribe(passiveHealing);
    }
}
