package jogo;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe responsável pela estruturação lógica do mapa do jogo.
 * <p>
 * O mapa é construído utilizando uma estrutura de árvore não binária, onde cada nó 
 * representa uma {@link Sala}. O jogador inicia na raiz e navega pelos nós filhos 
 * até atingir as folhas, que representam o confronto final.
 * </p>
 */
public class Map {

    /** * O ponto de partida do jogador. 
     * Representa a raiz da árvore de salas.
     */
    public DefaultMutableTreeNode entrada;

    /**
     * Organiza e conecta as salas do jogo, definindo a hierarquia de progressão.
     * <p>
     * O mapa é dividido em três caminhos principais (Esquerda, Meio e Direita), 
     * cada um com sua própria sequência de inimigos e arquivos de arte ASCII, 
     * culminando em diferentes instâncias do Boss final (Elefante).
     * </p>
     *
     * @param rato     Inimigo para a sala "Ninho do Rato".
     * @param urso     Inimigo para a sala "Descanso do Urso".
     * @param cabra    Inimigo para a sala "Canto da Cabra".
     * @param kanye    Inimigo para a sala "Canto do verdadeiro GOAT".
     * @param cobra    Inimigo para a sala "Ninho da Cobra".
     * @param macaco   Inimigo para a sala "Árvore do Macaco".
     * @param elefante O Boss final que aparece no fim de todas as rotas.
     */
    public void organizeMap(Enemy rato, Enemy urso, Enemy cabra, Enemy kanye, Enemy cobra, Enemy macaco, Enemy elefante){

        // 1. Instanciação dos Nós (Cada nó encapsula um objeto Sala)
        entrada = new DefaultMutableTreeNode(new Sala("Entrada da Floresta", null, null));

        // Níveis de bifurcação inicial
        DefaultMutableTreeNode salaEsquerda = new DefaultMutableTreeNode(new Sala("Caminho da Esquerda", null, null));
        DefaultMutableTreeNode salaMeio = new DefaultMutableTreeNode(new Sala("Caminho do Meio", null, null));
        DefaultMutableTreeNode salaDireita = new DefaultMutableTreeNode(new Sala("Caminho da Direita", null, null));

        // Rota da Esquerda
        DefaultMutableTreeNode ninhoRato = new DefaultMutableTreeNode(new Sala("Ninho do Rato", rato, "rato.txt"));
        DefaultMutableTreeNode descansoUrso = new DefaultMutableTreeNode(new Sala("Descanso do Urso", urso, "urso.txt"));

        // Rota do Meio
        DefaultMutableTreeNode cantoCabra = new DefaultMutableTreeNode(new Sala("Canto da Cabra", cabra, "cabra.txt"));
        DefaultMutableTreeNode kanyeWest = new DefaultMutableTreeNode(new Sala("Canto do verdadeiro GOAT", kanye, "kanye.txt"));

        // Rota da Direita
        DefaultMutableTreeNode ninhoCobra = new DefaultMutableTreeNode(new Sala("Ninho da Cobra", cobra, "cobra.txt"));
        DefaultMutableTreeNode arvoreMacaco = new DefaultMutableTreeNode(new Sala("Árvore do Macaco", macaco, "macaco.txt"));

        // Nós de Finalização (Boss)
        DefaultMutableTreeNode elefanteEsquerda = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));
        DefaultMutableTreeNode elefanteMeio = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));
        DefaultMutableTreeNode elfanteDireita = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));

        // 2. Montagem da estrutura (Definição de Pais e Filhos)
        
        // Conexão da entrada com as três rotas iniciais
        entrada.add(salaEsquerda);
        entrada.add(salaMeio);
        entrada.add(salaDireita);

        // Estruturação do Caminho Esquerdo
        salaEsquerda.add(ninhoRato);
        ninhoRato.add(descansoUrso); 
        descansoUrso.add(elefanteEsquerda); 

        // Estruturação do Caminho Central
        salaMeio.add(cantoCabra);   
        cantoCabra.add(kanyeWest);
        kanyeWest.add(elefanteMeio);    

        // Estruturação do Caminho Direito
        salaDireita.add(ninhoCobra); 
        ninhoCobra.add(arvoreMacaco);
        arvoreMacaco.add(elfanteDireita);
    }
}