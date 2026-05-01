package jogo;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe responsável pela estruturação lógica e montagem do mapa do jogo.
 * <p>
 * O mapa é construído utilizando uma estrutura de árvore não binária (através da classe 
 * {@link DefaultMutableTreeNode}), onde cada nó representa uma {@link Sala}. 
 * O fluxo de jogo é definido pela navegação do jogador a partir do nó raiz (entrada) 
 * até os nós folha (confronto final).
 * </p>
 * @version 1.0
 */
public class Map {

    /** * O ponto de partida do jogador. 
     * Representa a raiz da árvore de salas que contém toda a hierarquia do mapa.
     */
    public DefaultMutableTreeNode entrada;

    /**
     * Organiza e conecta as salas do jogo, definindo a hierarquia de progressão e eventos.
     * <p>
     * O mapa é subdividido em três caminhos principais (Esquerda, Meio e Direita). 
     * Cada rota possui uma sequência única de desafios que incluem:
     * <ul>
     * <li><b>Inimigos:</b> Encontros de combate representados por instâncias de {@link Enemy}.</li>
     * <li><b>Eventos de Escolha:</b> Decisões narrativas através da classe {@link Choice}.</li>
     * <li><b>Fogueiras:</b> Zonas de descanso gerenciadas por {@link Bonfire}.</li>
     * <li><b>Lojas:</b> Pontos de troca de itens via {@link Store}.</li>
     * </ul>
     * Todas as rotas convergem para o Boss final (Elefante) em suas respectivas folhas.
     * </p>
     *
     * @param rato           Inimigo para a sala "Ninho do Rato".
     * @param urso           Inimigo para a sala "Descanso do Urso".
     * @param cabra          Inimigo para a sala "Canto da Cabra".
     * @param kanye          Inimigo para a sala "Canto do verdadeiro GOAT".
     * @param cobra          Inimigo para a sala "Ninho da Cobra".
     * @param macaco         Inimigo para a sala "Árvore do Macaco".
     * @param elefante       O Boss final (Elefante) que encerra o jogo.
     * @param machado        Carta de dano disponível para venda na {@link Store}.
     * @param escudoTatico   Carta de escudo disponível para venda na {@link Store}.
     * @param soro           Carta de cura disponível para venda na {@link Store}.
     */
    public void organizeMap(Enemy rato, Enemy urso, Enemy cabra, Enemy kanye, Enemy cobra, Enemy macaco, Enemy elefante, DamageCard machado, ShieldCard escudoTatico, HealingCard soro){

        // 1. Instanciação dos Nós (Cada nó encapsula um objeto Sala)
        entrada = new DefaultMutableTreeNode(new Sala("Entrada da Floresta"));

        // Bifurcações iniciais
        DefaultMutableTreeNode salaEsquerda = new DefaultMutableTreeNode(new Sala("Caminho da Esquerda"));
        DefaultMutableTreeNode salaMeio = new DefaultMutableTreeNode(new Sala("Caminho do Meio"));
        DefaultMutableTreeNode salaDireita = new DefaultMutableTreeNode(new Sala("Caminho da Direita"));

        // Configuração dos inimigos e artes ASCII nas salas correspondentes
        DefaultMutableTreeNode ninhoRato = new DefaultMutableTreeNode(new Sala("Ninho do Rato", rato, "rato.txt"));
        DefaultMutableTreeNode descansoUrso = new DefaultMutableTreeNode(new Sala("Descanso do Urso", urso, "urso.txt"));

        DefaultMutableTreeNode cantoCabra = new DefaultMutableTreeNode(new Sala("Canto da Cabra", cabra, "cabra.txt"));
        DefaultMutableTreeNode kanyeWest = new DefaultMutableTreeNode(new Sala("Canto do verdadeiro GOAT", kanye, "kanye.txt"));

        DefaultMutableTreeNode ninhoCobra = new DefaultMutableTreeNode(new Sala("Ninho da Cobra", cobra, "cobra.txt"));
        DefaultMutableTreeNode arvoreMacaco = new DefaultMutableTreeNode(new Sala("Árvore do Macaco", macaco, "macaco.txt"));

        // Instâncias do Boss final para cada encerramento de rota
        DefaultMutableTreeNode elefanteEsquerda = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));
        DefaultMutableTreeNode elefanteMeio = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));
        DefaultMutableTreeNode elefanteDireita = new DefaultMutableTreeNode(new Sala("Cachoeira do Elefante", elefante, "elefante.txt"));

        // Configuração de Eventos Globais (Loja, Fogueira e Escolhas)
        Store eventoLoja = new Store(machado, escudoTatico, soro);
        Bonfire eventoFogueira = new Bonfire();
        
        DefaultMutableTreeNode bonfireEsquerda = new DefaultMutableTreeNode(new Sala("Fogueira", eventoFogueira, "bonfire.txt"));
        DefaultMutableTreeNode bonfireMeio = new DefaultMutableTreeNode(new Sala("Fogueira", eventoFogueira, "bonfire.txt"));
        DefaultMutableTreeNode bonfireDireita = new DefaultMutableTreeNode(new Sala("Fogueira", eventoFogueira, "bonfire.txt"));

        DefaultMutableTreeNode lojaEsquerda = new DefaultMutableTreeNode(new Sala("Loja", eventoLoja, "store.txt"));
        DefaultMutableTreeNode lojaMeio = new DefaultMutableTreeNode(new Sala("Loja", eventoLoja, "store.txt"));
        DefaultMutableTreeNode lojaDireita = new DefaultMutableTreeNode(new Sala("Loja", eventoLoja, "store.txt"));

        // Definição de eventos narrativos Choice
        Choice eventoBau = new Choice("Um Baú Suspeito", "Você encontra um baú no meio do caminho...", "Abrir o baú", "Ignorar");
        Choice eventoRato = new Choice("Rato esquisito", "Um rato gigante de terno te encara...", "Fazer amizade", "Atacar");
        Choice eventoHater = new Choice("Hater do Kanye West", "Um fã da Taylor Swift bloqueia o caminho!", "Defender Kanye", "Concordar");

        DefaultMutableTreeNode choices1 = new DefaultMutableTreeNode(new Sala("Um Baú Suspeito", eventoBau));
        DefaultMutableTreeNode choices2 = new DefaultMutableTreeNode(new Sala("Rato esquisito", eventoRato));
        DefaultMutableTreeNode choices3 = new DefaultMutableTreeNode(new Sala("Hater do Kanye West", eventoHater));

        // 2. Montagem da Árvore (Relacionamentos Pai-Filho)
        
        // Conexão da raiz
        entrada.add(salaEsquerda);
        entrada.add(salaMeio);
        entrada.add(salaDireita);

        // Montagem da Rota Esquerda
        salaEsquerda.add(ninhoRato);
        ninhoRato.add(choices2);
        choices2.add(bonfireEsquerda);
        bonfireEsquerda.add(descansoUrso);
        descansoUrso.add(lojaEsquerda);
        lojaEsquerda.add(elefanteEsquerda); 

        // Montagem da Rota Central
        salaMeio.add(cantoCabra);   
        cantoCabra.add(choices3);
        choices3.add(lojaMeio);
        lojaMeio.add(kanyeWest);
        kanyeWest.add(bonfireMeio); 
        bonfireMeio.add(elefanteMeio);   

        // Montagem da Rota Direita
        salaDireita.add(ninhoCobra); 
        ninhoCobra.add(choices1);
        choices1.add(bonfireDireita);
        bonfireDireita.add(arvoreMacaco);
        arvoreMacaco.add(lojaDireita);
        lojaDireita.add(elefanteDireita);
    }
}