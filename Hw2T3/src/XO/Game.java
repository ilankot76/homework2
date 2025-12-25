package XO;

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
        // TODO Auto-generated method stub
        
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

        



