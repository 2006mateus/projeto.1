package jogo;
public class Venom extends Effects{
    private int damage;

    public Venom(String name, Entity owner, int stacks, int damage){
        super(name, owner, stacks);
        this.damage = damage;
    }

    @Override
    public void getNotify(){

        if (this.stacks <= 0){
            return;
        }

        this.stacks -= 1;

        if (owner.shield == 0){
            if (owner.health >= damage){
                owner.health -= damage;
            } else{
                owner.health = 0;
            }
        } else {
            if (owner.shield >= damage){
                owner.shield -= damage;
            } else{
                owner.health -= damage - owner.shield;
                owner.shield = 0;
            }
        }

        System.out.println("O efeito de veneno vai dar um dano de " + this.damage + " extra!");
    }
}
