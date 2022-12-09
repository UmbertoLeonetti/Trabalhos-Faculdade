import java.util.Scanner;

public class CacaPalavras {

    public CacaPalavras() {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        char mapa[][] = new char[10][5];
        String palavras[][] = new String[5][2];
        mapa(mapa);
        palavras(palavras);
        mapaPesquisa(palavras, mapa);
        do {
            System.out.println("opção 1: chamar o método palavrasImprimir");
            System.out.println("opção 2: chamar o método mapaImprimir");
            System.out.println("opção 3: chamar o método palavrasRepostas;");
            System.out.println("opção 4: sair;");
            System.out.print("digite uma das opções:");

            opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    palavrasImprimir(palavras);
                    break;
                case 2:
                    mapaImprimir(mapa);
                    break;

                case 3:
                    respostasImprimir(palavras);
                    break;

                case 4:
                    System.exit(0);

                    break;

                default:
                    System.out.println("opcao invalida!");
                    break;
            }

        } while (opcao != 4);

        scan.close();
    }

    public char[][] mapa(char mapa[][]) {

        mapa[ 0][ 0]='D';  mapa[ 0][ 1]='C';  mapa[ 0][ 2]='Q';  mapa[ 0][ 3]='W';  mapa[ 0][ 4]='E';
        mapa[ 1][ 0]='I';  mapa[ 1][ 1]='X';  mapa[ 1][ 2]='F';  mapa[ 1][ 3]='O';  mapa[ 1][ 4]='R';
        mapa[ 2][ 0]='F';  mapa[ 2][ 1]='F';  mapa[ 2][ 2]='R';  mapa[ 2][ 3]='G';  mapa[ 2][ 4]='F';
        mapa[ 3][ 0]='E';  mapa[ 3][ 1]='L';  mapa[ 3][ 2]='I';  mapa[ 3][ 3]='H';  mapa[ 3][ 4]='W';
        mapa[ 4][ 0]='L';  mapa[ 4][ 1]='S';  mapa[ 4][ 2]='F';  mapa[ 4][ 3]='O';  mapa[ 4][ 4]='U';
        mapa[ 5][ 0]='S';  mapa[ 5][ 1]='D';  mapa[ 5][ 2]='G';  mapa[ 5][ 3]='T';  mapa[ 5][ 4]='S';
        mapa[ 6][ 0]='E';  mapa[ 6][ 1]='J';  mapa[ 6][ 2]='H';  mapa[ 6][ 3]='E';  mapa[ 6][ 4]='T';
        mapa[ 7][ 0]='I';  mapa[ 7][ 1]='I';  mapa[ 7][ 2]='I';  mapa[ 7][ 3]='J';  mapa[ 7][ 4]='M';
        mapa[ 8][ 0]='X';  mapa[ 8][ 1]='C';  mapa[ 8][ 2]='K';  mapa[ 8][ 3]='B';  mapa[ 8][ 4]='G';
        mapa[ 9][ 0]='U';  mapa[ 9][ 1]='E';  mapa[ 9][ 2]='T';  mapa[ 9][ 3]='O';  mapa[ 9][ 4]='R';
        return mapa;
    }

    public void mapaImprimir(char mapa[][]) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mapa[i][j] + " ");

            }
            System.out.println(" ");
        }

    }

    public String[][] palavras(String palavras[][]) {

        palavras[0][0] = "IFELSES";
        palavras[1][0] = "FORA";
        palavras[2][0] = "WHILE";
        palavras[3][0] = "OBJ";
        palavras[4][0] = "VETOR";

        return palavras;

    }

    public void palavrasImprimir(String palavras[][]) {

        for (int i = 0; i < 5; i++) {

            System.out.println(palavras[i][0]);
        }

    }

    public String [][] mapaPesquisa(String palavras[][], char mapa[][]) {

        int cont = 0;
        for (int i = 0; i < 5; i++) {
            for (int l = 0; l < 10; l++) {
                for (int c = 0; c < 5; c++) {
                    char palavraVetor = palavras[i][0].charAt(0);
                    if (palavraVetor == mapa[l][c]) {
                        int linha = l;
                        int coluna = c;
                        for (int t = 1; t < (palavras[i][0].length()); t++) {
                            palavraVetor = palavras[i][0].charAt(t);
                            if (linha < 9) {
                                if (palavraVetor == mapa[linha + 1][c]) {
                                    linha++;
                                    cont++;
                                } else {
                                    t = 20;
                                }
                            }
                        }
                        linha = l;
                        for (int t = 1; t < (palavras[i][0].length()); t++) {
                            palavraVetor = palavras[i][0].charAt(t);
                            if (linha > 0) {
                                if (palavraVetor == mapa[linha - 1][c]) {
                                    linha--;
                                    cont++;
                                } else {
                                    t = 20;
                                }
                            }
                        }
                        for (int t = 1; t < (palavras[i][0].length()); t++) {
                            palavraVetor = palavras[i][0].charAt(t);
                            if (coluna < 4) {
                                if (palavraVetor == mapa[l][coluna + 1]) {
                                    coluna++;
                                    cont++;
                                } else {
                                    t = 20;
                                }
                            }
                        }
                        coluna = c;
                        for (int t = 1; t < (palavras[i][0].length()); t++) {
                            palavraVetor = palavras[i][0].charAt(t);
                            if (coluna > 0) {
                                if (palavraVetor == mapa[l][coluna - 1]) {
                                    coluna--;
                                    cont++;
                                } else {
                                    t = 20;
                                }
                            }
                        }
                        
                    }
                        if (cont == (palavras[i][0].length() - 1)) {
                            palavras[i][1] = "[" + l + "]"+ "[" + c + "] - " + palavras[i][0];
                        } 
                        if (cont != (palavras[i][0].length() - 1) &&  palavras[i][1] == null) {
                            palavras[i][1] = "Palavra NÃO encontrada";
                        }
                        cont = 0;
                }
            }

    }
    return palavras;
    }

    public void respostasImprimir(String palavras[][]) {

        for (int i = 0; i < 5; i++) {
            System.out.println(palavras[i][1]);
        }

    }

    public static void main(String[] args) {
        new CacaPalavras();
    }

} 