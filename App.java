import java.util.Scanner;

public class App
{
    public static void main(String[] args){
        int comandos = -1;

        Scanner leitor = new Scanner (System.in);

        System.out.println("Digite o nome do heroi");
        String nome = leitor.nextLine();

        Heroi heroi = new Heroi();
        heroi.nome = nome;
        heroi.vida = 40;
        heroi.vivo = 1;

        Inimigo rato = new Inimigo();
        rato.nome = "Pachioni";
        rato.vida = 20;
        rato.vivo = 1;
        rato.ataque = 5;

        carta_escudo protecao = new carta_escudo();
        protecao.nome = "cavalo";
        protecao.custo = 5;
        protecao.escudo = 5;

        carta_dano arma = new carta_dano();
        arma.nome = "Clarissa";
        arma.custo = 3;
        arma.dano = 5;

        while (comandos != 0){
            heroi.escudo = 0;
            heroi.energia = 30;
            System.out.println(heroi.nome + " (" + heroi.vida + "/40)" + "   (" + heroi.escudo + "/5)");
            System.out.println("vs");
            System.out.println(rato.nome + " (" + rato.vida + "/20)");

            System.out.println(heroi.energia + "/" + "30 de Energia disponivel");
            System.out.println("1 - Usar " + arma.nome);
            System.out.println("2 - Usar " + protecao.nome);
            System.out.println("3 - Encerrar turno");

            comandos = leitor.nextInt();

            if (comandos == 1){
                if (heroi.energia < arma.custo){
                    System.out.println("Nao ha energia suficiente");
                } else {
                    rato.receber_dano_inimigo(arma.dano, rato);
                    heroi.energia -= arma.custo;
                }
            } else if (comandos == 2){
                if (heroi.energia < protecao.custo){
                    System.out.println("Nao ha energia suficiente");
                } else {
                    heroi.ganhar_escudo(protecao.escudo, heroi);
                    heroi.energia -= protecao.custo;
                }
            } else if (comandos == 3){
                heroi.receber_dano(rato.ataque, heroi);
                break;
            }

            if (heroi.vida == 0){
                heroi.vivo = 0;
                comandos = 0;
                break;
            }
        }

        System.out.println(heroi.nome + "foi enrolado por" + rato.nome + " e foi para casa!");


        leitor.close();
    }
}