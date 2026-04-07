package jogo;

/**
 * Representa um efeito de fortalecimento (buff) que aumenta o dano causado pela entidade.
 * Esta classe estende {@link Effects} e fornece um bônus numérico de força que é 
 * consultado pelas cartas de ataque durante o cálculo de dano final.
 * <p>O efeito possui uma duração baseada em turnos (stacks), diminuindo a cada 
 * notificação do sistema.</p>
 */
public class Strength extends Effects {
    /** O valor fixo de bônus de dano que este efeito proporciona. */
    private int strengthing;

    /**
     * Construtor para criar um novo efeito de força/foco.
     *
     * @param name        O nome do efeito (ex: "Foco", "Fúria").
     * @param owner       A entidade (geralmente o Herói) que receberá o bônus.
     * @param stacks      A duração do efeito em turnos.
     * @param strengthing O valor do bônus de dano a ser adicionado aos ataques.
     */
    public Strength(String name, Entity owner, int stacks, int strengthing) {
        super(name, owner, stacks);
        this.strengthing = strengthing;
    }

    /**
     * Obtém o valor atual de fortalecimento.
     *
     * @return O bônus de dano.
     */
    public int getStrengthening() {
        return this.strengthing;
    }

    /**
     * Reage à notificação do {@link Publisher} (geralmente na passagem de turno).
     * <p>Se ainda houver acúmulos (stacks), decrementa um turno da duração do efeito 
     * e exibe uma mensagem informativa sobre o bônus de dano para o próximo ataque.</p>
     */
    @Override
    public void getNotify() {
        if (this.stacks <= 0) {
            return;
        }
        
        this.stacks -= 1;
        System.out.println("O efeito de Foco vai levar a um aumento de " + this.strengthing + " no dano de seu proximo ataque!");
    }
}