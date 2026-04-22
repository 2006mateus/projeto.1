package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShieldCardTest {

    @Test
    public void testShieldEffect() {

        Hero user = new Hero("Teste", 100, 0, 10, 50, 10); 
        Publisher pub = new Publisher();

        ShieldCard card = new ShieldCard("Escudo de Ferro", "Defesa", 1, 15);

        card.use(user, user, pub);

        assertEquals(15, user.getShield(), "O escudo deveria ser 15");
    }

    @Test
    public void testShieldLimit() {

        Hero user = new Hero("Teste", 100, 0, 10, 20, 10);
        ShieldCard card = new ShieldCard("Escudo Divino", "Defesa", 1, 30);

        card.use(user, user, new Publisher());

        assertEquals(20, user.getShield(), "O escudo não deve ultrapassar o MAX_SHIELD");
    }

    @Test
    public void testGetShieldGetter() {
        ShieldCard card = new ShieldCard("Teste", "Desc", 1, 10);
        assertEquals(10, card.getShield());
    }
}