package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardsTest {

    @Test
    public void testBaseCardAttributes() {
        Cards card = new DamageCard("Ataque Base", "Desc", 1, 10);
        assertEquals("Ataque Base", card.getName());
        assertEquals("Desc", card.getDescription());
        assertEquals(1, card.getCost());
    }
}