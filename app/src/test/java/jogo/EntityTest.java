package jogo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EntityTest {

    private Hero createTestEntity(int health, int shield) {
        return new Hero("Teste", health, shield, 10, 5, 10, 0);
    }

    @Test
    void testHeroFullState() {
        Entity entity = createTestEntity(100, 10);
        assertEquals("Teste", entity.getName());
        assertEquals(100, entity.getHealth());
        assertEquals(10, entity.getShield());
    }

    @Test
    void testEntityStateChanges() {
        Entity entity = createTestEntity(10, 0);
        entity.setHealth(20);
        entity.setShield(15);    
        assertEquals(20, entity.getHealth(), "O setter de vida falhou");
        assertEquals(15, entity.getShield(), "O setter de escudo falhou");
    }
    
    @Test
    public void testDamageAbsorbedByShield() {
        Entity entity = createTestEntity(10, 5);
        entity.takeDamage(3);
        assertEquals(10, entity.getHealth());
        assertEquals(2, entity.getShield());
    }

    @Test
    public void testPartialDamageWithShield() {
        Entity entity = createTestEntity(10, 5);
        entity.takeDamage(8);
        assertEquals(7, entity.getHealth());
        assertEquals(0, entity.getShield());
    } 
    
    @Test
    public void testDamageWithoutShield() {
        Entity entity = createTestEntity(10, 0);
        entity.takeDamage(5);
        assertEquals(5, entity.getHealth());
        assertEquals(0, entity.getShield());
    }  

    @Test
    public void testLifeDoesntBecomeNegative() {
        Entity entity = createTestEntity(10, 0);
        entity.takeDamage(15);
        assertEquals(0, entity.getHealth());
        assertEquals(0, entity.getShield());
    }

    @Test
    public void testGainShieldBelowMax() {
        Entity entity = createTestEntity(10, 0);
        entity.gainShield(3);
        assertEquals(3, entity.getShield());
    }
    
    @Test
    public void testGainShieldExceedMax() {
        Entity entity = createTestEntity(10, 0);
        entity.gainShield(10);
        assertEquals(entity.MAX_SHIELD, entity.getShield());
    }

    @Test
    public void testGainHealthBelowMax() {
        Entity entity = createTestEntity(5, 0);
        entity.takeDamage(3);
        entity.gainHealth(2);
        assertEquals(4, entity.getHealth());
    }
    
    @Test
    public void testGainHealthExceedMax() {
        Entity entity = createTestEntity(5, 0);
        entity.gainHealth(10);
        assertEquals(entity.MAX_HEALTH, entity.getHealth());
    }

    @Test
    public void testGetFinalHealingNormal() {
        Entity entity = createTestEntity(50, 100);
        assertEquals(20, entity.getFinalHealing(20));
    }

    @Test
    public void testGetFinalHealingOverMax() {
        Entity entity = createTestEntity(90, 100);
        entity.takeDamage(110);
        assertEquals(10, entity.getFinalHealing(20));
    }
    
    @Test
    public void testApplyNewEffect() {
        Entity entity = createTestEntity(100, 100);
        Effects venom = new Venom("Veneno", entity, 2, 5);
        entity.applyEffect(venom, entity);
        assertEquals(1, entity.effectsList.size());
        assertEquals("Veneno", entity.effectsList.get(0).name);
    }

    @Test
    public void testApplyStackingEffect() {
        Entity entity = createTestEntity(100, 100);
        Venom v1 = new Venom("Veneno", entity, 2, 5);
        Venom v2 = new Venom("Veneno", entity, 3, 5);
        
        entity.applyEffect(v1, entity);
        entity.applyEffect(v2, entity);
        
        assertEquals(1, entity.effectsList.size());
        assertEquals(5, entity.effectsList.get(0).stacks);
    }

    @Test
    public void testIsAlive() {
        Entity alive = createTestEntity(10, 10);
        Entity dead = createTestEntity(0, 10);
        
        assertTrue(alive.isAlive());
        assertFalse(dead.isAlive());
    }

    @Test
    public void testGetStrengthBonusActive() {
        Entity entity = createTestEntity(100, 100);
        Strength s = new Strength("Foco", entity, 1, 1); 
        entity.applyEffect(s, entity);
        assertEquals(1, entity.getStrengthBonus());
    }

    @Test
    public void testGetStrengthBonusInactive() {
        Entity entity = createTestEntity(100, 100);
        assertEquals(0, entity.getStrengthBonus());
    }

    @Test
    public void testRemoveEffect() {
        Entity entity = createTestEntity(100, 100);
        Venom v = new Venom("Veneno", entity, 2, 5);
        entity.applyEffect(v, entity);
        entity.removeEffect(v, entity);
        assertTrue(entity.effectsList.isEmpty());
    }
}
