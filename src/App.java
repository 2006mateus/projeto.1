import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int comandos = -1;
        boolean jogando = true;

        Scanner leitor = new Scanner (System.in);

        System.out.println("Digite o nome do heroi");
        String nome = leitor.nextLine();

        Heroi heroi = new Heroi(nome, 40);

        Inimigo rato = new Inimigo("rato", 20, 0, true, 5);

        CartaEscudo protecao = new CartaEscudo("bandagem", 5, 5);

        CartaDano arma = new CartaDano("tiro", 3, 5);

        while(jogando == true) {
            heroi.set_escudo(0);
            heroi.set_energia(10);

            while (comandos != 0 && heroi.get_energia() != 0) {
                System.out.println(heroi.get_nome() + " (" + heroi.get_vida() + "/40)" + "   (" + heroi.get_escudo() + "/5)");
                System.out.println("vs");
                System.out.println(rato.getnome() + " (" + rato.getvida(rato) + "/20)");

                System.out.println();

                System.out.println(heroi.get_energia() + "/" + "10 de Energia disponivel");
                System.out.println("1 - Usar " + arma.getnome());
                System.out.println("2 - Usar " + protecao.getnome());
                System.out.println("3 - Encerrar turno");
                System.out.println("0 - Sair do jogo");

                comandos = leitor.nextInt();

                if (comandos == 1) {
                    if (heroi.get_energia() < arma.getcusto()) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        rato.receber_dano_inimigo(arma.getDano());
                        arma.usar(rato, heroi);
                        if (rato.getvida(rato) == 0) {
                            rato.setVida(20); /*só vem outro rato pq ainda nao tem outros inimigos*/
                            System.out.println(rato.getnome() + " foi aniquilado!");
                        }
                    }
                } else if (comandos == 2) {
                    if (heroi.get_energia() < protecao.getcusto()) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        heroi.ganhar_escudo(protecao.getescudo());
                        protecao.usar(heroi);
                    }
                } else if (comandos == 3) {
                    if (heroi.esta_vivo() == false) {
                        jogando = false;
                    }
                    break;
                }
            }
            if (rato.getvida(rato) != 20) { /*evita receber ataque de um rato recem-chegado por conta da falta de energia*/
                heroi.receber_dano(rato.getataque(rato), heroi);
            } else if (comandos == 3) {
                rato.atacar(heroi);
            }
            if (heroi.esta_vivo() == false) {
                comandos = 0;
                jogando = false;
                System.out.println(heroi.get_nome() + " foi derrotado por " + rato.getnome() + " e foi para casa!");
                break;
            } else if (comandos == 0) {
                System.out.println(heroi.get_nome() + " saiu do jogo!");
                break;
            }
        }

        leitor.close();
    }
}