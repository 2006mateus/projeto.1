package jogo;
public abstract class Effects extends Subscriber {
    protected String name;
    protected Entity owner;
    protected int stacks;

    public Effects(String name, Entity owner, int stacks){
        this.name = name;
        this.owner = owner;
        this.stacks = stacks;
    }

    public String getString(){
        return name + " (Acumulos: " + stacks + ")";
    }
}
