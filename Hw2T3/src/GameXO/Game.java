package GameXO;

import java.util.Arrays;

public class Game {
    char board[][] = new char[5][5];
    private Player playerturn;
    private Player X;
    private Player O;

    public void switchTurn() {
        if (playerturn == X) {
            playerturn = O;
        } else {
            playerturn = X;
        }
    }

    public void startGameWithAI(Player X, Player O) {
        this.X = X;
        this.O = O;
        this.playerturn = X;
        Thread playerXThread = new Thread(X);
        Thread playerOThread = new Thread(O);
        for (int i = 0; i < 5; i++)
            Arrays.fill(board[i], ' ');
        int turns = 0;

        while (true) {
            double[] freeCells = getFreeCells();
            switchTurn();
            if (freeCells.length == 0) {
                System.out.println("Draw!");
                break;
            }
            if (checkWin()) {
                System.out.println("Player " + playerturn.getSymbol() + " wins!");
                break;
            }
            if (playerturn == X) {
                playerXThread.run();
            } else {
                playerOThread.run();
            }

            System.out.println("turns: " + (++turns));
            System.out.println();
            System.out.println("Next turn: Player " + playerturn.getSymbol());
            printBoard();
            System.out.println();

        }
    }

    public Game() {

        for (int i = 0; i < 5; i++)
            Arrays.fill(board[i], ' ');

    }

    public char[][] board() {
        return board;
    }

    public void GameWithPlayer(Player temp, char symbol) {
        if (symbol == 'X') {
            this.X = new UserPlayer('X', this);
            this.O = new SelfPlayer('O', this);
            Thread playerOThread = new Thread(O);
        } else {
            this.O = new UserPlayer('O', this);
            this.X = new SelfPlayer('X', this);
            Thread playerXThread = new Thread(X);
        }

        this.playerturn = X;

        for (int i = 0; i < 5; i++)
            Arrays.fill(board[i], ' ');

        int turns = 0;

        while (true) {
            double[] freeCells = getFreeCells();
            switchTurn();
            if (freeCells.length == 0) {
                System.out.println("Draw!");
                break;
            }
            if (checkWin()) {
                System.out.println("Player " + playerturn.getSymbol() + " wins!");
                break;
            }

            System.out.println("turns: " + (++turns));
            System.out.println();
            System.out.println("Next turn: Player " + playerturn.getSymbol());
            printBoard();
            System.out.println();

        }
    }

    public boolean checkWin() {
        final int SIZE = 5;
        final int WIN = 4;

        int[][] directions = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 } };

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                char s = board[r][c];
                if (s != 'X' && s != 'O')
                    continue;

                for (int[] d : directions) {
                    int dr = d[0], dc = d[1];
                    boolean ok = true;

                    for (int i = 1; i < WIN; i++) {
                        int rr = r + dr * i;
                        int cc = c + dc * i;

                        if (rr < 0 || rr >= SIZE || cc < 0 || cc >= SIZE || board[rr][cc] != s) {
                            ok = false;
                            break;
                        }
                    }

                    if (ok)
                        return true;
                }
            }
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");

            }
            System.out.println();
        }
    }

    public char getTurn() {
        return playerturn == X ? 'X' : 'O';
    }

    public double[] getFreeCells() {
        double[] freeCells = new double[25];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == ' ') {
                    freeCells[index] = i + j / 10.0;
                    index++;
                }
            }
        }
        return Arrays.copyOf(freeCells, index);
    }

}
