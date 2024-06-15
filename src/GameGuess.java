import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class GameGuess extends JFrame
{
    private int Guesses = 0;
    private int GuessOld = 0;
    private int number; // application's number
    private JTextField guessInputJTextField; // user input field
    private JLabel prompt1JLabel; // first line of instruction
    private JLabel prompt2JLabel; // second line of instructions
    private JLabel messageJLabel; // displays message of game status
    private JLabel message2JLabel; //display number of guesses
    private JLabel message3JLabel;
    private JLabel random1 = new JLabel("");
    private JButton newGameJButton; // creates new game
    private Color background; // background color of application

    // set up GUI and initialize values

    public GameGuess()
    {
        super("Guessing Game");
        setLayout(new FlowLayout());
        background = Color.LIGHT_GRAY; // set background to light gray
        prompt1JLabel = new JLabel("I have a number between 1 and 100."); // describe game
        add(prompt1JLabel);
        prompt2JLabel = new JLabel("Can you guess my number? Type your Guess and then press 'Enter':"); // prompt user
        add(prompt2JLabel);
        guessInputJTextField = new JTextField(5); // to enter guesses
        guessInputJTextField.addActionListener(new GuessHandler());
        add(guessInputJTextField);
        messageJLabel = new JLabel("");
        add(messageJLabel);
        message2JLabel = new JLabel ("");
        add (message2JLabel);
        message3JLabel = new JLabel ("");
        add (message3JLabel);
        newGameJButton = new JButton("New Game"); // create "New Game" button
        add(newGameJButton); // add newGame button to JFrame

        Random generator = new Random();
        number = generator.nextInt(1001);//create random number

        newGameJButton.addActionListener(new ActionListener() // anonymous inner class
                                         {
                                             public void actionPerformed(ActionEvent e)
                                             {
                                                 theGame ();
                                             } // end method actionPerformed

                                         } // end anonymous inner class

        ); // end call to addActionListener

        theGame(); // start new game
    } // end GuessGameFrame constructor

    // choose a new random number
    public void theGame()
    {
        guessInputJTextField.setText("");
        Random generator = new Random();
        number = generator.nextInt(100);
        random1.setText("" + number);
        SwingUtilities.updateComponentTreeUI(random1);
        messageJLabel.setText("");
        guessInputJTextField.setEditable(true);
        message2JLabel.setText("");
        Guesses = 0;
        message3JLabel.setText("Enter guess # " + Integer.toString(++Guesses));
        getContentPane().setBackground(Color.LIGHT_GRAY);
    } // end method theGame

    // change background color
    class GuessHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int Guess;
            Guess = Integer.parseInt(guessInputJTextField.getText());
            SwingUtilities.updateComponentTreeUI(guessInputJTextField);

            if (Math.abs(number - Guess) < Math.abs(number - GuessOld))
            {
                // Hotter
                getContentPane().setBackground(Color.RED);
                message2JLabel.setText("You're getting warmer!");
                SwingUtilities.updateComponentTreeUI(message2JLabel);
            }

            if (Math.abs(number - Guess) > Math.abs(number - GuessOld))
            {
                // Colder
                message2JLabel.setText("You're getting colder!");
                SwingUtilities.updateComponentTreeUI(message2JLabel);
                getContentPane().setBackground(Color.BLUE);
            }

            GuessOld = Guess;
            if (Guess > number)
            {
                messageJLabel.setText(+ Guess + " is too high.");
                SwingUtilities.updateComponentTreeUI(messageJLabel);
            }

            if (Guess < number)
            {
                messageJLabel.setText(+ Guess + " is too low.");
                SwingUtilities.updateComponentTreeUI(messageJLabel);
            }// end if

            Guesses++;
            message3JLabel.setText("Enter guess #  " + Integer.toString(Guesses));

            if (Guess == number)
            {
                messageJLabel.setText("Congratulations!  You guessed my number!");
                getContentPane().setBackground(Color.YELLOW);
                message2JLabel.setText("");

                SwingUtilities.updateComponentTreeUI(messageJLabel);
                Guesses++;

                message3JLabel.setText("# of guesses = " + Integer.toString(Guesses));
                SwingUtilities.updateComponentTreeUI(message3JLabel);
                guessInputJTextField.setEditable(false);
            }
        }
    }
}
