import java.util.Random;

public class Enemy extends Entity {
    private int damage;
    
    private Random gerador = new Random(); 

    public Enemy(String name, int health, int shield, int damage, int maxShield){
        super(name, health, shield, maxShield);
        this.damage = damage;
    }

    public void atack(Hero hero, Publisher publisher){
        hero.takeDamage(damage);

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

            if (jaTemVeneno == false) {
                Venom veneno = new Venom("Veneno de Rato", hero, 2, 5);
                hero.applyEffect(veneno, hero); 
                publisher.subscribe(veneno);
            }
        }
    }

    public int getDamage() {
        return this.damage;
    }
}