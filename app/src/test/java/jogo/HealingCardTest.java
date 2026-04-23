package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealingCardTest {

    @Test
    public void testHealingEffect() {

        Hero user = new Hero("Teste", 50, 0, 10, 0, 10, 0); 
        Publisher pub = new Publisher();

        HealingCard card = new HealingCard("Poção", "Cura 30", 1, 30, 1);

        user.takeDamage(40);
        card.use(user, null, pub);

        assertEquals(40, user.getHealth(), "A vida deveria subir para 40");
    }

    @Test
    public void testHealingAtMaxHealth() {

        Hero user = new Hero("Teste", 50, 0, 10, 0, 10, 0);
        HealingCard card = new HealingCard("Poção Grande", "Cura 50", 1, 50, 1);

        user.takeDamage(40);
        card.use(user, null, new Publisher());

        assertEquals(50, user.getHealth(), "A vida não deve ultrapassar o máximo de 50");
    }

    @Test
    public void testGetHealingGetter() {
        HealingCard card = new HealingCard("Cura", "Desc", 1, 20, 1);
        // Garante cobertura do getter específico da classe
        assertEquals(20, card.getHealing());
    }
}