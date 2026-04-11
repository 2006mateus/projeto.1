package jogo;

/**
 * Representa um efeito negativo (debuff) de veneno aplicado a uma entidade.
 * Esta classe estende {@link Effects} e implementa uma lógica de dano recorrente 
 * que é disparada a cada notificação de turno.
 * <p>O veneno reduz a vida ou o escudo da entidade afetada (owner) com base no 
 * valor de dano definido, consumindo um acúmulo (stack) a cada ativação.</p>
 */
public class Venom extends Effects {
    /** O valor de dano que será aplicado à entidade em cada ativação do efeito. */
    private int damage;

    /**
     * Construtor para criar um novo efeito de veneno.
     *
     * @param name   O nome do efeito (ex: "Veneno de Rato", "Peçonha").
     * @param owner  A entidade que sofrerá o dano do veneno.
     * @param stacks A duração do efeito em turnos (acúmulos).
     * @param damage A quantidade de dano causada por turno.
     */
    public Venom(String name, Entity owner, int stacks, int damage) {
        super(name, owner, stacks);
        this.damage = damage;
    }

    /**
     * Reage à notificação do {@link Publisher}, aplicando o dano de veneno ao alvo.
     * <p>A lógica de dano do veneno segue estas regras:</p>
     * <ul>
     * <li>Se a entidade não tiver escudo, o dano é aplicado diretamente à vida.</li>
     * <li>Se a entidade tiver escudo, o dano consome primeiro o escudo e o excedente afeta a vida.</li>
     * <li>A cada ativação, um acúmulo (stack) é removido.</li>
     * </ul>
     * Se não houver mais acúmulos, o efeito não realiza nenhuma ação.
     */
    @Override
    public void getNotify() {

        if (this.stacks <= 0) {
            owner.removeEffect(this, owner);
            return;
        }

        this.stacks -= 1;

        if (owner.shield == 0) {
            if (owner.health >= damage) {
                owner.health -= damage;
            } else {
                owner.health = 0;
            }
        } else {
            if (owner.shield >= damage) {
                owner.shield -= damage;
            } else {
                owner.health -= damage - owner.shield;
                owner.shield = 0;
            }
        }

        System.out.println("O efeito de veneno vai dar um dano de " + this.damage + " extra!");
    }
}
