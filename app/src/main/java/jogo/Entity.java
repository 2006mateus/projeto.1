package jogo;

import java.util.ArrayList;

/**
 * Classe abstrata que representa qualquer entidade viva no jogo (Heróis e Inimigos).
 * Gerencia os atributos vitais como pontos de vida, escudo e a lista de efeitos de status 
 * ativos. Possui a lógica base para recebimento de dano e gerenciamento de bônus.
 */
public abstract class Entity {
    protected String name;
    protected int health;
    protected int shield;
    /** Valor máximo de vida, definido na criação da entidade. */
    protected final int MAX_HEALTH;
    /** Limite máximo de escudo que a entidade pode acumular. */
    protected final int MAX_SHIELD;
    /** Lista de efeitos de status (buffs/debuffs) atualmente aplicados à entidade. */
    protected ArrayList<Effects> effectsList = new ArrayList<>();

    /**
     * Construtor base para uma entidade.
     *
     * @param name      O nome da entidade.
     * @param health    A vida inicial (que também define a vida máxima).
     * @param shield    O valor inicial de escudo.
     * @param maxShield O limite máximo de escudo permitido.
     */
    public Entity(String name, int health, int shield, int maxShield) {
        this.name = name;
        this.health = health;
        this.shield = shield;
        this.MAX_HEALTH = health;
        this.MAX_SHIELD = maxShield;
    }

    /**
     * Processa o dano recebido pela entidade seguindo a hierarquia: 
     * primeiro o escudo é reduzido, e apenas o dano restante afeta a vida.
     *
     * @param damage A quantidade de dano bruto a ser aplicada.
     */
    public void takeDamage(int damage) {
        if (damage >= (this.health + this.shield)) {
            this.health = 0;
        } else if (this.shield >= damage) { 
            this.shield -= damage;
        } else {
            this.health = this.health + this.shield - damage;
            this.shield = 0;
        }
    }

    /**
     * Adiciona pontos de escudo à entidade, respeitando o limite definido por MAX_SHIELD.
     *
     * @param shield A quantidade de escudo a ser adicionada.
     */
    public void gainShield(int shield) {
        if (this.shield + shield > this.MAX_SHIELD) {
            this.shield = MAX_SHIELD;
        } else {
            this.shield += shield;
        }
    }
    /**
     * Adiciona pontos de vida à entidade, respeitando o limite definido por MAX_HEALTH
     * 
     * @param health A quantidade de vida a ser adicionada
     */

    public void gainHealth(int health) {
        if (this.health + health > this.MAX_HEALTH) {
            this.health = MAX_HEALTH;
        } else {
            this.health += health;
        }
    }

    /**
     * Aplica um efeito de status à entidade. Se o efeito já existir (mesmo nome), 
     * os acúmulos (stacks) são somados. Caso contrário, o novo efeito é adicionado à lista.
     *
     * @param effect O objeto de efeito a ser aplicado.
     * @param npc    A entidade que receberá o efeito.
     */
    public void applyEffect(Effects effect, Entity npc){
        boolean exist = false;
        for (int i = 0; i < npc.effectsList.size(); i++) {
            if (npc.effectsList.get(i).name.equals(effect.name)) {
                npc.effectsList.get(i).stacks += effect.stacks;
                exist = true;
                break;
            }
        }

        if (!exist) {
            npc.effectsList.add(effect);
        }
    }

    /**
     * Verifica se a entidade ainda possui pontos de vida.
     *
     * @return true se a vida for maior que 0, false caso contrário.
     */
    public Boolean isAlive() {
        return this.health > 0;
    }

    /** @return O nome da entidade. */
    public String getName() {
        return this.name;
    }

    /** @return A quantidade atual de pontos de vida. */
    public int getHealth() {
        return this.health;
    }

    /** @return A quantidade atual de escudo. */
    public int getShield() {
        return this.shield;
    }

    /** @param health Novo valor de vida. */
    public void setHealth(int health) {
        this.health = health;
    }

    /** @param shield Novo valor de escudo. */
    public void setShield(int shield) {
        this.shield = shield;
    }

    /**
     * Percorre a lista de efeitos ativos em busca de um bônus de força.
     *
     * @return O valor do bônus de fortalecimento se o efeito {@link Strength} 
     * estiver ativo, ou 0 caso contrário.
     */
    public int getStrengthBonus() {
        for (Effects e : this.effectsList) {
            if (e instanceof Strength) {
                return ((Strength) e).getStrengthening();
            }
        }
        return 0;
    }
}