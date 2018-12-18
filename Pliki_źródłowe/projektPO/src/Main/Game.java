package Main;

import Menu.Menu;

import javax.swing.*;

/**
 * @author Michał Ziółek
 */
public class Game {

    /**
     * Screen- JFrame frame which will be used, to set proper content.
     */
    private static JFrame Screen;

    /**
     * Main method, which will launch whole application.
     * @param args number of command line arguments passed in
     */
    public static void main(String[] args)
    {

            new Game();


    }

    /**
     *Constructor which creates ScreenView and gets proper content for it.
     */
    public Game()
    {
        Screen=new JFrame();
        new ScreenView();
        getMenu();//Menu implements Board(in Game package)

    }

    /**
     * Creates menu layout.
     */
    public void getMenu()
    {
        Screen.setResizable(false);
        new Menu();
        Screen.setVisible(true);
    }

    /**
     * Getter for Screen, used in many methods to change content of the Screen
     * @return JFrame frame
     */
    public static JFrame getScreen()
    {
        return Screen;
    }
}
