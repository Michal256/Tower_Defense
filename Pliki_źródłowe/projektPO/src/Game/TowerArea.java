package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
public class TowerArea {

    /**
     * BufferedImage- stores Image data buffer
     * xBeginMap,yBeginMap- point, from which image will be drawn
     * xEndMap,yEndMap- point, to which image will be drawn
     * xBeginSrc,yBeginSrc- source point, from which image will be loaded
     * xEndSrc,yEndSrc- source point, to which image will be loaded
     */
    private BufferedImage MainTextures;
    private int xBeginMap,yBeginMap,xEndMap,yEndMap;
    private int xBeginSrc,yBeginSrc,xEndSrc,yEndSrc;


    /**
     *  Constructor which is responsible for setting all components.
     * @param MainTextures stores Image data buffer(actual Image data buffer)
     * @param xBeginMap- x point, from which image will be drawn
     * @param yBeginMap- y point, from which image will be drawn
     * @param xEndMap- x point, to which image will be drawn
     * @param yEndMap- y point, to which image will be drawn
     * @param xBeginSrc- source x point, from which image will be loaded
     * @param yBeginSrc- source y point, from which image will be loaded
     * @param xEndSrc- source x point, to which image will be loaded
     * @param yEndSrc- source y point, to which image will be loaded
     */
    public TowerArea(BufferedImage MainTextures,int xBeginMap, int yBeginMap, int xEndMap, int yEndMap, int xBeginSrc, int yBeginSrc, int xEndSrc, int yEndSrc)
    {
        this.MainTextures=MainTextures;

        this.xBeginMap=xBeginMap;
        this.yBeginMap=yBeginMap;
        this.xEndMap=xEndMap;
        this.yEndMap=yEndMap;

        this.xBeginSrc=xBeginSrc;
        this.yBeginSrc=yBeginSrc;
        this.xEndSrc=xEndSrc;
        this.yEndSrc=yEndSrc;

    }

    /**
     * Method which checks if the click action, was on the one of the tower areas.
     * If it was, it execute proper instructions, to draw proper objects,
     * so that player could see that is clicked.
     * @param xMouse x cord where click event occurred
     * @param yMouse y cord where click event occurred
     * @param whichTower for which tower from listOfTowers it should be checked
     */
    public void checkClickedArea(int xMouse, int yMouse, short whichTower)
    {
        if((this.xBeginMap<=xMouse&&xEndMap>=xMouse)
                &&(this.yBeginMap<=yMouse&&this.yEndMap>=yMouse))
        {
            if(Board.towerAreaSelected[whichTower]==1)
            {
                Board.towerAreaSelected[whichTower]=0;
                Board.changeTopInfoForTowers=false;
                Board.towerIsChosen=false;
            }
            else {
                for (int i = 0; i < Board.towerAreaSelected.length; i++)
                {
                    Board.towerAreaSelected[i] = 0;
                }
                Board.towerAreaSelected[whichTower] = 1;

            }
        }
        else
        {
            Board.towerAreaSelected[whichTower]=0;
            Board.towerIsChosen=false;
            Board.changeTopInfoForTowers=false;
        }

    }


     /**
     * Method is responsible for drawing all screen content.
     * It's called each time, whenever changes on board are made.
     * @param g actual JFrame graphics
     * @param whichTower for which tower from listOfTowers it should be checked
     */
    public void paintComponent(Graphics g,int whichTower)
    {

        g.drawImage(MainTextures, getxBeginMap(), getyBeginMap(), getxEndMap(), getyEndMap(), getxBeginSrc(), getyBeginSrc(), getxEndSrc(), getyEndSrc(), null);
        if (Board.towerAreaSelected[whichTower] == 1)
        {
            if(Board.towersCreated[whichTower]==0) {
                //When there is no tower on the area
                g.setColor(Color.red);
                Board.listOfTowerButtons.get(0).paintComponent(g);
                g.setColor(Color.green);
                Board.listOfTowerButtons.get(1).paintComponent(g);
                g.setColor(Color.blue);
                Board.listOfTowerButtons.get(2).paintComponent(g);


                Board.changeTopInfoForTowers = true;
            }
        }

    }

    public int getxBeginMap() {
        return xBeginMap;
    }

    public int getyBeginMap() {
        return yBeginMap;
    }

    public int getxEndMap() {
        return xEndMap;
    }

    public int getyEndMap() {
        return yEndMap;
    }

    public int getxBeginSrc() {
        return xBeginSrc;
    }

    public int getyBeginSrc() {
        return yBeginSrc;
    }

    public int getxEndSrc() {
        return xEndSrc;
    }

    public int getyEndSrc() {
        return yEndSrc;
    }



}
