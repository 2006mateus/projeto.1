package jogo;

/**
 * Representa um efeito de cura passiva (Heal over Time) que é ativado através
 * de notificações do sistema de eventos.
 * * <p>O efeito cura o proprietário ({@code owner}) a cada notificação recebida,
 * desde que ele ainda possua cargas (stacks) e não esteja derrotado.</p>
 */
public class PassiveHealing extends Effects {
    private int healing;

    /**
     * Construtor para o efeito de Cura Passiva.
     * * @param name    O nome do efeito (ex: "Regeneração").
     * @param owner   A {@link Entity} que possui o efeito e receberá a cura.
     * @param stacks  O número de vezes que o efeito será acionado antes de expirar.
     * @param healing A quantidade de cura por ativação.
     */
    public PassiveHealing(String name, Entity owner, int stacks, int healing) {
        super(name, owner, stacks);
        this.healing = healing;
    }

    /**
     * Método acionado pelo {@link Publisher} (ou sistema de eventos).
     * * <p>A lógica de ativação segue os seguintes passos:</p>
     * <ul>
     * <li>Verifica se ainda restam stacks disponíveis.</li>
     * <li>Decrementa uma stack do efeito.</li>
     * <li>Verifica se o proprietário ainda está vivo (HP > 0).</li>
     * <li>Aplica a cura respeitando o limite de {@code MAX_HEALTH} da entidade.</li>
     * </ul>
     * * @override
     */
    @Override
    public void getNotify() {
        
        if (this.stacks <= 0) {
            owner.removeEffect(this, owner);
            return;
        }

        this.stacks -= 1;

        if (owner.health == 0) {
            return;
        }

        if (owner.health + healing >= owner.MAX_HEALTH) {
            owner.health = owner.MAX_HEALTH;
        } else {
            owner.health += healing;
        }

        System.out.println("Voce recebeu " + this.healing + " de cura passiva");
    }
}
