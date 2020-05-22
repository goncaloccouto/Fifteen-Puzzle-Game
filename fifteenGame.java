import java.util.*;
import java.lang.management.*;

public class fifteenGame{

static boolean hasSolution(int[] array){

    int parity = 0;
    int boardWidth = (int) Math.sqrt(array.length);
    int row = 0;
    int blankSpace = 0;

    for (int i = 0; i < array.length; i++){
        if (i % boardWidth == 0)
            row++;
        if (array[i] == 0)
            blankSpace = row;
        for (int j = i + 1; j < array.length; j++){
            if (array[i] > array[j] && array[j] != 0)
                parity++;
        }
    }
    if (boardWidth % 2 == 0){
        if (blankSpace % 2 == 0)
            return parity % 2 == 0;
        else
            return parity % 2 != 0;
    }
    else
        return parity % 2 == 0;
}

  boolean atingiuSolucao(Board tInicial, Board tFinal){
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(tInicial.matrix[i][j] != tFinal.matrix[i][j]){
          return false;
        }
      }
    }
    return true;
  }


  public static void main(String args[]){
    Scanner in = new Scanner(System.in);
    System.out.println("------------------------------------------------------------");
    System.out.println("|                                                          |");
    System.out.println("|                Bem Vindo ao jogo dos 15                  |");
    System.out.println("|                                                          |");
    System.out.println("------------------------------------------------------------");
    System.out.println();
    System.out.println("Insira o tabuleiro inicial");
    System.out.println();
    int array[] = new int[16];
    int arrayfinal[] = new int[16];
    double mb = 1024 * 1024;
    for(int i = 0; i < 16; i++){
      array[i] = in.nextInt();
    }
    System.out.println();
    System.out.println("Insira o tabuleiro final");
    System.out.println();
    for(int i = 0; i < 16; i++){
      arrayfinal[i] = in.nextInt();
    }
    System.out.println();
    Board tabuleiroInicial = new Board(array,null,null,0,"");
    Board tabuleiroFinal = new Board(arrayfinal,null,null,0,"");
    tabuleiroInicial.printBoard();
    System.out.println();
    if(hasSolution(array) == true){
      System.out.println("Escolha o algoritmo:  1-BFS  2-DFS  3-IDFS  4-Greedy  5-A*");
      int algoritmo = in.nextInt();
      if(algoritmo == 1){
        long startTime = System.nanoTime();
        Runtime memory = Runtime.getRuntime();
        Bfs b = new Bfs(tabuleiroInicial,tabuleiroFinal);
        long endTime = System.nanoTime();
        System.out.println("Tempo em nano segundos: " + (endTime - startTime));
        System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
      }
      else if(algoritmo == 2){
        long startTime = System.nanoTime();
        Runtime memory = Runtime.getRuntime();
        Dfs d = new Dfs(tabuleiroInicial,tabuleiroFinal,Integer.MAX_VALUE);
        long endTime = System.nanoTime();
        System.out.println("Tempo em nano segundos: " + (endTime - startTime));
        System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
      }
      else if(algoritmo == 3){
        int limite = 1;
        long startTime = System.nanoTime();
        Runtime memory = Runtime.getRuntime();
        Dfs d = new Dfs(tabuleiroInicial,tabuleiroFinal,limite);
        while(d.encontrouSolucao==false){
          limite++;
          d = new Dfs(tabuleiroInicial,tabuleiroFinal,limite);
        }
        long endTime = System.nanoTime();
        System.out.println("Tempo em nano segundos: " + (endTime - startTime));
        System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
      }
      else if(algoritmo == 4){
        System.out.println("Qual heuristica quer usar ?  1-Pecas fora do sitio  2-Distancia de Manhattan");
        int escolha = in.nextInt();
        long startTime = System.nanoTime();
        Runtime memory = Runtime.getRuntime();
        Greedy g = new Greedy(tabuleiroInicial,tabuleiroFinal, escolha);
        long endTime = System.nanoTime();
        System.out.println("Tempo em nano segundos: " + (endTime - startTime));
        System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
      }
      else{
        System.out.println("Qual heuristica quer usar ?  1-Pecas fora do sitio  2-Distancia de Manhattan");
        int escolha = in.nextInt();
        long startTime = System.nanoTime();
        Runtime memory = Runtime.getRuntime();
        Astar a = new Astar(tabuleiroInicial, tabuleiroFinal, escolha);
        long endTime = System.nanoTime();
        System.out.println("Tempo em nano segundos: " + (endTime - startTime));
        System.out.printf("Memoria usada: %.4f Mb\n", (memory.totalMemory() - memory.freeMemory()) / mb);
      }
    }
    else
      System.out.println("Sem solucao");
  }
}
