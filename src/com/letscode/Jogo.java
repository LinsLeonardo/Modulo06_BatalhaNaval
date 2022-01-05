package com.letscode;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    Random random = new Random();
    int indiceUm;
    int indiceDois;
    int linhaJogo;
    int colunaJogo;
    boolean fimDeJogo = false;
    int jogadorAtual = 1;
    int acertosJogador = 0;
    int acertosMaquina = 0;
    Scanner scan = new Scanner(System.in);


    String[][] tabuleiroJogador = new String[10][10];
    String[][] tabuleiroMaquina = new String[10][10];

    public void iniciaJogo(){

        System.out.println("Bem vindo ao jogo Batalha Naval!");
        System.out.println();
        System.out.println("Siga a legenda abaixo:");
        System.out.println("O - casa vazia (água) que ainda não foi jogada");
        System.out.println("N - casa onde os navios estão posicionados");
        System.out.println("* - casa onde havia um navio mas foi acertado por um tiro");
        System.out.println("X - casa onde foi dado um tiro na água");
        System.out.println();

        geraTabuleiros();
        populaNavios();
        printaTabuleiros();

        do {
            jogadaAtual(jogadorAtual);
            printaTabuleiros();
            verificaFimDeJogo();
        } while (!fimDeJogo);
    }

    public void geraTabuleiros(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                tabuleiroJogador[i][j] = "O";
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                tabuleiroMaquina[i][j] = "O";
            }
        }
    }

    public void populaNavios(){
        for (int i = 0; i < 8; i++){
            indiceUm = random.nextInt(9);
            indiceDois = random.nextInt(9);
            if(tabuleiroJogador[indiceUm][indiceDois] == "N"){
                i-= 1;
            }
            tabuleiroJogador[indiceUm][indiceDois] = "N";
        }

        for (int i = 0; i < 8; i++){
            indiceUm = random.nextInt(9);
            indiceDois = random.nextInt(9);
            if(tabuleiroMaquina[indiceUm][indiceDois] == "N"){
                i-= 1;
            }
            tabuleiroMaquina[indiceUm][indiceDois] = "N";
        }
    }

    public void printaTabuleiros(){
        System.out.println("Tabuleiro do Jogador: \n");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(tabuleiroJogador[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nTabuleiro da Máquina: \n");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(tabuleiroMaquina[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void jogadaAtual(int jogador){
        switch (jogador) {
            case 1:
                do {
                    do {
                        System.out.println("\nDigite a linha da jogada (0-9): ");
                        linhaJogo = scan.nextInt();
                    } while (linhaJogo < 0 || linhaJogo > 9);

                    do {
                        System.out.println("Digite a coluna da jogada (0-9): ");
                        colunaJogo = scan.nextInt();
                    } while (colunaJogo < 0 || colunaJogo > 9);

                    if (tabuleiroJogador[linhaJogo][colunaJogo] == "O"){
                        tabuleiroJogador[linhaJogo][colunaJogo] = "X";
                        jogadorAtual = 2;
                        break;
                    }
                    else if (tabuleiroJogador[linhaJogo][colunaJogo] == "N"){
                        tabuleiroJogador[linhaJogo][colunaJogo] = "*";
                        acertosJogador += 1;
                        jogadorAtual = 2;
                        break;
                    }
                    else {
                        System.out.println("Casa já jogada anteriormente, repita a jogada!");
                    }
                }
                while (jogador == 1);

            case 2:
                do {
                    indiceUm = random.nextInt(9);
                    indiceDois = random.nextInt(9);
                    if (tabuleiroMaquina[indiceUm][indiceDois] == "O"){
                        tabuleiroMaquina[indiceUm][indiceDois] = "X";
                        jogadorAtual = 1;
                        break;
                    }
                    else if (tabuleiroMaquina[indiceUm][indiceDois] == "N"){
                        tabuleiroMaquina[indiceUm][indiceDois] = "*";
                        acertosMaquina += 1;
                        jogadorAtual = 1;
                        break;
                    }
                } while (jogadorAtual == 2);

        }
    }

    public void verificaFimDeJogo(){
        if(acertosJogador == 8){
            System.out.println("O jogo terminou, o jogador venceu!");
            fimDeJogo = true;
        }
        if(acertosMaquina == 8){
            System.out.println("O jogo terminou, a máquina venceu :/");
            fimDeJogo = true;
        }
    }
}
