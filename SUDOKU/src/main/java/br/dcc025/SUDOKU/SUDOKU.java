/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.dcc025.SUDOKU;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Victor
 */
import java.util.Scanner;
<<<<<<< HEAD
=======
import java.util.Random;
>>>>>>> 1808d21e84900b6c70db3666fa5dd76c0c57f7b8

public class SUDOKU {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Tabuleiro gabTab = new Tabuleiro();
        Tabuleiro tabuleiro = new Tabuleiro();

        int option = 0;
        int saida = 0;

        while (saida == 0) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerar novo jogo");
            System.out.println("2 - Jogar");
            System.out.println("3 - Ver o gabarito");
            System.out.println("4 - Sair");
            option = teclado.nextInt();

            switch (option) {
                case 1:
<<<<<<< HEAD
                    System.out.println("Bem vindo ao Sudoku!!");
=======
>>>>>>> 1808d21e84900b6c70db3666fa5dd76c0c57f7b8
                    System.out.println("Escolha uma opção:");
                    System.out.println("1 - Jogo Aletório");
                    System.out.println("2 - Jogo Pesonalizado");
                    System.out.println("3 - Sair");
                    String escolha = teclado.next();
                    int choice = Integer.parseInt(escolha);
                    if(choice == 1 ){
                        System.out.println("Informe a quantidade de casas aleatórias: ");
                        int casas = teclado.nextInt();
                        tabuleiro.geradorTabuleiro(casas, gabTab);
                        System.out.println();                        
                        gabTab.gabarito();
                        tabuleiro.imprimeTabuleiro();
                        System.out.println("Jogo pronto para ser jogado!!");
                        break;
                    }
                    else if(choice == 2){
                        String escape = "";
                        while (!escape.equals("saida")) {
                            System.out.println("Digite a(s) jogada(s) no formato (linha,coluna,valor) \n"+
                                               "Digite remover para refazer uma jogada\n"+
                                               "Digite saida para voltar ao menu");
                            String insercao = teclado.next();
                            if (!insercao.equals("saida" ) && !insercao.equals("remover" )){
                                tabuleiro.setValor(insercao);
                                tabuleiro.imprimeTabuleiro();
                            }
                            else if(insercao.equals("remover")){
                                System.out.println("Informe a linha a ser removida: ");
                                int linha = teclado.nextInt();
                                System.out.println("Informe a coluna a ser removida");
                                int coluna = teclado.nextInt();
                                tabuleiro.removeValor(linha,coluna);
                                tabuleiro.imprimeTabuleiro();
                            }
                            else if(insercao.equals("saida")){
                                escape = "saida";
                                break;
                            }
                        };
                            break;
                    } 
                    case 2:
                    while (tabuleiro.contaVazias() > 0) {
                            System.out.println("Digite a(s) jogada(s) no formato (linha,coluna,valor) \n"+
                                               "Digite remover para refazer uma jogada\n"+
                                               "Digite saida para voltar ao menu");
                            tabuleiro.imprimeTabuleiro(); 
                            String jogadas = teclado.next();
                            if (!jogadas.equals("saida" ) && !jogadas.equals("remover" )){
                                tabuleiro.setValor(jogadas);
                                tabuleiro.imprimeTabuleiro();                            
                                if (tabuleiro.contaVazias() == 0) {
                                    System.out.println("Parabéns, você ganhou!");
<<<<<<< HEAD
                                    System.out.println("1 - Jogar novamente");
                                    System.out.println("2 - Sair do jogo");
                                    int denovo=teclado.nextInt();
                                    if( denovo == 1){
                                        break;
                                    }
                                    else{                                     
                                        saida = 1;
                                        break;
                                    }
=======
                                    saida = 1;
                                    break;
>>>>>>> 1808d21e84900b6c70db3666fa5dd76c0c57f7b8
                                }
                            }
                            else if(jogadas.equals("remover")){
                                System.out.println("Informe a linha a ser removida: ");
                                int linha = teclado.nextInt();
                                System.out.println("Informe a coluna a ser removida");
                                int coluna = teclado.nextInt();
                                tabuleiro.removeValor(linha,coluna);
                                tabuleiro.imprimeTabuleiro();
                            }
                                 
                            else if(jogadas.equals("saida")){
                                break;
                            }
                        };
                        break;
                case 3:
                    gabTab.gabarito();
                    gabTab.imprimeTabuleiro();
                    break;
                case 4:
                    saida = 1;
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
        teclado.close();
        
    }
}
