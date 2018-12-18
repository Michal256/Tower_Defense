package Menu;

import Main.Game;
import Game.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel
{
    /**
     * Screen- stores actual JFrame
     * buttonPanel etc.- stores JPanel object, which will divide Screen on proper sections.
     * startButton etc.-stores JButton objects
     *
     */
    private JFrame Screen;
    private JPanel buttonPanel,northPanel,southPanel,westPanel,eastPanel;
    private JButton startButton,optionButton,creditsButton,exitButton;

    /**
     * Constructor which is responsible for creating content of Menu panel
     */
    public Menu()
    {
        this.Screen= Game.getScreen();

        Screen.getContentPane().removeAll();
        Screen.getContentPane().invalidate();
        Screen.getContentPane().validate();

        Screen.setLayout(new BorderLayout());
        northPanel=new JPanel();
        southPanel=new JPanel();
        westPanel=new JPanel();
        eastPanel=new JPanel();
        buttonPanel=new JPanel();

        northPanel.setLayout(new GridLayout(1,3));
        southPanel.setLayout(new GridLayout(1,3));
        westPanel.setLayout(new GridLayout(7,1));
        eastPanel.setLayout(new GridLayout(7,1));
        buttonPanel.setLayout(new GridLayout(7,1));

        northPanel.setPreferredSize(new Dimension(Screen.getWidth(),300));

        startButton=new JButton("Graj");
        optionButton=new JButton("Opcje");
        creditsButton=new JButton("Informacje");
        exitButton=new JButton("Wyjdź z gry");

        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(optionButton);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(creditsButton);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(exitButton);

        Panel_ImageLoader img=new Panel_ImageLoader("img/logo/logo.png");
        northPanel.add(img);

        southPanel.add(Box.createRigidArea(new Dimension(0,150)));
        eastPanel.add(Box.createRigidArea(new Dimension(200,0)));
        westPanel.add(Box.createRigidArea(new Dimension(200,0)));



        Screen.add(buttonPanel,BorderLayout.CENTER);
        Screen.add(northPanel,BorderLayout.NORTH);
        Screen.add(southPanel,BorderLayout.SOUTH);
        Screen.add(eastPanel,BorderLayout.EAST);
        Screen.add(westPanel,BorderLayout.WEST);

        ListenForButton lForButton=new ListenForButton();
        startButton.addActionListener(lForButton);
        optionButton.addActionListener(lForButton);
        creditsButton.addActionListener(lForButton);
        exitButton.addActionListener(lForButton);

    }
    private class ListenForButton extends JFrame implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==startButton)
            {
                Screen.setResizable(true);
                Board GameArea=new Board();

                Screen.getContentPane().removeAll();
                Screen.setContentPane(GameArea);
                Screen.getContentPane().invalidate();
                Screen.getContentPane().validate();

                Screen.setVisible(true);


            }
            else if(e.getSource()==optionButton)
            {
                new Options();
                Screen.setVisible(true);
            }
            else if(e.getSource()==creditsButton)
            {
                new Credits();
            }
            else if(e.getSource()==exitButton)
            {
                System.exit(0);
            }
        }
    }
}
