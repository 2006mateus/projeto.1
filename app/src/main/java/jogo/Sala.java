package jogo;

public class Sala {
    private String nome;
    private Enemy inimigo;
    private String fileTxt;

    public Sala(String nome, Enemy inimigo, String fileTxt) {
        this.nome = nome;
        this.inimigo = inimigo;
        this.fileTxt = fileTxt;
    }

    public String getNome() { return nome; }
    public Enemy getInimigo() { return inimigo; }
    public String getFileTxt() { return fileTxt; }
}