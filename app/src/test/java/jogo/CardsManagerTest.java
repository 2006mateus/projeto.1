package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardsManagerTest {

    @Test
    public void testAddAndGetQuantity() {
        CardsManager manager = new CardsManager();
        DamageCard card = new DamageCard("Ataque", "Dano", 1, 10);
        
        manager.addCard(card);
        
        assertEquals(1, manager.getQuantityDeck(), "O deck deveria ter 1 carta");
        assertEquals(0, manager.getQuantityHand(), "A mão deveria estar vazia");
    }

    @Test
    public void testMoveToPurchasableAndBuy() {
        CardsManager manager = new CardsManager();

        for(int i=0; i<4; i++) manager.addCard(new DamageCard("C"+i, "D", 1, 5));
        
        manager.moveToPurchasable();
        assertEquals(4, manager.getPurchasableQuantity());

        manager.buyCard(1);
        
        assertEquals(1, manager.getQuantityHand(), "Deveria ter 1 carta na mão");
        assertEquals(3, manager.getPurchasableQuantity(), "Deveriam sobrar 3 para compra");
    }

    @Test
    public void testBuyCardInvalidSelection() {
        CardsManager manager = new CardsManager();

        manager.buyCard(99); 
        assertEquals(0, manager.getQuantityHand(), "Não deveria ter comprado nada");
    }

    @Test
    public void testUseCardWithEnergy() {
        CardsManager manager = new CardsManager();
        Hero hero = new Hero("Hero", 100, 0, 10, 0, 10);
        Enemy enemy = new Enemy("Enemy", 50, 0, 50, 0);
        DamageCard card = new DamageCard("Golpe", "Dano", 5, 10);

        manager.addCard(card);
        manager.moveToPurchasable();
        manager.buyCard(1);

        manager.useCard(0, hero, enemy, new Publisher());

        assertEquals(0, manager.getQuantityHand(), "A carta deveria sair da mão");
        assertEquals(5, hero.getEnergy(), "Deveria ter gasto 5 de energia");
        assertEquals(40, enemy.getHealth(), "Inimigo deveria ter sofrido dano");
    }

    @Test
    public void testUseCardWithoutEnergy() {
        CardsManager manager = new CardsManager();
        Hero hero = new Hero("Hero", 100, 0, 2, 0, 10);
        DamageCard card = new DamageCard("Super", "Dano", 5, 10);
        
        manager.addCard(card);
        manager.moveToPurchasable();
        manager.buyCard(1);

        manager.useCard(0, hero, new Enemy("E", 10, 0, 10, 0), new Publisher());

        assertEquals(1, manager.getQuantityHand(), "A carta deveria continuar na mão");
        assertEquals(2, hero.getEnergy(), "Energia não deve mudar");
    }

    @Test
    public void testRecycleAndClear() {
        CardsManager manager = new CardsManager();
        DamageCard card = new DamageCard("C", "D", 1, 1);
        
        manager.discardCard(card);
        manager.recycleDeck();
        
        assertEquals(1, manager.getQuantityDeck(), "A carta deveria ter voltado do descarte para o deck");

        manager.moveToPurchasable();
        manager.buyCard(1);
        manager.clearHand();
        assertEquals(0, manager.getQuantityHand());
    }
}