package jogo;

public class StrengthCard extends Cards {
    private int bonus;
    private int stacks;
    private String effectName;

    public StrengthCard(String name, String effectName, String description, int cost, int bonus, int stacks) {
        super(name, description, cost);
        this.bonus = bonus;
        this.effectName = effectName;
        this.stacks = stacks;
    }

    public void use(Entity user, Entity enemy, Publisher publisher) {
        System.out.println(this.name + " foi utilizada com sucesso!");
        Strength strength = new Strength(effectName, user, stacks, bonus);
        user.applyEffect(strength, user);
        publisher.subscribe(strength);
    }
}
