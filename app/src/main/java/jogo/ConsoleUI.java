import java.nio.file.Files;
import java.nio.file.Path;

public class ConsoleUI {

    // Códigos ANSI para colorir o texto no terminal do Linux/Mac/Windows moderno
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    /**
     * Limpa o terminal completamente
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa a execução para dar tempo do jogador ler
     * @param milliseconds Tempo em milissegundos (ex: 1000 = 1 segundo)
     */
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Lê um arquivo .txt com a arte ASCII e imprime na tela
     * @param fileName Nome do arquivo (ex: "rato.txt")
     */
    public static void printAsciiArt(String fileName) {
        try {
            // O Gradle usa a pasta resources para arquivos que não são código
            String content = Files.readString(Path.of("src/main/resources/" + fileName));
            System.out.println(content);
        } catch (Exception e) {
            System.out.println("[Arte ASCII não encontrada: " + fileName + "]");
        }
    }
}