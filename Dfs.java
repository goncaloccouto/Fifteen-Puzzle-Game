import java.util.*;

class Dfs{

  LinkedList<Board> stack = new LinkedList<>();
  LinkedList<Board> temp = new LinkedList<>();
  boolean encontrouSolucao = false;

  int nosgerados = 0;
  int nosutilizados = 0;
  int profundidadeSolucao = 0;

  Dfs(Board tabuleiro, Board tabuleiroFinal,int limite){
    Board newBoard = new Board(tabuleiro.convertBoardToArray(),null,null,0, "");
    stack.addLast(newBoard);
    Board solucao = Dfs_visit(tabuleiro,tabuleiroFinal,limite);
    if(solucao!=null){
      profundidadeSolucao = solucao.depth;
      encontrouSolucao = true;
    }
    while(solucao != null){
      System.out.print(solucao.op);
      solucao = solucao.pai;
      if(solucao != null)
        System.out.print("  ");
    }
    if(encontrouSolucao==true){
      System.out.println();
      System.out.println("Numero de nos gerados: " + nosgerados);
      System.out.println("Numero de nos utilizados: " + nosutilizados);
      System.out.println("Profundidade de solucao: " + profundidadeSolucao);
    }
  }

  Board Dfs_visit(Board tabuleiro, Board tabuleiroFinal,int limite){
    while(stack.size()!=0){
      Board newTabuleiro = stack.pop();
      nosutilizados++;
      if(atingiuSolucao(newTabuleiro,tabuleiroFinal) == true){
        return newTabuleiro;
      }
      else{
        if(newTabuleiro.depth < limite){
          geraFilhos(newTabuleiro);
          insertInStack();
        }
      }
    }
    return null;
  }

  void insertInStack(){
    while(temp.size()!=0){
      Board tabuleiro = temp.pop();
      stack.addLast(tabuleiro);
    }
  }

  void geraFilhos(Board tabuleiro){
    Board tabDown = tabuleiro.moveDown();
    Board tabUp = tabuleiro.moveUp();
    Board tabLeft = tabuleiro.moveLeft();
    Board tabRight = tabuleiro.moveRight();
    if(tabDown != null && checkRepeated(tabDown)==false){
      temp.addLast(tabDown);
      nosgerados++;
    }
    if(tabUp != null && checkRepeated(tabUp)==false){
      temp.addLast(tabUp);
      nosgerados++;
    }
    if(tabLeft != null && checkRepeated(tabLeft)==false){
      temp.addLast(tabLeft);
      nosgerados++;
    }
    if(tabRight != null && checkRepeated(tabRight)==false){
      temp.addLast(tabRight);
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
