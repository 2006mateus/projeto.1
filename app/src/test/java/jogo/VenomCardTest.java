package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VenomCardTest {

    @Test
    public void testVenomCardFullEffect() {

        Hero user = new Hero("Teste", 100, 0, 100, 0);
        Enemy enemy = new Enemy("Cobra", 50, 0, 50, 0);
        Publisher pub = new Publisher();
        VenomCard card = new VenomCard("Flecha Venenosa", "Veneno Grave", "Dano 5 + 2 veneno", 1, 5, 2, 3);

        card.use(user, enemy, pub);
        assertEquals(45, enemy.getHealth(), "O dano imediato de 5 deveria ser aplicado");
        assertFalse(enemy.effectsList.isEmpty(), "O inimigo deveria ter um efeito aplicado");
    }

    @Test
    public void testVenomCardWithStrength() {
        Hero user = new Hero("Teste", 100, 0, 100, 0);
        Enemy enemy = new Enemy("Slime", 50, 0, 50, 0);
        VenomCard card = new VenomCard("Nuvem", "Toxico", "Desc", 1, 10, 1, 1);
        
        card.use(user, enemy, new Publisher());
        assertEquals(40, enemy.getHealth(), "O dano base 10 deveria funcionar");
    }
}