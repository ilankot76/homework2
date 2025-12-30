package GameXO;

import java.util.Scanner;

public class UserPlayer extends Player {

    private final Scanner scanner = new Scanner(System.in);

    public UserPlayer(char symbol, Game game) {
        super(symbol, game);
    }

    @Override
    public void run() {
        char[][] board = game.GetBoard();
        while (true) {

            synchronized (game) {
                if (game.isGameOver())
                    return;

                while (!game.isGameOver() && game.getTurn() != symbol) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                int r, c;
                while (true) {
                    System.out.print("Enter row (0-4): ");
                    r = scanner.nextInt();
                    System.out.print("Enter col (0-4): ");
                    c = scanner.nextInt();

                    if (r >= 0 && r < 5 && c >= 0 && c < 5 &&
                            board[r][c] == '-') {
                        break;
                    }
                    System.out.println("Invalid cell, try again.");
                }
                    game.input(r, c, getSymbol());

                    game.printBoard();

                    if (game.checkWin(getSymbol())) {
                        game.setWinner(getSymbol());
                        game.notifyAll();
                        return;
                    }
                    if (game.isBoardFull()) {
                        game.setDraw();
                        game.notifyAll();
                        return;
                    }
                        game.switchTurn();
                        game.notifyAll();
            }
        }

    }

}