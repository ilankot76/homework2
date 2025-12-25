package XO;

public class Game {
   byte[][] board = new byte[5][5];

   public Game() {
   }

   public void printBoard() {
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            if(board[i][j]==0){System.out.print("0 ");}
             else {System.out.print("X ");}
         }
         System.out.println();
      }
   }

}
