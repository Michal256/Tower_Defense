package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScreenView extends JFrame {
    /**
     * Screen-stores actual JFrame
     */
    private JFrame Screen;

    /**
     * Constructor which set's basic settings for the JFrame frame.
     */
    public ScreenView()
    {
        this.Screen=Game.getScreen();


        Screen.setSize(1024, 768);
        Screen.setMinimumSize(new Dimension(1024, 768));

        Screen.setTitle("Tower Defense 10 Waves");
        Screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen.setLocationRelativeTo(null);
        Screen.pack();

    }

}
