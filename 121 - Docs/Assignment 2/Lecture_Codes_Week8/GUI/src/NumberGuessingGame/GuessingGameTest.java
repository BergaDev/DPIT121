package NumberGuessingGame;

import javax.swing.JFrame;

/**
 *
 * @author Hooman
 */
public class GuessingGameTest 
{

    public static void main(String[] args) 
    {
        GuessingGameTest ja = new GuessingGameTest();
        ja.guessGameFrame();
    }
    
    public void guessGameFrame()
    {
        
        GuessGameFrame guessGameFrame = new GuessGameFrame(); 
        guessGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guessGameFrame.setSize(300, 250); // set frame size
        guessGameFrame.setVisible(true); // display frame  
    } 
}
