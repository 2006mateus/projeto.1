package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassiveHealingCardTest {

    @Test
    public void testPassiveHealingCardUsage() {

        Hero user = new Hero("Clérigo", 50, 0, 10, 100);
        Publisher pub = new Publisher();
        PassiveHealingCard card = new PassiveHealingCard("Prece", "Regen", "Cura 10 + Regen", 1, 10, 2);

        user.takeDamage(20);
        card.use(user, null, pub);
        assertEquals(40, user.getHealth(), "A cura imediata de 10 deveria ser aplicada");
        assertFalse(user.effectsList.isEmpty(), "O efeito de cura passiva deve estar na lista do usuário");
        assertEquals("Regen", user.effectsList.get(0).name);
    }

    @Test
    public void testPassiveHealingCardAttributes() {
        PassiveHealingCard card = new PassiveHealingCard("Teste", "Efeito", "Desc", 3, 5, 1);
        
        assertEquals("Teste", card.getName());
        assertEquals(3, card.getCost());
        assertEquals("Desc", card.getDescription());
    }
}