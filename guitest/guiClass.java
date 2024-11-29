package guitest;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class guiClass {

//Make a menu with different options pertaining to size, random object painting, and maybe even a game???
    JFrame frame = new JFrame("JFrame Example");
    BufferedImage graphic;
    String filepathMenu = "Menu Unlock.wav";

    String filepathConnect4 = "Connect4.wav";
    boolean quasarAdded = false;
    public static void PlaySound(String soundLocation)
    {
        try
        {
            File musicPath = new File(soundLocation);

            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else
            {
                System.out.println("The file couldn't be found");
            }
        }
        catch(Exception e)
        {
          System.out.println("Error: "+e);
        }

    }


    public void guiColor()
    {

        String[] buttonSizeOptions = {"Blue", "Reset", "Red"};

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);



        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem buttonItem = new JMenuItem("Button Color Changer");
        JMenuItem Connect4 = new JMenuItem("Play Connect 4");
        JMenuItem CheatCode = new JMenuItem("Enter Cheat Code");
        JMenuItem quasar = new JMenuItem("Play Quasar");
        fileMenu.add(buttonItem);
        fileMenu.addSeparator();
        fileMenu.add(Connect4);
        fileMenu.addSeparator();
        fileMenu.add(CheatCode);
        fileMenu.addSeparator();


        menuBar.add(fileMenu);



        // Create a panel with a button

        JButton button = new JButton("Color Changer");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        button.setForeground(Color.black);

        panel.add(button);

        // Add action to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                Color randColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                //JOptionPane.showMessageDialog(frame, "Button Clicked! Color Change!");
                panel.setBackground(randColor);
                button.setLocation(random.nextInt(250), random.nextInt(250));
            };
        });

        Connect4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaySound(filepathConnect4);
                JOptionPane.showMessageDialog(panel, "Connect 4 is in Development!");

            }
        });

        buttonItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showOptionDialog(panel,
                        "Choose a Color For Your Button",
                        "Button Color Change.show",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        buttonSizeOptions,
                        null);
                if (choice == 0) //Changing a button's color in JFrame is confusing.
                {
                    button.setBackground(Color.blue);
                } else if (choice == 1) {
                    button.setBackground(Color.GRAY);
                }
                else
                {
                    button.setBackground(Color.RED);
                }

            }
        });

        CheatCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cheat = JOptionPane.showInputDialog(panel, "Enter your cheat here: ");
                if(cheat.equalsIgnoreCase("quasar") && !quasarAdded)
                {
                    fileMenu.add(quasar);
                    fileMenu.revalidate();
                    PlaySound(filepathMenu);
                    quasarAdded = true;
                }

            }
        });

        quasar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuasarGameCode playGame = new QuasarGameCode(500);
                try {
                    playGame.MainQuasarGame();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        // Create another panel with text
        JPanel textPanel = new JPanel();
        JLabel label = new JLabel("Geeks Premier League 2023");
        textPanel.add(label);

        // Set layout for the main frame
        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(menuBar);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.SOUTH);



        frame.setVisible(true);

    }



}
