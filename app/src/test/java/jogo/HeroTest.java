package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {

    @Test
    public void testHeroInitialStats() {
        Hero hero = new Hero("Teste", 100, 10, 5, 50);

        assertEquals("Teste", hero.getName());
        assertEquals(100, hero.getHealth());
        assertEquals(10, hero.getShield());
        assertEquals(5, hero.getEnergy());
    }

    @Test
    public void testLoseEnergySuccess() {
        Hero hero = new Hero("Teste", 100, 0, 10, 0);
        
        hero.loseEnergy(4);
        
        assertEquals(6, hero.getEnergy(), "A energia deveria cair de 10 para 6");
    }

    @Test
    public void testLoseEnergyInsufficient() {
        Hero hero = new Hero("Teste", 100, 0, 3, 0);
        hero.loseEnergy(5);
        assertEquals(3, hero.getEnergy(), "A energia não deve mudar se o custo for maior que o atual");
    }

    @Test
    public void testSetEnergyBehavior() {
        Hero hero = new Hero("Teste", 100, 0, 5, 0);
        hero.setEnergy(999);
        
        assertEquals(10, hero.getEnergy(), "O setEnergy deve definir o valor fixo de 10 conforme o código");
    }

    @Test
    public void testInheritedEntityMethods() {
        Hero hero = new Hero("Teste", 100, 0, 10, 100);
        
        hero.gainShield(20);
        assertEquals(20, hero.getShield());
        
        hero.takeDamage(10);
        assertEquals(10, hero.getShield());
        assertEquals(100, hero.getHealth());
    }
}