package jogo;

import java.util.Scanner;

public abstract class Evento {
    public abstract boolean start(Hero explorador, Enemy inimigo, CardsManager ds, Scanner s, Publisher p, String fileTxt);
}