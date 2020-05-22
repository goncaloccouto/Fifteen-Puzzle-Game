import java.util.*;

class BoardComparator implements Comparator<Board>{
    public int compare(Board tabuleiro1, Board tabuleiro2){
        return tabuleiro1.heuristica - tabuleiro2.heuristica;
    }
}


class Greedy{

  Comparator<Board> comparator = new BoardComparator();
  PriorityQueue<Board> queue = new PriorityQueue<>(comparator);
  PriorityQueue<Board> temp = new PriorityQueue<>(comparator);

  Board tinicial;
  Board tabuleiroFinal;

  int opcao;
  int nosgerados = 0;
  int nosutilizados=0;
  int profundidadeSolucao = 0;

  Greedy(Board tabuleiro, Board tabuleiroFinal,int opcao){
      this.tabuleiroFinal = tabuleiroFinal;
      this.opcao = opcao;
      Board newBoard = new Board(tabuleiro.convertBoardToArray(),null,null,0, "", 0);
      queue.offer(newBoard);
      Board solucao = Greedy_visit(tabuleiro,tabuleiroFinal);
      profundidadeSolucao = solucao.depth;
      while(solucao != null){
        System.out.print(solucao.op);
        solucao = solucao.pai;
        if(solucao != null)
          System.out.print("  ");
      }
      System.out.println();
      System.out.println("Numero de nos gerados: " + nosgerados);
      System.out.println("Numero de nos utilizados: " + nosutilizados);
      System.out.println("Profundidade de solucao: " + profundidadeSolucao);
  }

  Board Greedy_visit(Board tabuleiro, Board tabuleiroFinal){
    while(queue.size()!=0){
      Board newTabuleiro = queue.poll();
      nosutilizados++;
      if(atingiuSolucao(newTabuleiro,tabuleiroFinal) == true){
        return newTabuleiro;
      }
      else{
        geraFilhos(newTabuleiro);
        insertInQueueGreedy();
      }
    }
    return null;
  }

  void insertInQueueGreedy(){
    queue.offer(temp.poll());
  }

  void pecasFora(Board tabuleiro){
    int soma = 0;
    int array[] = tabuleiro.convertBoardToArray();
    int arrayFinal[] = this.tabuleiroFinal.convertBoardToArray();
    for(int i = 0; i < 16 ; i++){
      if(array[i] != arrayFinal[i] && array[i] != 0 ){
        soma++;
      }
    }
    tabuleiro.heuristica = soma;
  }

  void distanciaManhattan(Board tabuleiro){
    int distanciaM = 0;
    int valor=0;
    int arrayPosIni[] = tabuleiro.convertBoardToArray();
    int arrayPosFin[] = tabuleiroFinal.convertBoardToArray();
    for(int i = 0; i < 16; i++){
      if(arrayPosIni[i] != arrayPosFin[i] && arrayPosIni[i] != 0){
        valor = arrayPosIni[i];
        for(int j=i;j<16;j++)
          if(arrayPosFin[j] == valor)
            break;
          distanciaM++;
      }
    }
    tabuleiro.heuristica = distanciaM;
  }

  void heuristicaGreedy(Board tabuleiro){
    if(opcao == 1)
      pecasFora(tabuleiro);
    else
      distanciaManhattan(tabuleiro);
  }

  void geraFilhos(Board tabuleiro){
    Board tabDown = tabuleiro.moveDown();
    Board tabUp = tabuleiro.moveUp();
    Board tabLeft = tabuleiro.moveLeft();
    Board tabRight = tabuleiro.moveRight();
    if(tabDown != null && checkRepeated(tabDown)==false){
      heuristicaGreedy(tabDown);
      temp.offer(tabDown);
      nosgerados++;
    }
    if(tabUp != null && checkRepeated(tabUp)==false){
      heuristicaGreedy(tabUp);
      temp.offer(tabUp);
      nosgerados++;
    }
    if(tabLeft != null && checkRepeated(tabLeft)==false){
      heuristicaGreedy(tabLeft);
      temp.offer(tabLeft);
      nosgerados++;
    }
    if(tabRight != null && checkRepeated(tabRight)==false){
      heuristicaGreedy(tabRight);
      temp.offer(tabRight);
      nosgerados++;
    }
  }

  boolean checkRepeated(Board tabuleiro){
      Board tabuleiroAux = tabuleiro.pai;
      while(tabuleiroAux != null){
        if(compareMatrix(tabuleiro.getValue(),tabuleiroAux.getValue())==true)
          return true;
        tabuleiroAux = tabuleiroAux.pai;
      }
      return false;
    }

  boolean compareMatrix(int[][] matrixA, int[][] matrixB){
      for(int i=0;i<4;i++){
        for(int j=0;j<4;j++){
          if(matrixA[i][j] != matrixB[i][j])
            return false;
        }
      }
      return true;
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
}
