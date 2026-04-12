package jogo;

import javax.swing.tree.DefaultMutableTreeNode;

public class Map {

    public DefaultMutableTreeNode entrada;

    public void organizeMap(Enemy rato, Enemy urso, Enemy cabra, Enemy kanye, Enemy cobra, Enemy macaco, Enemy elefante){

        // 1. Criando os Nós (Guardando as Salas com os inimigos dentro)
        entrada = new DefaultMutableTreeNode(new Sala("Entrada da Floresta", null, null));

        DefaultMutableTreeNode salaEsquerda = new DefaultMutableTreeNode(new Sala("Caminho da Esquerda", null, null));
        DefaultMutableTreeNode salaMeio = new DefaultMutableTreeNode(new Sala("Caminho do Meio", null, null));
        DefaultMutableTreeNode salaDireita = new DefaultMutableTreeNode(new Sala("Caminho da Direita", null, null));

        DefaultMutableTreeNode ninhoRato = new DefaultMutableTreeNode(new Sala("Ninho do Rato", rato, "rato.txt"));
        DefaultMutableTreeNode descansoUrso = new DefaultMutableTreeNode(new Sala("Descanso do Urso", urso, "urso.txt"));

        DefaultMutableTreeNode cantoCabra = new DefaultMutableTreeNode(new Sala("Canto da Cabra", cabra, "cabra.txt"));
        DefaultMutableTreeNode kanyeWest = new DefaultMutableTreeNode(new Sala("Canto do verdadeiro GOAT", kanye, "kanye.txt"));

        DefaultMutableTreeNode ninhoCobra = new DefaultMutableTreeNode(new Sala("Ninho da Cobra", cobra, "cobra.txt"));
        DefaultMutableTreeNode arvoreMacaco = new DefaultMutableTreeNode(new Sala("Árvore do Macaco", macaco, "macaco.txt"));

        DefaultMutableTreeNode elefanteEsquerda = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));
        DefaultMutableTreeNode elefanteMeio = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));
        DefaultMutableTreeNode elfanteDireita = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));

        // 2. Conectando as rotas da Árvore
        entrada.add(salaEsquerda);
        entrada.add(salaMeio);
        entrada.add(salaDireita);

        salaEsquerda.add(ninhoRato);
        ninhoRato.add(descansoUrso); 
        descansoUrso.add(elefanteEsquerda); 

        salaMeio.add(cantoCabra);   
        cantoCabra.add(kanyeWest);
        kanyeWest.add(elefanteMeio);    

        salaDireita.add(ninhoCobra); 
        ninhoCobra.add(arvoreMacaco);
        arvoreMacaco.add(elfanteDireita);
    }
}