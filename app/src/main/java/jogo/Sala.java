package jogo;

/**
 * Representa uma unidade de cenário (nó) dentro do mapa do jogo.
 * <p>
 * A classe {@code Sala} funciona como um contêiner que define o que o jogador 
 * encontrará ao navegar pelo mapa. Ela pode conter inimigos para combate, 
 * eventos especiais (como lojas, fogueiras ou escolhas) e referências visuais.
 * </p>
 * @version 1.1
 */
public class Sala {
    
    /** Nome amigável da sala exibido ao jogador durante a navegação. */
    private String nome;
    
    /** O inimigo instanciado para esta sala. Indica um encontro de combate. */
    private Enemy inimigo;
    
    /** O arquivo de texto contendo a arte ASCII para representação visual. */
    private String fileTxt;

    /** O evento lógico (Battle, Store, Bonfire, Choice) associado a esta sala. */
    private Evento event;

    /**
     * Construtor para salas de combate com arte visual.
     *
     * @param nome    Identificador textual da sala.
     * @param inimigo O inimigo presente no local.
     * @param fileTxt O nome do arquivo de arte localizado em {@code resources}.
     */
    public Sala(String nome, Enemy inimigo, String fileTxt) {
        this.nome = nome;
        this.inimigo = inimigo;
        this.event = null;
        this.fileTxt = fileTxt;
    }

    /**
     * Construtor para salas de evento (Loja, Fogueira) com arte visual.
     *
     * @param nome    Nome da sala.
     * @param event   O objeto {@link Evento} a ser disparado.
     * @param fileTxt O nome do arquivo de arte visual.
     */
    public Sala(String nome, Evento event, String fileTxt) {
        this.nome = nome;
        this.inimigo = null;
        this.event = event;
        this.fileTxt = fileTxt;
    }

    /**
     * Construtor para salas vazias ou de transição.
     *
     * @param nome Nome da sala.
     */
    public Sala(String nome) {
        this.nome = nome;
        this.inimigo = null;
        this.event = null;
        this.fileTxt = null;
    }

    /**
     * Construtor para salas de evento narrativo (como {@link Choice}) sem arte visual.
     *
     * @param nome  Nome da sala.
     * @param event O evento a ser disparado.
     */
    public Sala(String nome, Evento event) {
        this.nome = nome;
        this.inimigo = null;
        this.event = event;
        this.fileTxt = null;
    }

    /**
     * @return O nome da sala.
     */
    public String getNome() { 
        return nome; 
    }

    /**
     * @return O objeto {@link Enemy} da sala, ou {@code null} se for uma sala de evento ou vazia.
     */
    public Enemy getInimigo() { 
        return inimigo; 
    }

    /**
     * @return O nome do arquivo de arte ASCII (ex: "rato.txt").
     */
    public String getFileTxt() { 
        return fileTxt; 
    }

    /**
     * @return O objeto {@link Evento} vinculado a esta sala.
     */
    public Evento getEvent() {
        return event;
    }
}