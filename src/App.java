import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int comandos = -1;
        boolean jogando = true;

        Scanner leitor = new Scanner (System.in);

        System.out.println("Digite o nome do heroi");
        String nome = leitor.nextLine();

        Entidade explorador = new Heroi(nome, 100, 0, 10);
        Entidade rato = new Inimigo("rato bebe", 30, 0, 15);

        Cartas bastao = new CartaDano("bastao", "Um bastao enferrujado, ele aparenta estar bem proximo de quebrar.", 3, 10);
        Cartas luva = new CartaEscudo("luva velha", "Uma luva velha, aparenta ter sido para algum esporte ha muito tempo.", 20, 5);

        while(jogando == true) {
            explorador.setEscudo(0);
            explorador.setEnergia(10);

            while (comandos != 0 && explorador.getEnergia() != 0) {
                System.out.println(explorador.getNome() + " (" + explorador.getVida() + "/40)" + "   (" + explorador.getEscudo() + "/5)");
                System.out.println("vs");
                System.out.println(rato.getNome() + " (" + rato.getVida() + "/20)");

                System.out.println();

                System.out.println(explorador.getEnergia() + "/" + "10 de Energia disponivel");
                System.out.println("1 - Usar " + bastao.getNome());
                System.out.println("2 - Usar " + luva.getNome());
                System.out.println("3 - Encerrar turno");
                System.out.println("0 - Sair do jogo");

                comandos = leitor.nextInt();

                if (comandos == 1) {
                    if (explorador.getEnergia() < bastao.getCusto()) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        rato.receber_dano_inimigo(bastao.getDano());
                        bastao.usar(explorador);
                        if (rato.getVida() == 0) {
                            rato.setVida(20); /*só vem outro rato pq ainda nao tem outros inimigos*/
                            System.out.println(rato.getNome() + " foi aniquilado!");
                        }
                    }
                } else if (comandos == 2) {
                    if (explorador.getEnergia() < luva.getCusto()) {
                        System.out.println("Nao ha energia suficiente");
                    } else {
                        explorador.ganharEscudo(luva.getEscudo());
                        bastao.usar(explorador);
                    }
                } else if (comandos == 3) {
                    if (explorador.estaVivo() == false) {
                        jogando = false;
                    }
                    break;
                }
            }
            if (rato.getVida() != 20) { /*evita receber ataque de um rato recem-chegado por conta da falta de energia*/
                explorador.receber_dano(rato.getAtaque());
            } else if (comandos == 3) {
                rato.atacar(explorador);
            }
            if (explorador.estaVivo() == false) {
                comandos = 0;
                jogando = false;
                System.out.println(explorador.getNome() + " foi derrotado por " + rato.getNome() + " e foi para casa!");
                break;
            } else if (comandos == 0) {
                System.out.println(explorador.getNome() + " saiu do jogo!");
                break;
            }
        }

        leitor.close();
    }
}