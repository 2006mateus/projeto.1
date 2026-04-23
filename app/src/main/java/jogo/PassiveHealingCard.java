package jogo;

/**
 * Representa uma carta de habilidade que fornece cura imediata e aplica um 
 * efeito de cura passiva (cura ao longo do tempo) ao usuário.
 * * <p>Esta classe estende a classe base {@link Cards} e integra-se ao sistema 
 * de eventos através de um {@link Publisher} para gerenciar a continuidade 
 * do efeito passivo.</p>
 */
public class PassiveHealingCard extends Cards {
    private int healing;
    private int stacks;
    private String effectName;

    /**
     * Construtor para a carta de Cura Passiva.
     * * @param name        O nome visível da carta.
     * @param effectName  O nome do efeito de status que será exibido no alvo.
     * @param description O texto descritivo das habilidades da carta.
     * @param cost        O custo de recursos para jogar a carta.
     * @param healing     A quantidade de cura (imediata e por stack).
     * @param stacks      O número de vezes/turnos que a cura passiva será ativada.
     */
    public PassiveHealingCard(String name, String effectName, String description, int cost, int healing, int stacks, int price) {
        super(name, description, cost, price);
        this.healing = healing;
        this.effectName = effectName;
        this.stacks = stacks;
    }

    /**
     * Executa a lógica da carta: realiza uma cura instantânea no usuário, 
     * instancia um novo efeito de {@link PassiveHealing} e o registra no 
     * sistema de notificações (Publisher).
     * * @param user      A {@link Entity} que está conjurando a carta e receberá os benefícios.
     * @param enemy     A {@link Entity} adversária (não afetada diretamente por esta carta).
     * @param publisher O mediador de eventos que gerenciará os gatilhos do efeito passivo.
     */
    @Override
    public void use(Entity user, Entity enemy, Publisher publisher) {
        System.out.println(this.name + " foi utilizada com sucesso!");
        user.gainHealth(healing);
        PassiveHealing passiveHealing = new PassiveHealing(effectName, user, stacks, healing);
        user.applyEffect(passiveHealing, user);
        publisher.subscribe(passiveHealing);
    }
}
