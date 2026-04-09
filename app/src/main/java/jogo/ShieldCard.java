package jogo;

/**
 * Representa uma carta de defesa no jogo.
 * Esta classe estende {@link Cards} e implementa a lógica para conceder 
 * pontos de escudo à entidade que a utiliza, aumentando sua resistência a danos.
 */
public class ShieldCard extends Cards {
    /** A quantidade de pontos de escudo que a carta fornece. */
    private int shield;

    /**
     * Construtor para criar uma nova carta de escudo.
     *
     * @param name        O nome da carta (ex: "Escudo de Madeira").
     * @param description A descrição do efeito defensivo.
     * @param shield      A quantidade de pontos de escudo a serem concedidos.
     * @param cost        O custo de energia para usar a carta.
     */
    public ShieldCard(String name, String description, int cost, int shield) {
        super(name, description, cost);
        this.shield = shield;
    }

    /**
     * Obtém o valor de escudo que esta carta proporciona.
     *
     * @return O valor do escudo.
     */
    public int getShield() {
        return shield;
    }

    /**
     * Executa a ação de defesa. A entidade que utiliza a carta (user) 
     * recebe o valor de escudo definido na carta, respeitando o limite 
     * de {@code MAX_SHIELD} da entidade.
     *
     * @param user A entidade que está jogando a carta e que receberá o escudo.
     * @param hero O alvo da carta
     */
    @Override
    public void use(Entity user, Entity hero, Publisher publisher) {
        user.gainShield(shield);
        System.out.println(user.name + " ganhou " + shield + " de escudo!");
    }
}