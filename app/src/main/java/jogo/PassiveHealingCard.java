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

    // ainda falta colocar esse use no cards manager, que vai ser igual pra todas as cartas, mas pra isso tem que mudar os outros efeitos tambem
    public void use(Entity user, Entity enemy,Publisher publisher) {
        System.out.println("a injecao foi aplicada com sucesso!");
        user.gainHealth(healing);
        PassiveHealing passiveHealing = new PassiveHealing(effectName, user, stacks, healing);
        user.applyEffect(passiveHealing, user);
        publisher.subscribe(passiveHealing);
    }
}
