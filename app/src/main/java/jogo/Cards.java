package jogo;

/**
 * Classe abstrata que baseia todas as cartas do jogo.
 * Define os atributos fundamentais que qualquer carta deve ter (nome, descrição e custo) 
 * e estabelece o contrato para o método de uso que as subclasses devem implementar.
 */
public abstract class Cards {
    protected String name;
    protected String description;
    protected int cost;

    /**
     * Construtor padrão para inicializar os atributos básicos de uma carta.
     *
     * @param name      O nome de exibição da carta.
     * @param description O texto explicativo sobre o que a carta faz.
     * @param cost     O valor em recursos (ex: energia, mana) necessário para jogar a carta.
     */

    public Cards(String name, String description, int cost){
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    /**
     * Obtém o nome da carta.
     *
     * @return O nome da carta.
     */

    public String getName(){
        return name;
    }

    /**
     * Obtém a descrição do efeito da carta.
     *
     * @return A descrição da carta.
     */

    public String getDescription(){
        return description;
    }

    /**
     * Obtém o custo necessário para jogar a carta.
     *
     * @return O valor do custo da carta.
     */

    public int getCost(){
        return cost;
    }

    /**
     * Executa o efeito da carta. 
     * Como cada carta tem um efeito único (ex: causar dano, dar escudo), 
     * a lógica específica deve ser implementada nas subclasses.
     *
     * @param user   A entidade (Personagem/Herói ou Inimigo) que está jogando a carta.
     * @param entity A entidade que será o alvo do efeito da carta.
     * @param publisher 
     */

    public abstract void use(Entity user, Entity entity, Publisher publisher);
}
