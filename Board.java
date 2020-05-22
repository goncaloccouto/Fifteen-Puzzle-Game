class Board{
  int [][] matrix = new int[4][4];
  Board pai;
  Board next;
  String op;
  int depth;
  int heuristica;

  Board(int array[], Board pai, Board next, int depth, String op){
    int conta = 0;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        matrix[i][j] = array[conta];
        conta++;
      }
    }
    this.pai = pai;
    this.next = next;
    this.depth = depth;
    this.op = op;
    this.heuristica = 0;
  }

  Board(int array[], Board pai, Board next, int depth, String op, int heuristica){
    int conta = 0;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        matrix[i][j] = array[conta];
        conta++;
      }
    }
    this.pai = pai;
    this.next = next;
    this.depth = depth;
    this.op = op;
    this.heuristica = heuristica;
  }

  int[][] getValue(){
    return matrix;
  }

  Board getNext(){
    return next;
  }

  void setNext(Board n){
    this.next = n;
  }

  int get0Position(){
    int pos=0;
    for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
        if(matrix[i][j] == 0)
          return pos;
        else
          pos++;
      }
    }
    return pos;
  }

  int get0line(){
    return (get0Position())/4;
  }

  int get0column(){
    return (get0Position())%4;
  }

  Board moveUp(){
    int line0 = get0line();
    int column0 = get0column();
    if(line0 == 0)
      return null;
    else{
      Board copia = this.copyBoard("U");
      int temp = copia.matrix[line0-1][column0];
      copia.matrix[line0-1][column0] = 0;
      copia.matrix[line0][column0] = temp;
      return copia;
    }
  }

  Board moveDown(){
    int line0 = get0line();
    int column0 = get0column();
    if(line0 == 3)
      return null;
    else{
      Board copia = this.copyBoard("D");
      int temp = copia.matrix[line0+1][column0];
      copia.matrix[line0+1][column0] = 0;
      copia.matrix[line0][column0] = temp;
      return copia;
    }
}

  Board moveLeft(){
    int line0 = get0line();
    int column0 = get0column();
    if(column0 == 0)
      return null;
    else{
      Board copia = this.copyBoard("L");
      int temp = copia.matrix[line0][column0-1];
      copia.matrix[line0][column0-1] = 0;
      copia.matrix[line0][column0] = temp;
      return copia;
    }
  }

  Board moveRight(){
    int line0 = get0line();
    int column0 = get0column();
    if(column0 == 3)
      return null;
    else{
      Board copia = this.copyBoard("R");
      int temp = copia.matrix[line0][column0+1];
      copia.matrix[line0][column0+1] = 0;
      copia.matrix[line0][column0] = temp;
      return copia;
    }
  }

  void printBoard(){
    for(int i = 0; i < 4; i++){
      System.out.println("--------------------");
      System.out.print("|");
      for(int j = 0; j < 4; j++){
        System.out.printf("%2d", matrix[i][j]);
        System.out.print(" | ");
      }
      System.out.println();
    }
    System.out.println("--------------------");

  }

  Board copyBoard(String op){
    int array[] = new int[16];
    int aux = 0;
    for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
        array[aux] = this.matrix[i][j];
        aux++;
      }
    }
    Board copia = new Board(array,this,null,this.depth + 1,op);
    return copia;
  }

  int[] convertBoardToArray(){
    int array[] = new int[16];
    int aux = 0;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        array[aux] = this.matrix[i][j];
        aux++;
      }
    }
    return array;
  }
}
