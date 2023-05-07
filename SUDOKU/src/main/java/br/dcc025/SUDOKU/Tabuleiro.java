package br.dcc025.SUDOKU;
import java.util.Random;

public class Tabuleiro {
    private static final int DIMENSAO = 9;
    private int[][] tabuleiro;
    private int casasVazias;

    public Tabuleiro() {
        this.tabuleiro = new int[DIMENSAO][DIMENSAO];
        this.casasVazias = DIMENSAO * DIMENSAO;
        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                this.tabuleiro[i][j] = 0;
            }
        }

    }

    public void imprimeTabuleiro() {
        for (int linha = 0; linha < DIMENSAO; linha++) {
            for (int coluna = 0; coluna < DIMENSAO; coluna++) {
                System.out.print(this.tabuleiro[linha][coluna]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public boolean validaLinha(int n, int linha) {
        for (int i = 0; i < DIMENSAO; i++) {
            if (this.tabuleiro[linha][i] == n) {
                return true;
            }
        }
        return false;
    }

    public boolean validaColuna(int n, int coluna) {
        for (int i = 0; i < DIMENSAO; i++) {
            if (this.tabuleiro[i][coluna] == n) {
                return true;
            }
        }
        return false;
    }

    public boolean validaQuadrado(int n, int linha, int coluna) {
        int linhaQuadrado = linha - linha % 3;
        int colunaQuadrado = coluna - coluna % 3;
        for (int i = linhaQuadrado; i < linhaQuadrado + 3; i++) {
            for (int j = colunaQuadrado; j < colunaQuadrado + 3; j++) {
                if (this.tabuleiro[i][j] == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validaPosicao(int linha, int coluna) {
        if (this.tabuleiro[linha][coluna] != 0) {
            return true;
        }
        return false;
    }

    private  boolean insercaoValida(int n, int linha, int coluna) {
        return !validaLinha( n, linha) &&
               !validaColuna( n, coluna) &&
               !validaQuadrado( n, linha, coluna) &&
               !validaPosicao(linha, coluna);

    }

    public void setValor(String jogo) {
        
            int linha = 0;
            int coluna = 0;
            int valor = 0;
            for (int i = 1; i < jogo.length(); i += 7) {
                linha = Character.getNumericValue(jogo.charAt(i) - 1);
                coluna = Character.getNumericValue(jogo.charAt(i + 2) - 1);
                valor = Character.getNumericValue(jogo.charAt(i + 4));
                if (linha < 0 || linha > 9 || coluna < 0 || coluna > 9 || valor < 1 || valor > 9) {
                    System.out.println("Valores inválidos.\n");
                } 
                else {
                    if (insercaoValida(valor, linha, coluna)) {
                        this.tabuleiro[linha][coluna] = valor;
                        if (this.tabuleiro[linha][coluna] == 0) {
                            this.casasVazias--;
                        }
                    } 
                    else {
                        System.out.println("Jogada inválida.\n"+
                                           "Valor já está presente na linha,coluna,quadrado ou na posição informada");
                    }
                }
        }           

    }
    public void removeValor(int linha, int coluna) {
        if (this.tabuleiro[linha-1][coluna-1] != 0) {
            this.tabuleiro[linha-1][coluna-1] = 0;
            this.casasVazias++;
        }
    }
    public void criaTabuleiro() {
        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                this.tabuleiro[i][j] = 0;
            }
        }
    }

    public boolean gabarito() {
        for (int linha = 0; linha < DIMENSAO; linha++) {
            for (int coluna = 0; coluna < DIMENSAO; coluna++) {
                if (this.tabuleiro[linha][coluna] == 0) {
                    for (int n = 1; n <= DIMENSAO; n++) {
                        if (insercaoValida(n, linha, coluna)) {
                            this.tabuleiro[linha][coluna] = n;
                            if (gabarito()) {
                                return true;
                            } else {
                                this.tabuleiro[linha][coluna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public void setValorCopia(int linha, int coluna, int valor) {
        this.tabuleiro[linha][coluna] = valor;
    }

    public void copiaTab(Tabuleiro gabTab) {
        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                gabTab.setValorCopia(i, j, this.tabuleiro[i][j]);
            }
        }
    }
    public void geradorTabuleiro(int casas, Tabuleiro gabTab) {
        Random gerador = new Random();
        int linha, coluna, valor;
        int k = 0;
        for (int i = 0; i < casas; i++) {
            do {
                linha = gerador.nextInt(8);
                coluna = gerador.nextInt(8);
                valor = (gerador.nextInt(8) + 1);
            } while (!insercaoValida(valor, linha, coluna));
            this.tabuleiro[linha][coluna] = valor;
            k++;
        }
        copiaTab(gabTab);
    }
    public int contaVazias() {
        return this.casasVazias;
    }
}

