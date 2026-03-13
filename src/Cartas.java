public abstract class Cartas {
    private String nome;
    private String descricao;
    private int custo;

    public Cartas(String nome, String descricao, int custo){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getCusto(){
        return custo;
    }

    public void usar(Heroi heroi){
        heroi.perderEnergia(custo);
    }
}
