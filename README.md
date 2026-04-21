=====================================

DESCRIÇÃO DO PROJETO
--------------------
Este é um jogo de cartas tático PvE (Player vs Environment) desenvolvido em Java, 
inspirado em mecânicas de roguelikes de construção de baralho (deck-building). 
O projeto foi estruturado para demonstrar conceitos avançados de Programação 
Orientada a Objetos (POO), incluindo herança, polimorfismo e padrões de projeto.

O jogador assume o papel de um explorador que navega por um mapa de salas, 
enfrenta inimigos em combates por turnos e deve gerenciar seus recursos (energia 
e vida) para chegar ao fim da jornada.


MECÂNICAS DE JOGO
-----------------
1. Sistema de Turnos:
   - Turno do Herói: O jogador compra cartas e as utiliza consumindo Energia.
   - Turno do Inimigo: O oponente realiza ações automáticas pré-definidas.
   - Fase de Efeitos: Status como Veneno ou Cura são processados pelo sistema.

2. Mapa em Árvore:
   - A progressão não é linear. O jogo utiliza uma estrutura de árvore (Map.java) 
     onde cada nó é uma 'Sala'. O jogador escolhe caminhos entre salas que podem 
     conter diferentes inimigos ou desafios.

3. Combate e Defesa:
   - Além da vida (HP), as entidades possuem 'Escudo'. O escudo absorve o dano 
     antes da vida ser afetada, mas é resetado no início de cada turno do herói.


ESTRUTURA DE CLASSES
--------------------
- App.java: Ponto de entrada que inicializa o herói, o mapa e o loop de navegação.
- Interface.java: Motor de jogo que gerencia a lógica de combate e entradas.
- Battle.java: Mediador que controla o ciclo de vida de um encontro específico.
- CardsManager.java: Gerencia o Deck, Mão, Descarte e a mecânica de 'Recycle'.
- Entity.java (Base): Classe abstrata para Hero e Enemy, gerindo atributos vitais.
- Hero.java: Entidade do jogador com sistema de Energia.
- Enemy.java: Entidade adversária com IA de ataque e chances de aplicar debuffs.
- ConsoleUI.java: Utilitário para artes ASCII, cores ANSI e limpeza de terminal.


CARTAS E EFEITOS
----------------
O jogo possui uma hierarquia de cartas baseada na classe abstrata 'Cards':
- DamageCard: Causa dano direto (amplificado por Força).
- ShieldCard: Concede proteção temporária (Escudo).
- HealingCard: Restaura vida imediatamente.
- VenomCard: Causa dano e aplica status de veneno.
- StrengthCard / PassiveHealingCard: Aplicam buffs duradouros.

PADRÃO DE PROJETO: OBSERVER
---------------------------
O sistema de status (Buffs/Debuffs) utiliza o padrão Observer:
- Publisher: Mantém a lista de efeitos ativos e os notifica a cada turno.
- Subscriber: Classe base para os efeitos.
- Efeitos Implementados: 
    * Venom (Veneno): Dano recorrente que ignora parte da defesa.
    * Strength (Força): Aumenta o dano de todas as cartas de ataque.
    * PassiveHealing: Regeneração de vida ao longo do tempo.


REQUISITOS E EXECUÇÃO
---------------------
O projeto utiliza o Gradle como ferramenta de automação.

- Para compilar:
  ./gradlew build

- Para executar:
  ./gradlew run
