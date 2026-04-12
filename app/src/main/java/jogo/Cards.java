package jogo;

/**
 * Classe abstrata que serve como base para todas as cartas do sistema de jogo.
 * <p>
 * Define os atributos fundamentais, como nome, descrição e custo de uso, 
 * garantindo que todas as subclasses (como DamageCard, ShieldCard, etc.) 
 * sigam a mesma estrutura básica.
 * </p>
 */
public abstract class Cards {
    
    /** Nome de exibição da carta. */
    protected String name;
    
    /** Texto descritivo que detalha os efeitos e o lore da carta. */
    protected String description;
    
    /** Custo de pontos de ação ou energia necessário para utilizar a carta. */
    protected int cost;

    /**
     * Construtor para inicializar os atributos básicos de uma carta.
     *
     * @param name        O nome da carta.
     * @param description A descrição detalhada do efeito da carta.
     * @param cost        O custo de uso da carta.
     */
    public Cards(String name, String description, int cost){
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    /**
     * Recupera o nome da carta.
     *
     * @return O nome da carta em formato {@code String}.
     */
    public String getName(){
        return name;
    }

    /**
     * Recupera a descrição do efeito da carta.
     *
     * @return A descrição da carta em formato {@code String}.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Recupera o custo de uso da carta.
     *
     * @return O valor inteiro representando o custo.
     */
    public int getCost(){
        return cost;
    }

    /**
     * Define o comportamento específico de uso da carta.
     * <p>
     * Este método deve ser implementado pelas subclasses para aplicar efeitos 
     * específicos como dano, cura, aplicação de status ou modificadores de defesa.
     * </p>
     *
     * @param user      A entidade (Herói ou Inimigo) que está utilizando a carta.
     * @param target    A entidade alvo que sofrerá os efeitos da carta.
     * @param publisher O sistema de eventos responsável por notificar a interface 
     * ou outros sistemas sobre as ações ocorridas.
     */
    public abstract void use(Entity user, Entity target, Publisher publisher);
}