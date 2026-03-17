public class ShieldCard extends Cards{
    private int shield;

    public ShieldCard(String name, String description, int shield, int cost){
        super(name, description, cost);
        this.shield = shield;
    }

    public int getShield(){
        return shield;
    }

    public void use(Entity hero) {
        hero.gainShield(shield);
        System.out.println(hero.name + " ganhou " + shield + " de escudo!");
    }
}
