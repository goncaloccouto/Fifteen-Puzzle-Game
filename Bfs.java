import java.util.*;

public class Bfs{
    LinkedList<Board> queue = new LinkedList<>();
    LinkedList<Board> temp = new LinkedList<>();

    int nosgerados = 0;
    int nosutilizados = 0;
    int profundidadeSolucao = 0;


    Bfs(Board tabuleiro, Board tabuleiroFinal){
      Board newBoard = new Board(tabuleiro.convertBoardToArray(),null,null,0, "");
      queue.addLast(newBoard);
      Board solucao = Bfs_visit(tabuleiro,tabuleiroFinal);
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

    Board Bfs_visit(Board tabuleiro, Board tabuleiroFinal){
      while(queue.size()!=0){
        Board newTabuleiro = queue.pollFirst();
        nosutilizados++;
        if(atingiuSolucao(newTabuleiro,tabuleiroFinal) == true){
          return newTabuleiro;
        }
        else{
          geraFilhos(newTabuleiro);
          insertInQueue();
        }
      }
      return null;
    }

    void insertInQueue(){
      while(temp.size()!=0){
        Board tabuleiro = temp.pollFirst();
        queue.addLast(tabuleiro);
      }
    }

    void geraFilhos(Board tabuleiro){
      Board tabDown = tabuleiro.moveDown();
      Board tabUp = tabuleiro.moveUp();
      Board tabLeft = tabuleiro.moveLeft();
      Board tabRight = tabuleiro.moveRight();
      if(tabDown != null && checkRepeated(tabDown) == false){
        temp.addLast(tabDown);
        nosgerados++;
      }
      if(tabUp != null && checkRepeated(tabUp) == false){
        temp.addLast(tabUp);
        nosgerados++;
      }
      if(tabLeft != null && checkRepeated(tabLeft) == false){
        temp.addLast(tabLeft);
        nosgerados++;
      }
      if(tabRight != null && checkRepeated(tabRight) == false ){
        temp.addLast(tabRight);
        nosgerados++;
      }
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

    boolean checkRepeated(Board tabuleiro){
        Board tabuleiroAux = tabuleiro.pai;
        while(tabuleiroAux != null){
          if(compareMatrix(tabuleiro.getValue(),tabuleiroAux.getValue())==true)
            return true;
          tabuleiroAux = tabuleiroAux.pai;
        }
        return false;
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
