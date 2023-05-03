/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.ufjf.dcc025.sudoku;
/**
 *
 * @author Victor
 */
import java.util.Scanner;
public class SUDOKU {
    private static final int DIMENSAO = 9;

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
      Scanner teclado = new Scanner(System.in);
      int saida = 0;
      int option = 0;
      int again = 0;
      int[][] tabuleiro = new int [DIMENSAO][DIMENSAO];
      criaTabuleiro(tabuleiro);
            while(saida == 0){
            System.out.println("""
                               Bem vindo ao Sudoku! 
                               Qual op\u00e7\u00e3o voc\u00ea deseja?
                               (1) Jogo Aleat\u00f3rio
                               (2) Montar seu pr\u00f3prio jogo
                               (3) Sair do jogo""");
                               option=teclado.nextInt();           

            switch(option){                
                case 1 -> {
                    System.out.println("""
                                       Informe a linha, a coluna e o valor desejado separados por virgula.
                                       Caso haja mais de uma jogada, separa-las por parenteses e sem espa\u00e7o.""");                    
                    String jogo = teclado.next();
                    setValor(tabuleiro, jogo);
                    jogo = "";                 
                    System.out.println("""
                                       Deseja parar de jogar?
                                       1 para Sim e 0 para n\u00e3o: """);
                    again = teclado.nextInt();
                  if (again == 1) {
                  } else {
                      saida = 1;
                  }
              }
                case 2 -> {
                    System.out.println("""
                                       Informe a linha, a coluna e o valor desejado separados por virgula.
                                       Caso haja mais de uma casa para inserir, separa-las por parenteses e sem espa\u00e7o.""");
                    String adicao = teclado.next();
                    int[][] customTabuleiro = new int [DIMENSAO][DIMENSAO];
                    int[][] gabTab = new int [DIMENSAO][DIMENSAO];
                    criaTabuleiro(customTabuleiro);
                    criaTabuleiro(gabTab);                    
                    setValor(customTabuleiro, adicao);
                    copiaTab(customTabuleiro, gabTab);
                    imprime(customTabuleiro);
                    if(gabarito(gabTab)){
                        System.out.println("Jogo pronto para jogar");
                        System.out.println("""
                                           Informe a linha, a coluna e o valor desejado separados por virgula.
                                           Caso haja mais de uma jogada, separa-las por parenteses e sem espa\u00e7o.""");
                        imprime(gabTab);                   
                        String jogoCustom = teclado.next();
                        setValor(tabuleiro, jogoCustom);
                        jogoCustom = "";
                        imprime(customTabuleiro);
                    }
                    else{
                        System.out.println("Jogo sem solução ");
                        saida =1;
                        imprime(customTabuleiro);
                    }
                    again = teclado.nextInt();
                  if (again == 1) {
                  } else {
                      saida = 1;
                  }
              }           

                case 3 -> saida = 1;
                default -> System.out.println("Opção Inválida!");
            }
        };

      if(gabarito(tabuleiro)){
        System.out.println("Jogo valido e pronto para ser jogado!");
        imprime(tabuleiro);
      }
      else{
        System.out.println("Jogo não valido!!");
      }    
      imprime(tabuleiro);
    }


    private static void imprime(int[][] tabuleiro) {
      for (int linha = 0; linha < DIMENSAO; linha++){
          for(int coluna = 0;coluna < DIMENSAO; coluna++){
              System.out.print(tabuleiro[linha][coluna]);
              System.out.print(" ");
          }
          System.out.print("\n");
      }
    }
    
    private static boolean validaLinha(int[][] tabuleiro, int n, int linha) {
      for (int i = 0; i < DIMENSAO; i++) {
        if (tabuleiro[linha][i] == n) {
          return true;
        }
      }
      return false;
    }

    private static boolean validaColuna(int[][] tabuleiro, int n, int coluna) {
      for (int i = 0; i < DIMENSAO; i++) {
        if (tabuleiro[i][coluna] == n) {
          return true;
        }
      }
      return false;
    }

    private static boolean validaQuadrado(int[][] tabuleiro, int n, int linha, int coluna) {
      int linhaQuadrado = linha - linha % 3;
      int colunaQuadrado = coluna - coluna % 3;

      for (int i = linhaQuadrado; i < linhaQuadrado + 3; i++) {
        for (int j = colunaQuadrado; j < colunaQuadrado + 3; j++) {
          if (tabuleiro[i][j] == n) {
            return true;
          }
        }
      }
      return false;
    }

    private static boolean insercaoValida(int[][] tabuleiro, int n, int linha, int coluna) {
      return !validaLinha(tabuleiro, n, linha) &&
          !validaColuna(tabuleiro, n, coluna) &&
          !validaQuadrado(tabuleiro, n, linha, coluna);
    }

    private static boolean gabarito(int[][] tabuleiro) {
    for (int linha = 0; linha < DIMENSAO; linha++) {
      for (int coluna = 0; coluna < DIMENSAO; coluna++) {
        if (tabuleiro[linha][coluna] == 0) {
          for (int n = 1; n <= DIMENSAO; n++) {
            if (insercaoValida(tabuleiro, n, linha, coluna)) {
              tabuleiro[linha][coluna] = n;

              if (gabarito(tabuleiro)) {
                return true;
              }
              else {
                tabuleiro[linha][coluna] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
    }
    
    private static void setValor(int [][]tabuleiro, String jogo){
        int linha = 0; int coluna = 0; int valor = 0;
        for(int i = 1; i < jogo.length(); i+=7){
            linha = Character.getNumericValue(jogo.charAt(i)-1);
            System.out.println(linha);
            coluna = Character.getNumericValue(jogo.charAt(i+2)-1);
            System.out.println(coluna);
            valor = Character.getNumericValue(jogo.charAt(i+4));
            System.out.println(valor);
            if(insercaoValida(tabuleiro, valor, linha, coluna)){
                tabuleiro[linha][coluna]=valor;
            }
            else{
                System.out.println("Jogada Inválida: \n");
            }
        }
    }
    
    private static void criaTabuleiro(int [][] tabuleiro){
        for(int i = 0; i < DIMENSAO;i++){
            for (int j = 0; j< DIMENSAO; j ++){
                tabuleiro[i][j] = 0;
            }
        }
    }
    
    private static void copiaTab(int [][] tabuleiro, int [][] gabTab){
        for (int i = 0; i < DIMENSAO; i ++){
            for (int j = 0; j < DIMENSAO; j ++){
                gabTab[i][j] = tabuleiro[i][j];
            }
        }
    }
    
}

