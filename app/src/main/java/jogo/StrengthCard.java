package jogo;

/**
 * Representa uma carta de habilidade que concede um bônus de força (ataque) 
 * ao usuário por um período determinado.
 * * <p>Ao ser utilizada, esta carta cria um efeito de {@link Strength} que é 
 * aplicado à entidade e registrado no {@link Publisher} para controle de duração.</p>
 */
public class StrengthCard extends Cards {
    private int bonus;
    private int stacks;
    private String effectName;

    /**
     * Construtor para a carta de Força.
     * * @param name        O nome da carta.
     * @param effectName  O nome do efeito de status que será exibido.
     * @param description O texto descritivo sobre o aumento de dano.
     * @param cost        O custo de recursos para utilizar a carta.
     * @param bonus       O valor numérico do aumento de força.
     * @param stacks      A quantidade de turnos/ativações que o bônus durará.
     */
    public StrengthCard(String name, String effectName, String description, int cost, int bonus, int stacks) {
        super(name, description, cost);
        this.bonus = bonus;
        this.effectName = effectName;
        this.stacks = stacks;
    }

    /**
     * Executa a ação da carta. Instancia o efeito de força, aplica-o ao usuário 
     * e o inscreve no publicador de eventos.
     * * @param user      A {@link Entity} que receberá o bônus de força.
     * @param enemy     A {@link Entity} adversária (não afetada diretamente no momento do uso).
     * @param publisher O sistema de eventos que gerenciará a expiração das stacks.
     */
    @Override
    public void use(Entity user, Entity enemy, Publisher publisher) {
        System.out.println(this.name + " foi utilizada com sucesso!");
        Strength strength = new Strength(effectName, user, stacks, bonus);
        user.applyEffect(strength, user);
        publisher.subscribe(strength);
    }
}
