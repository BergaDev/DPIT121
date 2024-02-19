package NumberGuessingGame;

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

public class GuessGameFrame extends JFrame 
{
    private static Random generator = new Random();
    private int number; // number chosen by application
    private int guessCount; // number of guesses
    private int lastDistance; // distance between last guess and number
    private JTextField guessInputJTextField; // for guessing
    private JLabel prompt1JLabel; // first prompt to user
    private JLabel prompt2JLabel; // second prompt to user
    private JLabel messageJLabel; // displays message of game status
    private JButton newGameJButton; // creates new game
    private Color background; // background color of application

    // set up GUI and initialize values
    public GuessGameFrame()
    {
        super("Guessing Game");

        guessCount = 0; // initialize number of guesses to 0
        background = Color.LIGHT_GRAY; // set background to light gray

        // describe game and prompt user
        prompt1JLabel = new JLabel("I have a number between 1 and 1000.");
        prompt2JLabel = new JLabel("Can you guess my number?");

        guessInputJTextField = new JTextField(5); // to enter guesses
        guessInputJTextField.addActionListener(new GuessHandler());
        messageJLabel = new JLabel("Please enter your first guess.");

        newGameJButton = new JButton("New Game"); // starts new game
        newGameJButton.addActionListener( new ActionListener() // anonymous inner class
            {
                public void actionPerformed(ActionEvent e)
                {
                    messageJLabel.setText("Please enter your first guess.");
                    guessInputJTextField.setText(""); // reset guess field
                    guessInputJTextField.setEditable(true); // allow guesses
                    background = Color.LIGHT_GRAY; // reset background color
                    theGame(); // start new game
                    repaint(); // repaint application
                } 
            } // end anonymous inner class
        ); // end call to addActionListener

        setLayout(new FlowLayout()); // set layout
        add(prompt1JLabel); // add first prompt
        add(prompt2JLabel); // add second prompt
        add(messageJLabel); // add message label
        add(guessInputJTextField); // add guessing textfield
        add(newGameJButton); // add button to create new game

        theGame(); // start new game
    } 

    // choose a new random number
    public void theGame()
    {
       number = generator.nextInt(1000) + 1;
    } 

    // change background color
    public void paint(Graphics g)
    {
       super.paint(g);
       getContentPane().setBackground(background); // set background
    }

    // react to new guess
    public void react(int guess) 
    {
        guessCount++; // increment guesses
        int currentDistance = 1000; // set initial distance

        // first guess
        if (guessCount == 1) 
        {
            lastDistance = Math.abs(guess - number);
            if (guess > number)
                messageJLabel.setText("Too High. Try a lower number.");
            else
                messageJLabel.setText("Too Low. Try a higher number.");
        }
        else 
        {
           currentDistance = Math.abs(guess - number);

           // guess is too high
            if (guess > number) 
            {
                messageJLabel.setText("Too High. Try a lower number.");
                background = (currentDistance <= lastDistance) ?
                   Color.RED : Color.BLUE;
                lastDistance = currentDistance;
            } 
            else if (guess < number) // guess is too low
            {
                messageJLabel.setText("Too Low. Try a higher number.");
                background = (currentDistance <= lastDistance) ?
                   Color.RED : Color.BLUE;
                lastDistance = currentDistance;
            } 
            else // guess is correct
            {
                messageJLabel.setText("Correct!");
                background = Color.LIGHT_GRAY;
                guessInputJTextField.setEditable(false);
                guessCount = 0;
            }
            
            repaint();
        }
    }

    // inner class acts on user input
    class GuessHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           int guess = Integer.parseInt(guessInputJTextField.getText());
           react(guess);
        }
    } // end inner class GuessHandler
} // end class GuessGameFrame


/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 **************************************************************************/
