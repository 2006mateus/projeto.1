## Jogo de Cartas Insipirado em Slay The Spire

Um jogo de cartas estratégico PvE (Player vs Environment) desenvolvido em Java, fortemente inspirado nas mecânicas de Slay the Spire. O jogador assume o papel de um explorador que deve gerenciar sua energia e deck para derrotar inimigos em combates táticos.

## O Jogo

O objetivo é sobreviver ao ataque dos inimigos utilizando cartas de Ataque e Defesa.

    Energia: O herói começa cada turno com 10 pontos de energia.

    Mão de Cartas: O limite máximo é de 4 cartas simultâneas.

    Sistema de Deck: Quando o baralho acaba, a pilha de descarte é embaralhada e retorna ao jogo (Recycle).

## Classes Principais

Classe	                                      Descrição
App.java	                Gerencia o loop do jogo, entradas do usuário (Scanner) -                                           e a lógica de turnos.
CardsManager.java	    Responsável pela lógica do baralho. Gerencia a compra de cartas, a mão  -                           do jogador, a pilha de descarte e o embaralhamento.
Entity.java	        Classe abstrata base para todos os seres vivos (Herói e Inimigo), contendo vida, -                                          escudo e lógica de dano.
Cards.java	    Classe abstrata que define o modelo base para qualquer carta (nome, descrição e -                                               custo de energia).


## Entidades e Combate

    Hero: O personagem do jogador. Possui um sistema de energia limitado por turno.

    Enemy: O adversário. Possui padrões de ataque automáticos ao final do turno do jogador.

    Sistema de Dano: O dano é mitigado primeiro pelo Escudo. Se o dano exceder o escudo, a Vida é subtraída.

## Como compilar e rodar

Compilando:
```
javac -d bin $(find src -name "*.java")
```

Jogando:
```
java -cp bin App
```