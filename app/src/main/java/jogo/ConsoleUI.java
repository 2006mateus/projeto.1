package jogo;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utilitário para gerenciamento da interface de usuário via console.
 * <p>
 * Fornece constantes de cores ANSI para estilização de texto e métodos estáticos 
 * para controle de fluxo visual, como limpeza de tela, pausas dramáticas e 
 * renderização de artes ASCII.
 * </p>
 */
public class ConsoleUI {

    /** Reset de cor para o padrão do terminal. */
    public static final String RESET = "\u001B[0m";
    /** Cor vermelha para alertas ou indicadores de dano. */
    public static final String RED = "\u001B[31m";
    /** Cor verde para vitórias ou indicadores de cura. */
    public static final String GREEN = "\u001B[32m";
    /** Cor azul para informações do herói ou mensagens neutras. */
    public static final String BLUE = "\u001B[34m";
    /** Cor amarela para avisos ou destaque de energia. */
    public static final String YELLOW = "\u001B[33m";
    /** Cor ciano para efeitos especiais ou textos informativos. */
    public static final String CYAN = "\u001B[36m";

    /**
     * Limpa o terminal completamente.
     * <p>
     * Utiliza sequências de escape ANSI para resetar a posição do cursor 
     * e apagar o conteúdo visível no console.
     * </p>
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa a execução da thread atual por um tempo determinado.
     * <p>
     * Utilizado para controlar a velocidade com que as mensagens aparecem 
     * para o jogador, garantindo tempo de leitura adequado.
     * </p>
     *
     * @param milliseconds Tempo de espera em milissegundos.
     */
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Carrega e imprime uma arte ASCII a partir de um arquivo de texto.
     * <p>
     * O método busca o arquivo no diretório {@code src/main/resources/}. 
     * Caso o arquivo não exista ou ocorra erro de leitura, uma mensagem de erro 
     * amigável é exibida no lugar da arte.
     * </p>
     *
     * @param fileName Nome do arquivo de texto (ex: "elefante.txt").
     */
    public static void printAsciiArt(String fileName) {
        try {
            String content = Files.readString(Path.of("src/main/resources/" + fileName));
            System.out.println(content);
        } catch (Exception e) {
            System.out.println("[Arte ASCII não encontrada: " + fileName + "]");
        }
    }
}