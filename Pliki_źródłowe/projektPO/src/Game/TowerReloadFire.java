package Game;

import Main.Game;

import javax.swing.*;

public class TowerReloadFire implements Runnable {
    /**
     * Screen- actual JFrame frame
     */
    private JFrame Screen;

    public TowerReloadFire()
    {
        this.Screen= Game.getScreen();
    }

    /**
     * Method which is definition of function from interface Runnable.
     * Thread will run all code, which we can find in run method.
     * Method will check for monsters in range.
     * Then if monster will be in range of tower, it will try to fire a rocket towards monster.
     */
    @Override
    public void run() {
        try
        {
            int i= Board.listOfTowers.size();
            for(int n=0;n<i;n++)
            {
                Tower actualTower= Board.listOfTowers.get(n);
                    if (actualTower.getReloadTime() != 0) {
                        actualTower.setReloadTime(actualTower.getReloadTime() - 0.050 * actualTower.getFireRatio());
                        //To be sure that it's equal 0, if it's ended.
                        if (actualTower.getReloadTime() < 0) {
                            actualTower.setReloadTime(0);
                        }
                    }

                    if (actualTower.getTargetedMonster() != -1 && actualTower.getReloadTime() == 0) {

                        Rocket butterFly;
                        if(actualTower.getTowerType()==1)
                        {
                            butterFly = new RedRocket(Board.listOfTowers.indexOf(actualTower));
                        }
                        else if(actualTower.getTowerType()==2)
                        {
                            butterFly = new GreenRocket(Board.listOfTowers.indexOf(actualTower));
                        }
                        else if(actualTower.getTowerType()==3)
                        {
                            butterFly = new BlueRocket(Board.listOfTowers.indexOf(actualTower));
                        }
                        else
                        {
                            butterFly = new RedRocket(Board.listOfTowers.indexOf(actualTower));
                        }
                        butterFly.setRocketVisible(true);
                        Board.setRocketCounter(Board.getRocketCounter() + 1);
                        butterFly.setRocketIDInArray(Board.getRocketCounter() - 1);
                        Board.listOfRockets[Board.getRocketCounter() - 1] = butterFly;
                        actualTower.setReloadTime(1);
                    }

                    actualTower.setTargetedMonster(-1);

            }

            Thread.sleep(50);
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


}
