package jogo;

import java.util.Random;

/**
 * Representa os adversários no jogo.
 * Além dos atributos básicos de uma {@link Entity}, o inimigo possui um valor de dano fixo
 * e a capacidade de aplicar efeitos negativos (como Veneno) aleatoriamente durante seus ataques.
 */
public class Enemy extends Entity {
    /** Valor base de dano que o inimigo causa em cada ataque. */
    private int damage;
    
    /** Gerador de números aleatórios para determinar efeitos especiais de ataque. */
    private Random gerador = new Random(); 

    /**
     * Construtor para instanciar um novo inimigo.
     *
     * @param name      Nome do inimigo (ex: "Rato Gigante").
     * @param health    Pontos de vida iniciais.
     * @param shield    Pontos de escudo iniciais.
     * @param damage    Dano base causado pelo inimigo.
     * @param maxShield Limite máximo de escudo que este inimigo pode acumular.
     */
    public Enemy(String name, int health, int shield, int damage, int maxShield) {
        super(name, health, shield, maxShield);
        this.damage = damage;
    }

    /**
     * Realiza uma ação de ataque contra o herói.
     * O inimigo causa seu dano base e possui uma chance (33%) de aplicar um efeito de veneno.
     * Caso o herói já esteja envenenado, os acúmulos de veneno são incrementados; 
     * caso contrário, um novo efeito de {@link Venom} é criado e registrado no {@link Publisher}.
     *
     * @param hero      O herói que receberá o dano e o possível efeito.
     * @param publisher O gerenciador que registrará o novo efeito de veneno, se aplicado.
     */
    public void atack(Hero hero, Publisher publisher) {
        hero.takeDamage(damage);

        // Gera um número entre 0 e 2. Se for 1, aplica veneno (33,3% de chance).
        int chance = gerador.nextInt(3);

        if (chance == 1) {
            System.out.println("O ataque do " + this.getName() + " estava envenenado!");
            
            boolean jaTemVeneno = false;
            
            for (int i = 0; i < hero.effectsList.size(); i++) {
                if (hero.effectsList.get(i) instanceof Venom) {
                    hero.effectsList.get(i).stacks += 2; 
                    jaTemVeneno = true;
                    System.out.println("O veneno acumulou! Aumentou a duração no herói.");
                    break; 
                }
            }

            if (!jaTemVeneno) {
                Venom veneno = new Venom("Veneno de Rato", hero, 2, 5);
                hero.applyEffect(veneno, hero); 
                publisher.subscribe(veneno);
            }
        }
    }

    /**
     * Obtém o valor de dano base do inimigo.
     *
     * @return O dano causado pelo inimigo.
     */
    public int getDamage() {
        return this.damage;
    }
}