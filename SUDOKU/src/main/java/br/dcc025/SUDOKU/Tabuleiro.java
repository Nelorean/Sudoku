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
    public void removeValor(String remover) {
        int linha = 0;
        int coluna = 0;
        for (int i = 1; i < remover.length(); i += 7) {
            linha = Character.getNumericValue(remover.charAt(i) - 1);
            coluna = Character.getNumericValue(remover.charAt(i + 2) - 1);
            this.tabuleiro[linha][coluna] = 0;
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
        resolver();
        int casasRemovidas = DIMENSAO * DIMENSAO - casas;
        removerCasas(casasRemovidas, gabTab);
        copiaTab(gabTab);
    }
    public boolean resolver() {
        int linha = -1;
        int coluna = -1;
        boolean vazio = true;
        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                if (this.tabuleiro[i][j] == 0) {
                    linha = i;
                    coluna = j;
                    vazio = false;
                    break;
                }
            }
            if (!vazio) {
                break;
            }
        }
        if (vazio) {
            return true;
        }
        for (int num = 1; num <= DIMENSAO; num++) {
            if (insercaoValida(num, linha, coluna)) {
                this.tabuleiro[linha][coluna] = num;
                if (resolver()) {
                    return true;
                }
                this.tabuleiro[linha][coluna] = 0;
            }
        }
        return false;
    }

    public void removerCasas(int casas, Tabuleiro gabTab) {
        Random gerador = new Random();
        for (int i = 0; i < casas; i++) {
            int x = gerador.nextInt(DIMENSAO);
            int y = gerador.nextInt(DIMENSAO);
            while (this.tabuleiro[y][x] == 0) {
                x = gerador.nextInt(DIMENSAO);
                y = gerador.nextInt(DIMENSAO);
            }
            gabTab.tabuleiro[y][x] = this.tabuleiro[y][x];
            this.tabuleiro[y][x] = 0;
            this.casasVazias--;
        }
    }

    public int contaVazias() {
        int vazias = 0;
        for (int linha = 0; linha < DIMENSAO; linha++) {
            for (int coluna = 0; coluna < DIMENSAO; coluna++) {
                if (this.tabuleiro[linha][coluna] == 0) {
                    vazias++;
                }
            }
        }
        return vazias;
    }
}

