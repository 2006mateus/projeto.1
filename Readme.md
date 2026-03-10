# Jogo de cartas inspirado em slay the spire

Neste jogo PvE um herói deve combater seus inimigos usando cartas de dano e de escudo. O jogo é dividido em turnos, ao final de cada um o inimigo ataca o herói causando dano, cuidado! Ao aniquilar completamente um inimigo, outros que estão à espreita irão aparecer para que a jornada continue!

## Arquivos e suas funcionalidades

No App.java principal arquivo do projeto, são criados o herói, inimigo, carta dano e escudo que compõem o jogo, além de conter todos os comandos executados pelo jogador, como atacar, usar escudo, finalizar o turno e sair do jogo

Os outros arquivos contém apenas métodos simples como para as classes herói, inimigo, cartaDano ou cartaEscudo 

## Como compilar e rodar

Compilando:
```
javac -d bin $(find src -name "*.java")
```

Jogando:
```
java -cp bin App
```