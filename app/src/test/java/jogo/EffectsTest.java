package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EffectsTest {

    @Test
    public void testBaseEffectsAttributes() {

        Hero owner = new Hero("Heroi", 100, 0, 100, 10, 10);

        Effects effect = new Strength("Força", owner, 3, 5);

        assertEquals("Força", effect.name, "O nome do efeito deve ser Força");
        assertEquals(3, effect.getStacks(), "Deveria ter 3 stacks");
    }

    @Test
    public void testGetStringRepresentation() {
        Hero owner = new Hero("Heroi", 100, 0, 100, 10, 10);
        Effects effect = new Strength("Veneno", owner, 5, 0);

        String expected = "Veneno (Acumulos: 5)";
        assertEquals(expected, effect.getString(), "A representação textual está incorreta");
    }
}