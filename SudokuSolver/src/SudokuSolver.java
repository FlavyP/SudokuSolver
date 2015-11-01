public class SudokuSolver
{
   public static void main(String[] args)
   {
      new SudokuSolver().run();
   }
   
   private int[][] sudokuBoard = new int[][]{
      {5, 3, 0, 0, 7, 0, 0, 0, 0},
      {6, 0, 0, 1, 9, 5, 0, 0, 0},
      {0, 9, 8, 0, 0, 0, 0, 6, 0},
      {8, 0, 0, 0, 6, 0, 0, 0, 3},
      {4, 0, 0, 8, 0, 3, 0, 0, 1},
      {7, 0, 0, 0, 2, 0, 0, 0, 6},
      {0, 6, 0, 0, 0, 0, 2, 8, 0},
      {0, 0, 0, 4, 1, 9, 0, 0, 5},
      {0, 0, 0, 0, 8, 0, 0, 7, 9}
   }; 
   
   public void run()
   {
      printBoard(sudokuBoard);
      System.out.println("\nSolving...\n");
      solve(0, 0, sudokuBoard);
   }
   
   private void solve(int row, int col, int[][] sudokuBoard)
   {
      if (row >= sudokuBoard.length)
         printBoard(sudokuBoard);

      else if (sudokuBoard[row][col] != 0)
      {
         if (col < sudokuBoard.length-1)
            solve(row, col + 1, sudokuBoard);
         else
            solve(row + 1, 0, sudokuBoard);
      }
      
      else
      {
         for (int value = 1; value <= sudokuBoard.length; value++)
         {
            if (isMoveValid(row, col, value, sudokuBoard))
            {
               sudokuBoard[row][col] = value;

               if (col < sudokuBoard.length-1)
                  solve(row, col + 1, sudokuBoard);
               else
                  solve(row + 1, 0, sudokuBoard);
            }
         }
         sudokuBoard[row][col] = 0;
      }
   }
   
   private boolean checkRow(int row, int value, int[][] sudokuBoard)
   {
      for(int col = 0; col < sudokuBoard.length; col++)
      {
         if(sudokuBoard[row][col] == value)
            return false;
      }
      return true;
   }
   
   private boolean checkCol(int col, int value, int[][] sudokuBoard)
   {
      for(int row = 0; row < sudokuBoard.length; row++)
      {
         if(sudokuBoard[row][col] == value)
            return false;
      }
      return true;
   }
   
   private boolean checkSquare(int row, int col, int value, int[][] sudokuBoard)
   {
      int rowSquare = (row/3)*3;
      int colSquare = (col/3)*3;
      
      for(int i = rowSquare; i < rowSquare+3; i++)
      {
         for(int j = colSquare; j < colSquare+3; j++)
         {
            if(sudokuBoard[i][j] == value)
               return false;
         }
      }
      
      return true;
   }
   
   private boolean isMoveValid(int row, int col, int value, int[][] sudokuBoard)
   {
      if(checkRow(row, value, sudokuBoard) && checkCol(col, value, sudokuBoard) && checkSquare(row, col, value, sudokuBoard))
         return true;
      else
         return false;
   }
   
   private void printBoard(int[][] sudokuBoard)
   {
      for (int row = 0; row < sudokuBoard.length; row++)
      {
         if (row % 3 == 0)
            System.out.println(" -----------------------");
         
         for (int col = 0; col < sudokuBoard.length; col++)
         {
            if (col % 3 == 0)
               System.out.print("| ");
            
            System.out.print(sudokuBoard[row][col] + " ");
         }         
         System.out.print("|\n");
      }
      System.out.println(" -----------------------");
   }

}
