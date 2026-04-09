package jogo;

/**
 * Representa um tipo específico de carta focado em causar dano direto a uma entidade.
 * Esta classe estende {@link Cards} e adiciona a lógica de cálculo de dano, 
 * que leva em consideração o bônus de força do usuário.
 */
public class DamageCard extends Cards {
    private int damage;

    /**
     * Construtor para criar uma nova carta de dano.
     *
     * @param name        O nome da carta.
     * @param description A descrição do efeito de ataque.
     * @param cost        O custo de energia para usar a carta.
     * @param damage      O valor base de dano que a carta causa.
     */
    public DamageCard(String name, String description, int cost, int damage) {
        super(name, description, cost);
        this.damage = damage;
    }

    /**
     * Obtém o valor base de dano da carta (sem bônus).
     *
     * @return O dano base.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Executa a ação de ataque. O dano final é calculado somando o dano base 
     * da carta com o bônus de força fornecido pela entidade usuária.
     * Após o cálculo, a entidade inimiga recebe o dano e uma mensagem é exibida no console.
     *
     * @param user  A entidade que está atacando (cujo bônus de força será aplicado).
     * @param enemy A entidade que sofrerá o dano.
     */
    @Override
    public void use(Entity user, Entity enemy, Publisher publisher) {
        int bonus = user.getStrengthBonus();
        int finalDamage = this.damage + bonus;
        enemy.takeDamage(finalDamage);
        System.out.println(enemy.name + " recebeu " + finalDamage + " de dano!");
    }
}