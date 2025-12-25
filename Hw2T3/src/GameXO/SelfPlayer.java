package GameXO;

public class SelfPlayer extends Player {

    public SelfPlayer(char symbol, Game game) {
        super(symbol, game);
    }

    @Override
    public void run() {
            if(game.getTurn() == this.symbol){
                makeMove();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    


    public void makeMove(){
       double[] freeCells = game.getFreeCells();
        int randIndex = (int) (Math.random() * freeCells.length);
        double move = freeCells[randIndex];
        int row = (int) move;
        int col = (int) ((move - row) * 10);
        game.board[row][col] = this.symbol;
    }

}
