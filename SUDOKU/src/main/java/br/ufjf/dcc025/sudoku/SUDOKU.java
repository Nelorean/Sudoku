/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.ufjf.dcc025.sudoku;
/**
 *
 * @author Victor
 */
public class SUDOKU {
  private static final int DIMENSAO = 9;
  
  public static void main(String[] args) {
    
    int[][] tabuleiro = {
        {7, 0, 2, 0, 5, 0, 6, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 0, 0},
        {1, 0, 0, 0, 0, 9, 5, 0, 0},
        {8, 0, 0, 0, 0, 0, 0, 9, 0},
        {0, 4, 3, 0, 0, 0, 7, 5, 0},
        {0, 9, 0, 0, 0, 0, 0, 0, 8},
        {0, 0, 9, 7, 0, 0, 0, 0, 5},
        {0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
      };
 
    if (gabarito(tabuleiro)) {
      System.out.println("Jogo valido e pronto para ser jogado!");
    }
    else {
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
}

