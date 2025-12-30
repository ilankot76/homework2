package GameXO;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Game game = new Game();
        SelfPlayer ai = new SelfPlayer('O', game);
        game.StartGameWithUser(ai, 'X');

        scanner.close();
    }
}
