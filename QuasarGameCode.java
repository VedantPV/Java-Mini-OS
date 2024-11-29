package guitest;

import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.JPanel;
public class QuasarGameCode
{
    int credits;
    int TwentyInARowAchievement = 0;
    boolean titleScreen = false;
    public QuasarGameCode(int credits)
    {
        this.credits = credits;
    }

    private boolean hasLetters(String s)
    {
        for(char c: s.toCharArray())
        {
            if(Character.isLetter(c) || c == '.')
            {
                return true;
            }
        }
        return false;
    }




    public void MainQuasarGame() throws InterruptedException {
        if(!titleScreen)
        {
            JOptionPane.showMessageDialog(null, "________                                     \n" +
                    "\\_____  \\  __ _______    ___________ _______ \n" +
                    " /  / \\  \\|  |  \\__  \\  /  ___/\\__  \\\\_  __ \\\n" +
                    "/   \\_/.  \\  |  // __ \\_\\___ \\  / __ \\|  | \\/\n" +
                    "\\_____\\ \\_/____/(____  /____  >(____  /__|   \n" +
                    "       \\__>          \\/     \\/      \\/   ");
            JOptionPane.showMessageDialog(null, "\n\nRULES: \n Welcome to Quasar! This game works in a similar matter to BlackJack\nPick a choice of a random number between either 1-8 or 4-7. \n" +
                    "You can take your winnings after you reach 17, but you can keep going in the hopes of getting closer to 20 or even matching 20 for a bigger prize.\n" +
                    "If you get over 20, you lose all your winnings.\nYou start with 500 credits.\n\n\n ");
            titleScreen = true;
        }

        int winnings = 0;
        int addNum;
        Random startingRandNum = new Random();
        Random numAdded = new Random();
        String[] options1 = {"1-8", "4-7"};
        String[] options2 = {"1-8", "4-7", "Take Winnings"};
        String[] options3 = {"Yes, I will keep going!", "No, I will exit now!"};
        String[] jokes = {"Mr. Green is looking for new applicants of the Green Gambling Committee. You should consider it.", "Studies show that 99% of people who quit now are just one game away from hitting jackpot.", "Do your parents know about your gambling addiction? Because I know."};
        JPanel panel = new JPanel();


        String stringBet = JOptionPane.showInputDialog("Enter the amount you want to bet in an integer format(Current Number of Credits = "+credits+"): ");
        while(stringBet == null)
        {
            stringBet = JOptionPane.showInputDialog(panel,"Enter a number: ", null, JOptionPane.ERROR_MESSAGE);
        }
        while(hasLetters(stringBet))
        {
            stringBet = JOptionPane.showInputDialog(panel,"Your input must be an integer number, NOT A LETTER: ", null, JOptionPane.ERROR_MESSAGE);
            while(stringBet == null)
            {
                stringBet = JOptionPane.showInputDialog(panel,"Enter a number: ", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        int bet = Integer.parseInt(stringBet);
        while(bet <= 0 || bet > credits)
        {
            stringBet = JOptionPane.showInputDialog(panel,"Your input must be within the boundaries 1 to "+credits+": ", null, JOptionPane.ERROR_MESSAGE);
            bet = Integer.parseInt(stringBet);
        }
        credits = credits - bet;

        boolean won = false, takeWinnings = false;
        int choice = 0;
        int startingNum = (int) startingRandNum.nextInt(7) + 1;
        while(startingNum <= 20) {
            if(takeWinnings)
            {
                TwentyInARowAchievement = 0;
                break;
            }
            if(startingNum == 20)
            {
                TwentyInARowAchievement++;
                credits += winnings;
                JOptionPane.showMessageDialog(panel, "You got exactly 20! Enjoy your full winnings: "+winnings);
                won = true;
                break;
            }
            if(startingNum <= 16) {
                choice = JOptionPane.showOptionDialog(panel,
                        "Choose one of two choices (Current Number: " + startingNum + "), Goal: 20 | Winnings: " + winnings,
                        "Game Time",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options1,
                        null);
            }
            else
            {
                choice = JOptionPane.showOptionDialog(panel,
                        "Choose one of three choices (Current Number: " + startingNum + "), Goal: 20 | Winnings: " + winnings,
                        "Game Time",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options2,
                        null);
            }
            switch (choice) {
                case JOptionPane.YES_OPTION:
                    addNum = numAdded.nextInt(8)+1;
                    startingNum += addNum;
                    JOptionPane.showMessageDialog(panel, "Number Added: "+addNum+"\t Current Number: "+startingNum);
                    if(startingNum == 17)
                    {
                        winnings = bet;
                    }
                    else if(startingNum == 18)
                    {
                        winnings = (int)(bet + (bet*0.25));
                    }
                    else if(startingNum == 19)
                    {
                        winnings = (int)(bet + (bet*.50));
                    }
                    else if(startingNum == 20)
                    {
                        winnings = bet*2;
                    }
                    break;


                case JOptionPane.NO_OPTION:
                    addNum = numAdded.nextInt(4)+4;
                    startingNum += addNum;
                    JOptionPane.showMessageDialog(panel, "Number Added: "+addNum+"\t Current Number: "+startingNum);
                    if(startingNum == 17)
                    {
                        winnings = bet;
                    }
                    else if(startingNum == 18)
                    {
                        winnings = (int)(bet + (bet*0.25));
                    }
                    else if(startingNum == 19)
                    {
                        winnings = (int)(bet + (bet*.50));
                    }
                    else if(startingNum == 20)
                    {
                        winnings = bet*2;
                    }
                    break;

                case JOptionPane.CANCEL_OPTION:
                    credits += winnings;
                    JOptionPane.showMessageDialog(panel, "You chose to take the money, enjoy your winnings: "+winnings);
                    won = true;
                    takeWinnings = true;
                    break;
            }

        }

        if(!won)
        {
            JOptionPane.showMessageDialog(panel, "Whoops! You went over 20! You get nothing.");
            TwentyInARowAchievement = 0;
        }
        if(credits == 0)
        {
            JOptionPane.showMessageDialog(panel, "You have run out credits, thanks for playing!");
            System.exit(0);
        }
        else
        {
            if(TwentyInARowAchievement == 3)
            {
                JOptionPane.showMessageDialog(panel, "You have unlocked the '3-In-A-Row' Achievement! Maybe this is a sign to go touch grass?", "Achievement Unlocked", JOptionPane.INFORMATION_MESSAGE);

            }
            choice = JOptionPane.showOptionDialog(panel,
                    "Would you like to continue playing?",
                    "Play Again?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    options3,
                    null);
            while (choice < 0) {
                choice = JOptionPane.showOptionDialog(panel,
                        jokes[startingRandNum.nextInt(3)],
                        "Play Again?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options3,
                        null);
            }
            switch(choice)
            {
                case JOptionPane.YES_OPTION: MainQuasarGame();

                case JOptionPane.NO_OPTION:
                    JOptionPane.showMessageDialog(panel,"Total Winnings: "+credits+" credits!");
                    JOptionPane.getRootFrame().dispose();
            }
        }

    }





}
