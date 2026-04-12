package jogo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaTest {

    @Test
    public void testSalaGetters() {

        Enemy inimigo = new Enemy("Goblin", 50, 0, 50, 10);
        String nomeSala = "Masmorra Sombria";
        String arquivo = "sala1.txt";
        Sala sala = new Sala(nomeSala, inimigo, arquivo);

        assertEquals(nomeSala, sala.getNome(), "O nome da sala deve ser igual ao passado no construtor");
        assertEquals(inimigo, sala.getInimigo(), "O inimigo deve ser o mesmo objeto instanciado");
        assertEquals(arquivo, sala.getFileTxt(), "O caminho do arquivo TXT deve estar correto");
    }
}