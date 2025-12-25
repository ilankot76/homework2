package GameXO;
public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        SelfPlayer X = new SelfPlayer('X', game);
        SelfPlayer O = new SelfPlayer('O', game);
        game.startGameWithAI(X, O);

    }
}
