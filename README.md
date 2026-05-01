=====================================

DESCRIÇÃO DO PROJETO
--------------------
Este é um jogo de cartas tático PvE (Player vs Environment) desenvolvido em Java, 
unindo a profundidade estratégica de jogos de construção de baralho (deck-building) 
com a progressão aleatória de roguelikes. O projeto foi estruturado para 
demonstrar o uso de Programação Orientada a Objetos (POO) em um ambiente de 
sistema complexo, utilizando herança, polimorfismo e diversos padrões de projeto.

O jogador assume o controle de um herói que deve atravessar uma floresta perigosa, 
tomando decisões em eventos aleatórios, gerenciando sua economia e aprimorando 
seu baralho para sobreviver até o confronto final.


SISTEMA DE EVENTOS E NAVEGAÇÃO
------------------------------
O mundo do jogo é estruturado através de uma árvore não binária de salas. 
A progressão é baseada na escolha do jogador, onde cada nó (Sala) pode 
desencadear diferentes tipos de Eventos:

1. Combates (Battle): Confrontos diretos onde a sobrevivência depende da 
   gestão de cartas e energia.
2. Descanso (Bonfire): Salas de fogueira que permitem ao jogador escolher 
   estratégias de recuperação ou melhoria de atributos através do padrão Strategy.
3. Loja (Store): Um sistema econômico onde o Ouro acumulado pode ser trocado 
   por cartas raras ou para remover cartas indesejadas do baralho.
4. Escolhas (Choice): Eventos narrativos com diálogos que apresentam dilemas 
   morais ou de risco, impactando diretamente os recursos do herói.


MECÂNICAS DE COMBATE
--------------------
O combate ocorre em turnos e é regido por três recursos principais:
- Vida (HP): A saúde do herói. Se chegar a zero, a jornada termina.
- Energia: Recurso limitado por turno, necessário para conjurar cartas.
- Escudo: Proteção temporária que absorve dano antes da vida ser atingida. 
  O escudo expira no início do turno do jogador.

O sistema de status (Buffs e Debuffs) opera sob o padrão Observer, permitindo 
que efeitos como Veneno (dano contínuo), Força (aumento de dano) e Regeneração 
sejam processados de forma modular a cada ciclo de turno.


ESTRUTURA DE CLASSES
--------------------
- App.java: Ponto de entrada que inicializa o estado global e a árvore do mapa.
- Sala.java: Classe contêiner que armazena a lógica do evento e a arte visual.
- Hero.java & Enemy.java: Entidades que gerenciam atributos vitais e status.
- Evento.java (Base): Classe abstrata que padroniza o comportamento de Batalhas, 
  Lojas, Escolhas e Fogueiras.
- CardsManager.java: O motor de cartas que controla o Deck, Mão, Descarte e 
  as regras de compra.
- ConsoleUI.java: Gerencia a imersão visual com suporte a artes ASCII e 
  formatação de cores ANSI via terminal.


HIERARQUIA DE CARTAS (Cards)
----------------------------
As cartas são objetos polimórficos que estendem a classe base 'Cards':
- DamageCard: Ataques diretos influenciados pela Força do usuário.
- ShieldCard: Geração de defesa estratégica.
- HealingCard: Recuperação de vida em momentos críticos.
- StatusCards: Aplicação de efeitos de longa duração (Veneno, Regeneração).


REQUISITOS E EXECUÇÃO
---------------------
O projeto é automatizado via Gradle.

- Para compilar os binários:
  ./gradlew build

- Para iniciar a jornada:
  ./gradlew run

CONTRIBUIÇÃO DE IA GENERATIVA
----------------------------
A documentação Javadoc dos arquivos Java e parte deste README foram feitos com o auxílio de inteligências articifiais generativas (como Gemini). Além disso, esses modelos auxiliaram no aprendizado do uso das bibliotecas de estruturas de dados (como DefaulMutableTreeNode) e dos padrões de projeto (como strategy e observer).