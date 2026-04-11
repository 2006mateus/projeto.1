package jogo;

/**
 * Classe abstrata que define a base para todos os efeitos de status (buffs e debuffs) no jogo.
 * Como estende {@link Subscriber}, cada efeito pode reagir a eventos disparados pelo 
 * sistema de jogo (Publisher).
 * * <p>Efeitos possuem um sistema de "stacks" (acúmulos), permitindo que sua intensidade 
 * ou duração varie conforme são aplicados repetidamente.</p>
 */
public abstract class Effects extends Subscriber {
    /** Nome identificador do efeito (ex: "Veneno", "Força"). */
    protected String name;
    
    /** A entidade que está sob o efeito e que será afetada por suas regras. */
    protected Entity owner;
    
    /** A quantidade de acúmulos ou turnos restantes do efeito. */
    protected int stacks;

    /**
     * Construtor para inicializar um novo efeito.
     *
     * @param name   O nome do efeito.
     * @param owner  A entidade à qual este efeito será aplicado.
     * @param stacks A quantidade inicial de acúmulos/intensidade do efeito.
     */
    public Effects(String name, Entity owner, int stacks) {
        this.name = name;
        this.owner = owner;
        this.stacks = stacks;
    }

    /**
     * Retorna uma representação textual do efeito, útil para exibição na interface 
     * ou logs do terminal.
     *
     * @return Uma string contendo o nome do efeito e a quantidade atual de acúmulos.
     */
    public String getString() {
        return name + " (Acumulos: " + stacks + ")";
    }

    /** @return a quantidade de turnos restantes do efeito. */
    public int getStacks() {
        return this.stacks;
    }
}