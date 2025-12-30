package GameXO;

import java.util.Arrays;

public class Game {
    public static final int SIZE = 5;
    private static final int WIN = 4;
    private static final char EMPTY = '-';

    private final char[][] board = new char[SIZE][SIZE];

    private Player playerturn;
    private Player X;
    private Player O;

    private boolean gameOver = false;
    private char winner = '?';

    public Game() {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(board[i], EMPTY);
        }
    }

    public synchronized char[][] GetBoard() {
        return board;
    }

    public synchronized char getTurn() {
        return (playerturn == null) ? '?' : playerturn.getSymbol();
    }

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public synchronized void setWinner(char w) {
        winner = w;
        gameOver = true;
    }

    public synchronized void setDraw() {
        winner = '?';
        gameOver = true;
    }

    public synchronized void switchTurn() {
        playerturn = (playerturn == X) ? O : X;
    }

    public synchronized void input(int row, int col, char symbol) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Row/col out of bounds.");
        }
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Symbol must be 'X' or 'O' (letter O).");
        }
        if (board[row][col] != EMPTY) {
            throw new IllegalArgumentException("Cell not empty: (" + row + "," + col + ")");
        }
        board[row][col] = symbol;
    }

    public synchronized boolean isBoardFull() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    public synchronized int getRandomFreeCell() {
        int[] free = new int[SIZE * SIZE];
        int k = 0;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == EMPTY) {
                    free[k++] = r * SIZE + c;
                }
            }
        }

        if (k == 0)
            return -1;
        return free[(int) (Math.random() * k)];
    }

    public synchronized boolean checkWin(char symbol) {
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 } };

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] != symbol)
                    continue;

                for (int[] d : dirs) {
                    int dr = d[0], dc = d[1];

                    int endR = r + dr * (WIN - 1);
                    int endC = c + dc * (WIN - 1);
                    if (endR < 0 || endR >= SIZE || endC < 0 || endC >= SIZE)
                        continue;

                    boolean ok = true;
                    for (int i = 1; i < WIN; i++) {
                        if (board[r + dr * i][c + dc * i] != symbol) {
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

    public void startGameWithAI(Player X, Player O) {
        this.X = X;
        this.O = O;
        this.playerturn = X;
        this.gameOver = false;
        this.winner = '?';

        Thread tx = new Thread(X);
        Thread to = new Thread(O);

        tx.start();
        to.start();

        synchronized (this) {
            while (!gameOver) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        printBoard();
        if (winner == 'X' || winner == 'O') {
            System.out.println("Player " + winner + " wins!");
        } else {
            System.out.println("Draw!");
        }
    }

    public synchronized void printBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }
}
