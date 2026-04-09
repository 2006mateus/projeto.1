package jogo;

public class HealingCard extends Cards {
    private int healing;

    public HealingCard(String name, String description, int cost, int healing) {
        super(name, description, cost);
        this.healing = healing;
    }

    public int getHealing() {
        return this.healing;
    }

    public void use(Entity user, Entity enemy, Publisher publisher) {
        user.gainHealth(healing);
        int finalHealing = user.getFinalHealing(healing);
        System.out.println(user.name + " ganhou " + finalHealing + " de vida!");
    }
}
