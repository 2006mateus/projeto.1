public abstract class Cards {
    protected String name;
    protected String description;
    protected int cost;

    public Cards(String nome, String descricao, int custo){
        this.name = nome;
        this.description = descricao;
        this.cost = custo;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getCost(){
        return cost;
    }

    public void use(Hero hero){
        hero.loseEnergy(cost);
    }
}
