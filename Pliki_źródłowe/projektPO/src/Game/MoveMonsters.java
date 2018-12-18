package Game;

import Main.Game;

import javax.swing.*;
import java.util.ConcurrentModificationException;

public class  MoveMonsters implements Runnable {

    /**
     * Screen- actual JFrame frame
     * arrayOfRoads- array which contains points(X and Y), points which will be reached by monsters
     * actualCross- which cross has been passed by a monster
     * whichSide- number of lane he will be walking on.
     * ycord- monster is going in y axis (0 false/1 true)
     * xcord- monster is going in x axis (0 false/1 true)
     * walkDirection- [0]==1 monster is going in x axis [1]==1 monster is going in y axis
     * x/yMonsterEndMap- end point of monster image
     */
    private JFrame Screen;

    private int[][][] arrayOfRoads= new int[5][7][2];
    private int currentSection;
    private int actualCross;
    private int whichSide;
    private int ycord;
    private int xcord;
    private int[] walkDirection=new int[2];

    private int xMonsterEndMap,yMonsterEndMap;

    /**
     * Constructor which will get actual JFrame Screen
     */
    public MoveMonsters()
    {
        this.Screen= Game.getScreen();
    }

    /**
     * Method which is definition of function from interface Runnable.
     * Thread will run all code, which we can find in run method.
     * This method will try to move all monsters, by setting their cords to new values,
     * referring to monster walkDistanceX/Y.
     */
    @Override
    public void run() {

        try {
            int i=Board.listOfMonsters.size();
            for(int n=0;n<i;n++)
            {
                Monster temp=Board.listOfMonsters.get(n);
                if(temp.isTargetable()==true) {

                    arrayOfRoads = temp.getArrayOfRoads();

                    actualCross = temp.getActualCross();
                    currentSection = temp.getCurrentSection();
                    whichSide = temp.getWhichSide();
                    walkDirection = temp.getWalkDirection();

                    xMonsterEndMap = temp.getxEndMap();
                    yMonsterEndMap = temp.getyEndMap();
                    ycord = temp.getYcord();
                    xcord = temp.getXcord();

                    //Change only local whichSide for easier object positioning
                    if (whichSide == 0) {
                        whichSide = -1;
                    } else if (whichSide == 1) {
                        whichSide = 0;
                    } else if (whichSide == 2) {
                        whichSide = 1;
                    }

                    if ((((arrayOfRoads[actualCross][currentSection][0]) - (whichSide * 4)) <= xMonsterEndMap) && xcord == 1) {
                        changeMonsterDirection(temp, 0);
                        checkCurrentSection(temp);

                    } else if ((((arrayOfRoads[actualCross][currentSection][0]) + (whichSide * 4)) >= xMonsterEndMap) && xcord == -1) {
                        changeMonsterDirection(temp, 0);
                        checkCurrentSection(temp);

                    } else if ((((arrayOfRoads[actualCross][currentSection][1]) + (whichSide * 4)) <= yMonsterEndMap) && ycord == -1) {
                        changeMonsterDirection(temp, 1);
                        checkCurrentSection(temp);

                    } else if ((((arrayOfRoads[actualCross][currentSection][1]) - (whichSide * 4)) >= yMonsterEndMap) && ycord == 1) {
                        changeMonsterDirection(temp, 1);
                        checkCurrentSection(temp);

                    } else {
                        if (xcord == 1) {
                            temp.setxBeginMap(temp.getxBeginMap() + temp.getMonstersWalkDistanceX());
                            temp.setxEndMap(temp.getxEndMap() + temp.getMonstersWalkDistanceX());
                        } else if (xcord == -1) {
                            temp.setxBeginMap(temp.getxBeginMap() - temp.getMonstersWalkDistanceX());
                            temp.setxEndMap(temp.getxEndMap() - temp.getMonstersWalkDistanceX());
                        } else if (ycord == -1) {
                            temp.setyBeginMap(temp.getyBeginMap() + temp.getMonstersWalkDistanceY());
                            temp.setyEndMap(temp.getyEndMap() + temp.getMonstersWalkDistanceY());
                        } else if (ycord == 1) {
                            temp.setyBeginMap(temp.getyBeginMap() - temp.getMonstersWalkDistanceY());
                            temp.setyEndMap(temp.getyEndMap() - temp.getMonstersWalkDistanceY());
                        }

                        //Which objects should be animated
                        if (temp.getMonsterType() == 0 || temp.getMonsterType() == 2 || temp.getMonsterType() == 3 || temp.getMonsterType() == 4) {
                            new AnimatorOfMonsterWalking(temp);
                        }
                        Screen.repaint();
                    }

                }
            }
            if(Board.informationPanelType==1)
            {
                Thread.sleep(1000000000);
            }
            else {
                Thread.sleep(80);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        catch(ConcurrentModificationException e)
        {
            System.out.println(e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    /**
     * Method will change Monster direction. It always can change only by 90 degree.
     * @param temp actual Monster Object selected from listOfMonsters
     * @param fromDirection from which direction came X=0 Y=1
     */
    private void changeMonsterDirection(Monster temp,int fromDirection)
    {
        if(fromDirection==0)
        {
            walkDirection[0] = 0;
            walkDirection[1] = 1;
            temp.setXcord(0);
        }
        else if(fromDirection==1)
        {
            walkDirection[0]=1;
            walkDirection[1]=0;
            temp.setYcord(0);
        }
        temp.setWalkDirection(walkDirection);

    }

    /**
     *  Check if crosses and next road, sets proper ycords
     * @param temp actual Monster Object selected from listOfMonsters
     */
    private void checkCurrentSection(Monster temp)
    {
        int xBeforeChange=arrayOfRoads[actualCross][currentSection][0];
        int xAfterChange;

        int yBeforeChange=arrayOfRoads[actualCross][currentSection][1];
        int yAfterChange;

        //if is just last index's of GameRoad
        if(currentSection==0&&actualCross==0
                ||currentSection==2 &&actualCross==1
                ||currentSection==6 &&actualCross==2
                ||currentSection==4&&actualCross==3
                ||currentSection==6&&actualCross==4)
        {
            temp.setCurrentSection(0);
            currentSection=temp.getCurrentSection();

            if(actualCross==0)
            {
                temp.setActualCross(temp.getFirstCross());
                actualCross=temp.getActualCross();

            }
            else if(actualCross==1||actualCross==2)
            {
                temp.setActualCross(temp.getSecondCross());
                actualCross=temp.getActualCross();


            }
            else
            {
                temp.setActualCross(0);
                actualCross=temp.getActualCross();
                Board.informationPanelType=1;

            }
            xAfterChange=arrayOfRoads[actualCross][currentSection][0];
            yAfterChange=arrayOfRoads[actualCross][currentSection][1];
        }
        else
        {
            currentSection++;
            temp.setCurrentSection(currentSection);

            xAfterChange=arrayOfRoads[actualCross][currentSection][0];
            yAfterChange=arrayOfRoads[actualCross][currentSection][1];
        }

        if((yBeforeChange-yAfterChange)>0)
        {
            temp.setYcord(1);//Monster will go TOP
            //Texture/0-Golem/0-Dead 1-Right 2-Left 3-Top 4-Bottom
            new TextureChanger(temp,3);
        }
        else if((yBeforeChange-yAfterChange)<0)
        {
            temp.setYcord(-1);//Monster will go BOTTOM
            new TextureChanger(temp,4);
        }
        else
        {
            temp.setYcord(0);
        }


        if((xBeforeChange-xAfterChange)>0)
        {
            temp.setXcord(-1);//Monster will go LEFT
            new TextureChanger(temp,2);
        }
        else if((xBeforeChange-xAfterChange)<0)
        {
            temp.setXcord(1);//Monster will go RIGHT
            new TextureChanger(temp,1);
        }
        else
        {
            temp.setXcord(0);
        }


    }


}
