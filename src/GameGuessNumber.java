import javax.swing.JFrame;

public class GameGuessNumber
{
    public static void main(String args[]) throws Exception
    {
        GameGuess game = new GameGuess();
        game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        game.setSize(550, 150);
        game.setVisible(true);
    }
}
