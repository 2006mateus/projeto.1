public class DamageCard extends Cards{
    private int damage;

    public DamageCard(String name, String description, int cost, int damage){
        super(name, description, cost);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}
