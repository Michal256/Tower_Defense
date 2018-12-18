package Game;

import javax.swing.*;
import java.awt.*;

public class StringPainter extends JPanel {

    /**
     * text- text to be drawn
     * x,y- cords of where text should be drawn
     */
    private String text;
    private int x,y;

    /**
     * Constructor which is responsible for setting basic variables,
     * which will be used to draw a string on screen
     * @param text text which will be drawn on the screen
     * @param x where draw method at x axis should start drawing string
     * @param y where draw method at y axis should start drawing string
     */
    public StringPainter(String text,int x,int y)
    {
        this.text=text;
        this.x=x;
        this.y=y;
    }


    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method which is responsible for drawing a string on the screen,
     * on specific place with specific text.
     * @param g actual screen graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawString(this.text,this.x,this.y);
    }
}
