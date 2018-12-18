package Game;

import Main.Game;

import javax.swing.*;
import java.util.ArrayList;

public class RocketFlyToEnemy implements Runnable {
    /**
     * Screen- actual JFrame frame
     * wWidth- actual wWidth of frame
     * wHeight- actual wHeight of frame
     * x/yScale- actual scale based on wWidth and wHeight
     */

    private JFrame Screen;
    private int wWidth;
    private int wHeight;
    private double xScale,yScale;

    /**
     * Method which is definition of function from interface Runnable.
     * Thread will run all code, which we can find in run method.
     * This method will try to move all rockets, by setting their cords to new values,
     * referring to rocketSpeed.
     */
    @Override
    public void run()
    {


        ArrayList<Integer> listOfElementsIDToDelete=new ArrayList<>();
        this.Screen= Game.getScreen();
        this.wWidth=Screen.getContentPane().getWidth();
        this.wHeight=Screen.getContentPane().getHeight();
        getScaleValueForScreen();

        try {
            int m=Board.getRocketCounter();
            for(int n=0;n<m;n++)
            {
                Rocket butterFly=Board.listOfRockets[n];
                if (butterFly.getRocketTowerTarget() != -1)
                {
                    Monster actualMonster = Board.listOfMonsters.get(butterFly.getRocketTowerTarget());
                    //Scale for better accuracy
                    int rangeX = scaleValueForScreen(5, 0);
                    int rangeY = scaleValueForScreen(5, 1);

                    int ballRangeX = scaleValueForScreen(32, 0);
                    int ballRangeY = scaleValueForScreen(32, 1);


                    if ((butterFly.getxBeginMap()-ballRangeX >= scaleValueForScreen(actualMonster.getxBeginMap(), 0) - rangeX
                            && butterFly.getxEndMap()+ballRangeX <= scaleValueForScreen(actualMonster.getxEndMap(), 0)+ rangeX
                            && butterFly.getyBeginMap()-ballRangeY >= scaleValueForScreen(actualMonster.getyBeginMap(), 1)- rangeY
                            && butterFly.getyEndMap()+ballRangeY <= scaleValueForScreen(actualMonster.getyEndMap(), 1) + rangeY)) {

                        butterFly.rocketHitMonster(actualMonster);
                        listOfElementsIDToDelete.add(butterFly.getRocketIDInArray());
                    }
                    else
                        {

                        if (butterFly.getxBeginMap()-ballRangeX < scaleValueForScreen(actualMonster.getxBeginMap(), 0) - rangeX) {
                            butterFly.setxBeginMap(butterFly.getxBeginMap() + scaleValueForScreen(butterFly.getRocketSpeed(), 0));
                            butterFly.setxEndMap(butterFly.getxEndMap() + scaleValueForScreen(butterFly.getRocketSpeed(), 0));

                            butterFly.setxDistancePast(butterFly.getxDistancePast()+butterFly.getRocketSpeed());
                        }

                        else if (butterFly.getxBeginMap()+ballRangeX > scaleValueForScreen(actualMonster.getxBeginMap(), 0)+ rangeX) {
                            butterFly.setxBeginMap(butterFly.getxBeginMap() - scaleValueForScreen(butterFly.getRocketSpeed(), 0));
                            butterFly.setxEndMap(butterFly.getxEndMap() - scaleValueForScreen(butterFly.getRocketSpeed(), 0));

                            butterFly.setxDistancePast(butterFly.getxDistancePast()-butterFly.getRocketSpeed());
                        }

                        if (butterFly.getyBeginMap()-ballRangeY < scaleValueForScreen(actualMonster.getyBeginMap(), 1) - rangeY) {
                            butterFly.setyBeginMap(butterFly.getyBeginMap() + scaleValueForScreen(butterFly.getRocketSpeed(), 1));
                            butterFly.setyEndMap(butterFly.getyEndMap() + scaleValueForScreen(butterFly.getRocketSpeed(), 1));

                            butterFly.setyDistancePast(butterFly.getyDistancePast()+butterFly.getRocketSpeed());
                        }

                        else if (butterFly.getyBeginMap()+ballRangeY > scaleValueForScreen(actualMonster.getyBeginMap(), 1)+ rangeY) {
                            butterFly.setyBeginMap(butterFly.getyBeginMap() - scaleValueForScreen(butterFly.getRocketSpeed(), 1));
                            butterFly.setyEndMap(butterFly.getyEndMap() - scaleValueForScreen(butterFly.getRocketSpeed(), 1));

                            butterFly.setyDistancePast(butterFly.getyDistancePast()-butterFly.getRocketSpeed());
                        }

                        //Check if monster did not fall over, before Rocket got him. If died, visible=false
                        if(actualMonster.isTargetable()==false)
                        {
                                butterFly.setRocketVisible(false);
                        }
                            Screen.repaint();
                    }

                }

            }

            for(int i=0;i<listOfElementsIDToDelete.size();i++)
            {
                Board.setRocketCounter(Board.getRocketCounter()-1);
            }

           Thread.sleep(10);
        }
        catch(InterruptedException e)
        {
           System.out.println(e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }



    }


    /**
     * Sets global screen scale x and y.
     * Scale is being created, basing on the least screen resolution set.
     * Game elements are being positioned due to scale.
     */
    private void getScaleValueForScreen()
    {
        this.xScale=(this.wWidth/1006.0);
        this.yScale=(this.wHeight/721.0);


    }

    /**
     * Calculates proper pixel value for the specific object, depending on screen scale.
     * @param value Value in pixels
     * @param axis Axis 0 is X | Axis 1 is Y
     * @return Returns scaled value
     */
    private int scaleValueForScreen(int value,int axis)
    {
        if(axis==0)
        {
            value=(int)(value*xScale);
        }
        else if(axis==1)
        {
            value=(int)(value*yScale);
        }
        return value;
    }


}

