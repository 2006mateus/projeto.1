package jogo;

/**
 * Representa uma carta de ataque que causa dano direto e aplica um efeito 
 * de veneno (dano ao longo do tempo) ao inimigo.
 * * <p>O dano imediato da carta é influenciado pelo bônus de força atual do usuário,
 * enquanto o efeito de veneno subsequente é registrado no {@link Publisher} 
 * para causar dano periódico ao alvo.</p>
 */
public class VenomCard extends Cards {
    private int damage;
    private int hitPoints;
    private int stacks;
    private String effectName;

    /**
     * Construtor para a carta de Veneno.
     * * @param name        O nome da carta.
     * @param effectName  O nome do efeito de status que aparecerá no inimigo.
     * @param description O texto descritivo com os detalhes de dano e veneno.
     * @param cost        O custo de recursos para utilizar a carta.
     * @param damage      O dano imediato (base) da carta.
     * @param hitPoints   O dano por turno que o veneno causará.
     * @param stacks      A duração (em cargas) do efeito de veneno.
     */
    public VenomCard(String name, String effectName, String description, int cost, int damage, int hitPoints, int stacks, int price) {
        super(name, description, cost, price);
        this.damage = damage;
        this.hitPoints = hitPoints;
        this.effectName = effectName;
        this.stacks = stacks;
    }

    /**
     * Executa a lógica de ataque da carta. 
     * <p>Calcula o dano final somando o bônus de força do {@code user}, aplica-o ao 
     * {@code enemy} e, em seguida, cria e inscreve o efeito de {@link Venom} no 
     * sistema de notificações.</p>
     * * @param user      A {@link Entity} que utiliza a carta e fornece o bônus de força.
     * @param enemy     A {@link Entity} alvo que receberá o dano e o efeito de veneno.
     * @param publisher O sistema de eventos que processará o dano do veneno nos turnos seguintes.
     */
    @Override
    public void use(Entity user, Entity enemy, Publisher publisher) {
        System.out.println(this.name + " atingiu o inimigo e aplicou Veneno!");
        int bonus = user.getStrengthBonus();
        int finalDamage = this.damage + bonus;
        enemy.takeDamage(finalDamage);
        Venom venom = new Venom(effectName, enemy, stacks, hitPoints);
        user.applyEffect(venom, enemy);
        publisher.subscribe(venom);
    }
}
