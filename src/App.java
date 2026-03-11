import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int comandos = -1;
        boolean jogando = true;

        Scanner leitor = new Scanner (System.in);

        System.out.println("Digite o nome do heroi");
        String nome = leitor.nextLine();

        Heroi heroi = new Heroi();
        heroi.nome = nome;
        heroi.vida = 40;
        heroi.vivo = true;

        Inimigo rato = new Inimigo();
        rato.nome = "rato";
        rato.vida = 20;
        rato.vivo = true;
        rato.ataque = 5;

        CartaEscudo protecao = new CartaEscudo();
        protecao.nome = "bandagem";
        protecao.custo = 5;
        protecao.escudo = 5;

        CartaDano arma = new CartaDano();
        arma.nome = "tiro";
        arma.custo = 3;
        arma.dano = 5;

        while(jogando == true) {
            heroi.escudo = 0;
            heroi.energia = 30;

            while (comandos != 0 && heroi.energia != 0) {
                System.out.println(heroi.nome + " (" + heroi.vida + "/40)" + "   (" + heroi.escudo + "/5)");
                System.out.println("vs");
                System.out.println(rato.nome + " (" + rato.vida + "/20)");

                System.out.println();

                System.out.println(heroi.energia + "/" + "30 de Energia disponivel");
                System.out.println("1 - Usar " + arma.nome);
                System.out.println("2 - Usar " + protecao.nome);
                System.out.println("3 - Encerrar turno");
                System.out.println("0 - Sair do jogo");

                comandos = leitor.nextInt();

                if (comandos == 1) {
                    if (heroi.energia < arma.custo) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        rato.receber_dano_inimigo(arma.dano, rato);
                        arma.usar(rato, heroi);
                        if (rato.vida == 0) {
                            rato.vida = 20; /*só vem outro rato pq ainda nao tem outros inimigos*/
                            System.out.println(rato.nome + " foi aniquilado!");
                        }
                    }
                } else if (comandos == 2) {
                    if (heroi.energia < protecao.custo) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        heroi.ganhar_escudo(protecao.escudo, heroi);
                        protecao.usar(heroi);
                    }
                } else if (comandos == 3) {
                    if (heroi.vivo == false) {
                        jogando = false;
                    }
                    break;
                }
            }
            if (rato.vida != 20) { /*evita receber ataque de um rato recem-chegado por conta da falta de energia*/
                heroi.receber_dano(rato.ataque, heroi);
            } else if (comandos == 3) {
                rato.atacar(heroi, rato.ataque);
            }
            if (heroi.vida == 0) {
                heroi.vivo = false;
                comandos = 0;
                jogando = false;
                System.out.println(heroi.nome + " foi derrotado por " + rato.nome + " e foi para casa!");
                break;
            } else if (comandos == 0) {
                System.out.println(heroi.nome + " saiu do jogo!");
                break;
            }
        }

        leitor.close();
    }
}