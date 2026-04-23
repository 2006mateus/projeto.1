package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DamageCardTest {

    @Test
    public void testDamageWithStrengthBonus() {

        Hero user = new Hero("Explorador", 100, 0, 100, 0, 10);
        Enemy enemy = new Enemy("Rato", 50, 0, 50, 0);
        Publisher pub = new Publisher();

        DamageCard card = new DamageCard("Golpe Forte", "Dano", 1, 10, 1);

        card.use(user, enemy, pub);
        assertEquals(40, enemy.getHealth(), "Deveria causar 10 de dano (50 - 10)");

        Strength forca = new Strength("Poder", user, 1, 5); 
        user.applyEffect(forca, user);

        card.use(user, enemy, pub);

        assertEquals(25, enemy.getHealth(), "Deveria causar 15 de dano (10 base + 5 força)");
    }

    @Test
    public void testGetDamageGetter() {
        DamageCard card = new DamageCard("Espada", "Desc", 1, 20, 1);
        assertEquals(20, card.getDamage());
    }
}