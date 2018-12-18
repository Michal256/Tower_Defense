package Game;

import Main.Game;

import javax.swing.*;
public class GetGameTime extends JPanel implements Runnable {
    /**
     * gameTime- stores total number of seconds already played
     * Screen- stores actual JFrame
     */
    private int gameTime;
    private JFrame Screen;

    /**
     * Constructor which sets actual Screen JFrame
     */
    public GetGameTime()
    {
        this.Screen= Game.getScreen();
    }

    /**
     * Method which is definition of function from interface Runnable.
     * Thread will run all code, which we can find in run method.
     * This run will make gameTime actual.
     */
    public void run()
    {

        try
        {
            gameTime=Board.getGameTime();
            gameTime+=1;
            Board.setGameTime(gameTime);
            new MonsterWaveController();

            Screen.repaint();

            if(Board.informationPanelType==1)
            {
                Thread.sleep(1000000000);
            }
            else {
                Thread.sleep(1);
            }


        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }

    }



}
