package GameXO;

public abstract  class Player implements  Runnable {
    protected  char symbol;
    protected  Game game;

    public Player(char symbol, Game game) {
        this.symbol = symbol;
        this.game = game;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

}

