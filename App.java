import java.util.Scanner;

public class App
{
    public static void main(String[] args){
        Scanner leitor = new Scanner (System.in);

        String nome = leitor.nextLine();
        int vida = leitor.nextInt();
        int escudo = leitor.nextInt();

        Heroi heroi = new Heroi();
        heroi.nome = nome;
        heroi.escudo = escudo;
        heroi.vida = vida;
        heroi.vivo = 1;


        

        leitor.close();
    }
}