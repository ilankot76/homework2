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

                board[r][c] = getSymbol();
                game.printBoard();

                if (game.checkWin(getSymbol())) {
                    System.out.println("Winner: " + getSymbol());
                    return;
                }

                game.switchTurn();
            }
        }

    }

}