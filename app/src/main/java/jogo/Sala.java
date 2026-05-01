package jogo;

/**
 * Representa uma unidade de cenário (nó) dentro do mapa do jogo.
 * <p>
 * A classe {@code Sala} é um contêiner de dados que armazena as informações 
 * vitais de um local, incluindo seu nome de exibição, o inimigo que o habita 
 * (caso exista) e o arquivo de recurso visual para renderização de arte ASCII.
 * </p>
 */
public class Sala {
    
    /** Nome amigável da sala exibido ao jogador. */
    private String nome;
    
    /** O inimigo instanciado para esta sala. Pode ser {@code null} se a sala for segura. */
    private Enemy inimigo;
    
    /** O nome do arquivo .txt (ex: "urso.txt") que contém a arte ASCII deste cenário. */
    private String fileTxt;

    private Evento event;

    /**
     * Construtor para criar uma nova sala.
     *
     * @param nome    Identificador textual da sala.
     * @param inimigo O objeto do inimigo presente no local (ou {@code null} se vazio).
     * @param fileTxt O nome do arquivo de arte localizado em {@code resources}.
     */
    public Sala(String nome, Enemy inimigo, String fileTxt) {
        this.nome = nome;
        this.inimigo = inimigo;
        this.event = null;
        this.fileTxt = fileTxt;
    }


    public Sala(String nome, Evento event, String fileTxt) {
        this.nome = nome;
        this.inimigo = null;
        this.event = event;
        this.fileTxt = fileTxt;
    }

    public Sala(String nome) {
        this.nome = nome;
        this.inimigo = null;
        this.event = null;
        this.fileTxt = null;
    }

    // Adicione este 4º construtor no seu Sala.java
    public Sala(String nome, Evento event) {
        this.nome = nome;
        this.inimigo = null;
        this.event = event;
        this.fileTxt = null; // Como não tem arte, fica null
    }

    /**
     * Recupera o nome da sala.
     * * @return Uma {@code String} contendo o nome do local.
     */
    public String getNome() { 
        return nome; 
    }

    /**
     * Recupera o inimigo presente na sala.
     * * @return O objeto {@link Enemy} da sala, ou {@code null} se não houver combate.
     */
    public Enemy getInimigo() { 
        return inimigo; 
    }

    /**
     * Recupera o nome do arquivo de arte ASCII associado a este cenário.
     * * @return O nome do arquivo com extensão (ex: "chefe.txt").
     */
    public String getFileTxt() { 
        return fileTxt; 
    }

    public Evento getEvent() {
        return event;
    }
}