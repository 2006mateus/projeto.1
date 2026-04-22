package jogo;

import java.util.ArrayList;

/**
 * Classe abstrata que serve como base para qualquer ser vivo no jogo (Heróis e Inimigos).
 * <p>
 * Centraliza o gerenciamento de atributos vitais (HP e Escudo), limites máximos e 
 * o sistema de modificadores de status (efeitos). Implementa a lógica fundamental 
 * de combate, como o cálculo de mitigação de dano por escudo.
 * </p>
 */
public abstract class Entity {
    
    /** Nome identificador da entidade. */
    protected String name;
    
    /** Pontos de vida atuais. */
    protected int health;
    
    /** Pontos de escudo atuais que protegem a vida. */
    protected int shield;
    
    /** Valor máximo de vida que não pode ser ultrapassado por curas. */
    protected final int MAX_HEALTH;
    
    /** Limite máximo de acumulação de escudo permitido. */
    protected final int MAX_SHIELD;
    
    /** Lista de efeitos ativos (ex: Veneno, Força) que afetam o comportamento da entidade. */
    protected ArrayList<Effects> effectsList = new ArrayList<>();

    /**
     * Construtor para inicializar uma nova entidade com seus limites e valores iniciais.
     *
     * @param name      O nome da entidade.
     * @param health    A vida inicial (define também o {@code MAX_HEALTH}).
     * @param shield    O valor inicial de escudo.
     * @param maxShield O limite máximo de escudo permitido ({@code MAX_SHIELD}).
     */
    public Entity(String name, int health, int shield, int maxShield) {
        this.name = name;
        this.health = health;
        this.shield = shield;
        this.MAX_HEALTH = health;
        this.MAX_SHIELD = maxShield;
    }

    /**
     * Processa a redução de recursos vitais com base em um valor de dano bruto.
     * <p>
     * A lógica segue a prioridade:
     * 1. O dano atinge o escudo primeiro.
     * 2. Se o dano exceder o escudo, o restante é subtraído da vida.
     * 3. Se o dano total for maior que a soma de vida e escudo, a vida é zerada.
     * </p>
     *
     * @param damage A quantidade de dano a ser aplicada.
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
     * Incrementa o valor do escudo atual, respeitando o limite máximo definido.
     *
     * @param shield Quantidade de pontos de escudo a adicionar.
     */
    public void gainShield(int shield) {
        if (this.shield + shield > this.MAX_SHIELD) {
            this.shield = MAX_SHIELD;
        } else {
            this.shield += shield;
        }
    }

    /**
     * Incrementa a vida atual, impedindo que ultrapasse o valor de vida máxima.
     * * @param healing Quantidade de pontos de vida a restaurar.
     */
    public void gainHealth(double healing) {
        if (this.health + healing > this.MAX_HEALTH) {
            this.health = MAX_HEALTH;
        } else {
            this.health += healing;
        }
    }

    /**
     * Calcula a quantidade real de cura que a entidade pode receber antes de atingir o limite.
     * <p>
     * Útil para exibir mensagens de interface que mostram apenas quanto de HP foi 
     * efetivamente recuperado.
     * </p>
     *
     * @param healing O valor bruto de cura planejado.
     * @return O valor de cura que efetivamente será aproveitado.
     */
    public int getFinalHealing(int healing) {
        if (this.health + healing > this.MAX_HEALTH) {
            return (this.health + healing) - this.MAX_HEALTH;
        } else {
            return healing;
        }
    }

    /**
     * Aplica um efeito de status à entidade.
     * <p>
     * Caso o efeito (identificado pelo nome) já exista na lista, o número de 
     * cargas (stacks) é incrementado. Caso contrário, o efeito é registrado na lista.
     * </p>
     *
     * @param effect O efeito a ser aplicado.
     * @param npc    A entidade alvo (geralmente {@code this}).
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
     * Remove um efeito específico da lista de efeitos ativos da entidade.
     *
     * @param effect O efeito a ser removido.
     * @param npc    A entidade alvo da remoção.
     */
    public void removeEffect(Effects effect, Entity npc){
        npc.effectsList.remove(effect);
    }

    /**
     * Verifica se a entidade está viva.
     *
     * @return {@code true} se a vida atual for maior que zero; {@code false} caso contrário.
     */
    public Boolean isAlive() {
        return this.health > 0;
    }

    /** @return O nome da entidade. */
    public String getName() {
        return this.name;
    }

    /** @return O valor atual de pontos de vida. */
    public int getHealth() {
        return this.health;
    }

    /** @return O valor atual de pontos de escudo. */
    public int getShield() {
        return this.shield;
    }

    /** @param health Define um novo valor para a vida atual. */
    public void setHealth(int health) {
        this.health = health;
    }

    /** @param shield Define um novo valor para o escudo atual. */
    public void setShield(int shield) {
        this.shield = shield;
    }

    /**
     * Varre a lista de efeitos ativos para encontrar bônus de força.
     * <p>
     * Verifica se há algum objeto que seja instância da classe {@code Strength} 
     * e retorna o seu valor de modificador.
     * </p>
     *
     * @return O valor total de bônus de fortalecimento, ou 0 se nenhum estiver ativo.
     */
    public int getStrengthBonus() {
        int totalBonus = 0;

        for (int i = this.effectsList.size() - 1; i >= 0; i--) {
            Effects effect = this.effectsList.get(i);
            
            if (effect instanceof Strength) {
                Strength strength = (Strength) effect;
                totalBonus = strength.getStrengthening();
                strength.stacks -= 1; 

                if (strength.getStacks() <= 0) {
                    this.effectsList.remove(i);
                }
                
                return totalBonus;
            }
        }
        return 0;
    }
}