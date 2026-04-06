
public class Strength extends Effects{
    private int strengthing;

    public Strength(String name, Entity owner, int stacks, int strengthing){
        super(name, owner, stacks);
        this.strengthing = strengthing;
    }

    public int getStrengthening() {
        return this.strengthing;
    }

    @Override
    public void getNotify(){
        if (this.stacks <= 0){
            return;
        }
        
        this.stacks -= 1;
        System.out.println("O efeito de Foco vai levar a um aumento de " + this.strengthing + " no dano de seu proximo ataque!");
    }
}
