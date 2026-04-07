package jogo;
public class ShieldCard extends Cards{
    private int shield;

    public ShieldCard(String name, String description, int shield, int cost){
        super(name, description, cost);
        this.shield = shield;
    }

    public int getShield(){
        return shield;
    }

    public void use(Entity user, Entity hero) {
        user.gainShield(shield);
        System.out.println(user.name + " ganhou " + shield + " de escudo!");
    }
}
