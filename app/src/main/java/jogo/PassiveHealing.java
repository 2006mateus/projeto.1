package jogo;

public class PassiveHealing extends Effects{
    private int healing;

    public PassiveHealing(String name, Entity owner, int stacks, int healing){
        super(name, owner, stacks);
        this.healing = healing;
    }

    @Override
    public void getNotify() {

        if (this.stacks <= 0){
            return;
        }

        this.stacks -= 1;

        if (owner.health == 0) { // necessário pois sapenas há a notificação depois do turno do inimigo
            return;
        }
        
        if (owner.health + healing >= owner.MAX_HEALTH) {
            owner.health = owner.MAX_HEALTH;
        } else {
            owner.health += healing;
        }

        System.out.println("Voce recebeu" + this.healing + " de cura passiva!");
    }
}
