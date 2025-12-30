package GameXO;

public class SelfPlayer extends Player {

    public SelfPlayer(char symbol, Game game) {
        super(symbol, game);
    }

    @Override
    public void run() {
        while (!game.isGameOver()) {
            synchronized (game) {
                while (!game.isGameOver() && game.getTurn() != symbol) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (game.isGameOver())
                    return;

                int move = game.getRandomFreeCell();
                if (move == -1) {
                    game.setDraw();
                    game.notifyAll();
                    return;
                }

                int row = move / Game.SIZE;
                int col = move % Game.SIZE;

                game.input(row, col, symbol);
                System.out.println(" ");
                game.printBoard();
                System.out.println(" ");

                if (game.checkWin(symbol)) {
                    game.setWinner(symbol);
                    game.notifyAll();
                    return;
                }

                if (game.isBoardFull()) {
                    game.setDraw();
                    game.notifyAll();
                    return;
                }

                try {
                    Thread.sleep(1000); // Pause for half a second to simulate thinking
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                game.switchTurn();
                game.notifyAll();
            }
        }
    }
}
