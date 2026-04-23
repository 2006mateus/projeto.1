package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void testEnemyAttackBasic() {

        Enemy enemy = new Enemy("Rato", 50, 0, 10, 0);
        Hero hero = new Hero("Heroi", 100, 0, 100, 0, 10, 0);
        Publisher pub = new Publisher();

        enemy.atack(hero, pub);
        assertEquals(90, hero.getHealth(), "O herói deveria ter recebido 10 de dano");
    }

    @Test
    public void testEnemyVenomChance() {
        Enemy enemy = new Enemy("Cobra", 50, 0, 1, 0);
        Hero hero = new Hero("Heroi", 100, 0, 100, 0, 10, 0);
        Publisher pub = new Publisher();

        for (int i = 0; i < 20; i++) { // deve aplicar alguma hora
            enemy.atack(hero, pub);
        }
        assertFalse(hero.effectsList.isEmpty(), "O veneno deveria ter sido aplicado em algum dos ataques");
    }

    @Test
    public void testEnemyVenomAccumulation() {
        Enemy enemy = new Enemy("Cobra", 50, 0, 0, 0);
        Hero hero = new Hero("Heroi", 100, 0, 100, 0, 10, 0);
        Publisher pub = new Publisher();
        Venom v = new Venom("Veneno Inicial", hero, 2, 5);

        hero.effectsList.add(v);

        for (int i = 0; i < 20; i++) {
            enemy.atack(hero, pub);
            if (v.getStacks() > 2) break;
        }

        assertTrue(v.getStacks() > 2, "As stacks de veneno deveriam ter aumentado");
    }

    @Test
    public void testGetDamage() {
        Enemy enemy = new Enemy("Inimigo", 10, 0, 15, 0);
        assertEquals(15, enemy.getDamage());
    }
}