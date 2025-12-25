package XO;

public class Game {
   int[][] board = new int[5][5];

   public Game() {
   }

   public void printBoard() {
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            System.out.print(board[i][j] + " ");
         }
         System.out.println();
      }
   }

}
