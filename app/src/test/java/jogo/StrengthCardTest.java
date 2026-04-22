package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrengthCardTest {

    @Test
    public void testStrengthCardUsage() {

        Hero user = new Hero("Teste", 100, 0, 100, 0, 10);
        Publisher pub = new Publisher();
        StrengthCard card = new StrengthCard("Grito de Guerra", "Fúria", "Aumenta força em 5", 1, 5, 2);

        card.use(user, null, pub);
        assertFalse(user.effectsList.isEmpty(), "O usuário deveria ter recebido o efeito de força");
        assertEquals("Fúria", user.effectsList.get(0).name, "O nome do efeito aplicado deve ser Fúria");
    }

    @Test
    public void testStrengthCardAttributes() {

        StrengthCard card = new StrengthCard("Foco", "Foco", "Desc", 2, 10, 3);
        
        assertEquals("Foco", card.getName());
        assertEquals(2, card.getCost());
    }
}