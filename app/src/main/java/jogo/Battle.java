package jogo;

import java.util.Scanner;

public class Battle {

    public static boolean startBattle(Hero explorador, Enemy inimigo, CardsManager ds, Scanner s, Publisher p, String fileTxt){
        boolean result = false;
        System.out.println(ConsoleUI.RED + "A batalha começa!" + ConsoleUI.RESET);
        ConsoleUI.pause(1000);

        Interface.run(explorador, inimigo, s, ds, p, fileTxt);

        if (explorador.isAlive()){
            result = true;
            System.out.println(ConsoleUI.GREEN + "Vitoria!" + ConsoleUI.RESET);
            
        } else {
            result = false;
            System.out.println(ConsoleUI.GREEN + "Derrota! Tente denovo outra vez..." + ConsoleUI.RESET);
        }

        ds.clearHand();
        explorador.effectsList.clear();
        inimigo.effectsList.clear();
        p.clearPublisher();

        return result;
    }
}
