package jogo;

import java.util.Scanner;

public abstract class Evento {
    public abstract void start(Hero explorer, CardsManager deckSystem, Scanner reader);
}