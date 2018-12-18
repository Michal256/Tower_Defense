package Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel_ImageLoader extends JPanel {
    /**
     * image-stores buffered image data
     */
    private BufferedImage image;

    /**
     * Method which is responsible for file handling and loading image buffer data.
     * @param nameOfFile File which will be opened, to load the image buffered data.
     */
    public Panel_ImageLoader(String nameOfFile)
    {

        try{
            image= ImageIO.read(new File(""+nameOfFile));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Method which is used to draw components.
     * @param g Screen JFrame graphics
     */
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
