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

public class SUDOKU {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Tabuleiro gabTab = new Tabuleiro();
        Tabuleiro tabuleiro = new Tabuleiro();

        int option = 0;
        int saida = 0;

        while (saida == 0) {
            System.out.println("Bem vindo ao Sudoku!!");
            System.out.println(); 
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerar novo jogo");
            System.out.println("2 - Jogar");
            System.out.println("3 - Ver o gabarito");
            System.out.println("4 - Sair");
            option = teclado.nextInt();

            switch (option) {
                case 1:
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
                        while(!gabTab.gabarito()){
                            tabuleiro.geradorTabuleiro(casas, gabTab);
                            gabTab.gabarito();
                        };                        
                                          
                        tabuleiro.imprimeTabuleiro();
                        System.out.println(); 
                        System.out.println("Jogo pronto para ser jogado!!");   
                        break; 
                    }
                    else if(choice == 2){
                        String escape = "";
                        while (!escape.equals("saida")) {
                            System.out.println("Digite a(s) inserção(s) no formato (linha,coluna,valor) \n"+
                                               "Digite remover para refazer uma jogada\n"+
                                               "Digite saida para voltar ao menu");
                            String insercao = teclado.next();
                            if (!insercao.equals("saida" ) && !insercao.equals("remover" )){
                                tabuleiro.setValor(insercao);
                                System.out.println(); 
                                tabuleiro.imprimeTabuleiro();
                                System.out.println(); 
                                System.out.println();
                                if(!gabTab.gabarito()){
                                    System.out.println("Jogo tornou-se sem solução!");
                                }

                            }
                            else if(insercao.equals("remover")){
                                System.out.println("Digite a(s) inserção(s) no formato (linha,coluna)");
                                String remover = teclado.next();
                                tabuleiro.removeValor(remover);
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
                                    }
                                }
                                else if(jogadas.equals("remover")){
                                    System.out.println("Digite a(s) inserção(s) no formato (linha,coluna)");
                                    String remover = teclado.next();
                                    tabuleiro.removeValor(remover);
                                    tabuleiro.imprimeTabuleiro();
                                }
                                    
                                else if(jogadas.equals("saida")){
                                    break;
                                }
                            };
                            break;
                case 3:
                    if(gabTab.gabarito()){
                        System.out.println();
                        gabTab.imprimeTabuleiro();
                    }
                    else{
                        System.out.println("Soução não encontrada");
                    }
                    
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
