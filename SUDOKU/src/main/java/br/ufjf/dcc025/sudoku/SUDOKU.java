/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.ufjf.dcc025.sudoku;


/**
 *
 * @author Victor
 */
import java.util.Scanner;
import java.util.Random;

public class SUDOKU {
    private static final int DIMENSAO = 9;
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        int saida = 0;
        int option = 0;
        int again = 0;
        int casasVazias = 0;
        int[][] gabTab = new int [DIMENSAO][DIMENSAO];
    
        while(saida == 0){
            System.out.println("""
                               Bem vindo ao Sudoku! 
                               Qual op\u00e7\u00e3o voc\u00ea deseja?
                               (1) Jogo Aleat\u00f3rio
                               (2) Montar seu pr\u00f3prio jog
                               (3) Sair do jogo""");
                               option=teclado.nextInt();           

            switch(option){                
                case 1:
                    int casas = 0;
                    int[][] randomTabuleiro = new int [DIMENSAO][DIMENSAO];
                    criaTabuleiro(randomTabuleiro);
                    System.out.println("Informe o numero de casas ja existentes: ");                    
                    casas = teclado.nextInt();
                    geradorTabuleiro(randomTabuleiro, casas, gabTab);
                    contaVazias(randomTabuleiro, casasVazias);
                    System.out.println(contaVazias(randomTabuleiro, casasVazias));
                    do{
                        if (gabarito(gabTab)) {
                            System.out.println("Jogo criado com sucesso! e pronto para jogar!!!");
                        }
                    } while(!gabarito(gabTab));
                    while(contaVazias(randomTabuleiro, casasVazias) > 0){
                        System.out.println("Informe a linha, a coluna e o valor desejado separados por virgula.\n" + 
                        "Caso haja mais de uma jogada, separa-las por parenteses e sem espaço."); 
                        imprime(randomTabuleiro);                 
                        String jogoRandom = teclado.next();
                        setValor(randomTabuleiro, jogoRandom);
                        jogoRandom = "";
                        contaVazias(randomTabuleiro, casasVazias);
                        imprime(randomTabuleiro);
                        System.out.println("Se deseja remover alguma casa, digite remover, caso contrario digite adicionar");
                        String escolha = teclado.next();
                        if(escolha.equals("remover") || escolha.equals("REMOVER")){
                            System.out.println("Informe a linha que deseja realizar a remoção ");
                            int linha = teclado.nextInt() -1;
                            System.out.println("Informe a linha que deseja realizar a remoção ");
                            int coluna = teclado.nextInt() -1;
                            removeValor(randomTabuleiro, linha, coluna);
                            imprime(randomTabuleiro); 
                        }
                        else{     
                            System.out.println(); 
                        }                        
                    };
                    System.out.println("Deseja jogar novamente? \n ");
                    System.out.println("Digite 1 para Sim e 0 para não");
                    again = teclado.nextInt();
                        if(again == 1){
                            break;
                        }
                        else{
                            saida = 1;
                            break;
                        }           
                case 2:
                    casasVazias = 0;
                    String adicao="";
                    String stop = "saida";
                    String parada = "";
                    int[][] customTabuleiro = new int [DIMENSAO][DIMENSAO];
                    criaTabuleiro(customTabuleiro);                                   
                    while(!stop.equals(parada)){
                        System.out.println("""
                                           Informe a linha, a coluna e o valor desejado separados por virgula.
                                           Caso haja mais de uma casa para inserir, separa-las por parenteses e sem espa\u00e7o.""");
                        adicao = teclado.next();
                        setValor(customTabuleiro, adicao);
                        imprime(customTabuleiro);
                        System.out.println("""
                                           Caso n\u00e3o haja mais inser\u00e7\u00f5es, digite saida para sair do modo cria\u00e7\u00e3o.
                                           Caso contr\u00e1rio, digite continuar""");
                        parada = teclado.next(); 
                        if(parada.equals("saida")){
                            if (gabarito(gabTab)) {
                                System.out.println("""
                                                   Jogo criado com sucesso! e pronto para jogar!!!
                                                   Deseja editar o tabuleiro?
                                                   Digite 1 para editar e 0 para jogar: """);
                                int resposta = teclado.nextInt();
                                if(resposta == 1){
                                    parada = "";
                                }
                            }
                            else{
                                System.out.println("""
                                                   Jogo sem solu\u00e7\u00e3o!!!
                                                   Deseja editar o tabuleiro?
                                                   Digite 1 para editar e 0 para voltar ao menu:  """);
                                int resposta = teclado.nextInt();
                                    if(resposta == 1 ){
                                        parada = "";
                                    }
                                    else{
                                        saida =1;
                                        break;
                                    }               
                            }
                        }                
                    };    
                    /// game start
                    copiaTab(customTabuleiro, gabTab);
                    while(contaVazias(customTabuleiro, casasVazias) > 0){
                        System.out.println("""
                                           Informe a linha, a coluna e o valor desejado separados por virgula.
                                           Caso haja mais de uma jogada, separa-las por parenteses e sem espa\u00e7o."""); 
                        imprime(customTabuleiro);                 
                        String jogoRandom = teclado.next();
                        setValor(customTabuleiro, jogoRandom);
                        jogoRandom = "";
                        contaVazias(customTabuleiro, casasVazias);
                        imprime(customTabuleiro);
                        System.out.println("Se deseja remover alguma casa, digite remover, caso contrario digite adicionar");
                        String escolha = teclado.next();
                        if(escolha.equals("remover") || escolha.equals("REMOVER")){
                            System.out.println("Informe a linha que deseja realizar a remoção ");
                            int linha = teclado.nextInt() -1;
                            System.out.println("Informe a linha que deseja realizar a remoção ");
                            int coluna = teclado.nextInt() -1;
                            removeValor(customTabuleiro, linha, coluna);
                            imprime(customTabuleiro); 
                        }
                        else{     
                            System.out.println(); 
                        }                        
                    };     
                case 3:
                    saida = 1;
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
        teclado.close();
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

    private static boolean validaPosicao(int [][]tabuleiro,int linha,int coluna){
        if(tabuleiro[linha][coluna] != 0){
            return true;
        }
        return false;
    }
         
    private static boolean insercaoValida(int[][] tabuleiro, int n, int linha, int coluna) {
        return !validaLinha(tabuleiro, n, linha) &&
               !validaColuna(tabuleiro, n, coluna) &&
               !validaQuadrado(tabuleiro, n, linha, coluna) &&
               !validaPosicao(tabuleiro, linha, coluna);

    }

    private static void setValor(int [][]tabuleiro, String jogo){
        int linha = 0; int coluna = 0; int valor = 0;
        for(int i = 1; i < jogo.length(); i+=7){
            linha = Character.getNumericValue(jogo.charAt(i)-1);
            coluna = Character.getNumericValue(jogo.charAt(i+2)-1);
            valor = Character.getNumericValue(jogo.charAt(i+4));

            if(insercaoValida(tabuleiro, valor, linha, coluna)){
                tabuleiro[linha][coluna]=valor;
            }
            else{
                System.out.println("Jogada Inválida: \n");
            }
        }
    }
    
    private static void removeValor(int [][]tabuleiro, int linha, int coluna){
        tabuleiro[linha][coluna]=0;
    }

    private static void criaTabuleiro(int [][] tabuleiro){
        for(int i = 0; i < DIMENSAO;i++){
            for (int j = 0; j< DIMENSAO; j ++){
                tabuleiro[i][j] = 0;
            }
        }
    }
    
    private static boolean gabarito(int[][] gabTab) {
        for (int linha = 0; linha < DIMENSAO; linha++) {
          for (int coluna = 0; coluna < DIMENSAO; coluna++) {
            if (gabTab[linha][coluna] == 0) {
              for (int n = 1; n <= DIMENSAO; n++) {
                if (insercaoValida(gabTab, n, linha, coluna)) {
                    gabTab[linha][coluna] = n;
                  if (gabarito(gabTab)) {
                    return true;
                  }
                  else {
                    gabTab[linha][coluna] = 0;
                  }
                }
              }
              return false;
            }
          }
        }
        return true;
        }

    private static void copiaTab(int [][] tabuleiro, int [][] gabTab){
        for (int i = 0; i < DIMENSAO; i ++){
            for (int j = 0; j < DIMENSAO; j ++){
                gabTab[i][j] = tabuleiro[i][j];
            }
        }
    }

    private static void geradorTabuleiro(int [][] randomTabuleiro, int casas, int [][] gabTab){
        Random gerador = new Random();
        int linha, coluna, valor;
        int k = 0;
        int cont = 0;
            while( k < casas && cont < 10){
                for(int i = 0 ; i < casas; i ++){
                    linha =  gerador.nextInt(8);
                    coluna = gerador.nextInt(8);
                    valor = (gerador.nextInt(8) + 1);
                    if(insercaoValida(randomTabuleiro, valor, linha, coluna)){
                        randomTabuleiro[linha][coluna] = valor ;
                        k++;
                    }  
                }

            };
        copiaTab(randomTabuleiro, gabTab);  
    }

    private static int contaVazias(int [][]tabuleiro, int casasVazias){        
        for(int i = 0; i < DIMENSAO;i ++){
            for(int j = 0; j < DIMENSAO;j ++){
                if(tabuleiro[i][j]== 0){
                    casasVazias ++;
                }
            }
        }
        return casasVazias;
    }
}
