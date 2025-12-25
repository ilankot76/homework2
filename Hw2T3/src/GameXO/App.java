package GameXO;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Game game = new Game();
        SelfPlayer X = new SelfPlayer('X', game);
        SelfPlayer O = new SelfPlayer('O', game);
        game.startGameWithAI(X, O);

    }
}
