public abstract class Effects {
    protected String name;
    protected Entity owner;
    protected int stacks;

    public Effects(String name, Entity owner, int stacks){
        this.name = name;
        this.owner = owner;
        this.stacks = stacks;
    }

    public String getName(){
        return name + " (Acumulos: " + stacks + ")";
    }

    public abstract void getNotify();
}
