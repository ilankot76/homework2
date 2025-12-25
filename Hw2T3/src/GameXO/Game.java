package GameXO;

import java.util.Arrays;

public class Game implements Runnable {
    char board[][] = new char[5][5];
    private Player playerturn;
    private Player X;
    private Player O;


    public Game(){
        for (int i = 0; i < 5; i++) 
     Arrays.fill(board[i], ' ');



     
    
    }

    @Override
    public void run() {
        while (true) {
            printBoard();
            double[] freeCells = getFreeCells();
            if (freeCells.length == 0) {
                System.out.println("Draw!");
                break;
            }
            // Here you would typically get the move from the current player
            // For demonstration, we'll just pick the first free cell
            double move = freeCells[0];
            int row = (int) move;
            int col = (int) ((move - row) * 10);
            board[row][col] = (playerturn == X) ? 'X' : 'O';
            // Switch turns
            playerturn = (playerturn == X) ? O : X;
        }        
    }


   
    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");
                
            }
            System.out.println();
        }
    }

     public Player getTurn(){
         return null;
        }

        public double[] getFreeCells(){
            double[] freeCells = new double[25];
            int index = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(board[i][j] == ' '){
                        freeCells[index]=i+j/10.0;
                        index++;
                    }
                }
            }
            return Arrays.copyOf(freeCells, index);
        }

}

        



