public class Strength extends Effects{
    private int strengthing = 10;

    public Strength(String name, Entity owner, int stacks, int strengthing){
        super(name, owner, stacks);
        this.strengthing = strengthing;
    }

    @Override
    public void getNotify(){

        if (this.stacks <= 0){
            return;
        }

        this.stacks -= 1;

        if (owner.shield == 0){
            if (owner.health >= strengthing){
                owner.health -= strengthing;
            } else{
                owner.health = 0;
            }
        } else {
            if (owner.shield >= strengthing){
                owner.shield -= strengthing;
            } else{
                owner.health -= strengthing - owner.shield;
                owner.shield = 0;
            }
        }
    }
}
