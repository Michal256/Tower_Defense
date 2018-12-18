package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Rocket
{
    /**
     * BufferedImage- stores Image data buffer
     * xBeginMap,yBeginMap- point, from which image will be drawn
     * xEndMap,yEndMap- point, to which image will be drawn
     * xBeginSrc,yBeginSrc- source point, from which image will be loaded
     * xEndSrc,yEndSrc- source point, to which image will be loaded
     * rocketSpeed- pixels per time traveled by a rocket
     * rocketTowerId- Id of Tower from which rocket was fired
     * rocketTowerTarget- monster to which rocket has been sent
     * rocketIDInArray- id of rocket in rocket array
     * rocketVisible- rocket visibility
     */
    protected BufferedImage mainTexture;
    private int xBeginMap,yBeginMap,xEndMap,yEndMap;
    private int xBeginSrc,yBeginSrc,xEndSrc,yEndSrc;
    private int xDistancePast,yDistancePast;
    private int rocketSpeed=8;
    private int rocketTowerId;
    private int rocketTowerTarget;
    private int rocketIDInArray;
    private boolean rocketVisible=false;

    /**
     * Constructor which is responsible for creating a rocket with proper rocketTowerId,
     * starting cords and rocket target.
     * @param rocketTowerId id of tower, from which rocket has been sent.
     */
    public Rocket(int rocketTowerId)
    {
        this.rocketTowerId=rocketTowerId;
        getStartCords();
        getRocketTarget();
    }

    /**
     * Default constructor, which will set all components on base values.
     */
    public Rocket()
    {
        this.rocketTowerId=-1;
        this.xBeginMap=0;
        this.yBeginMap=0;
        this.xEndMap=0;
        this.yEndMap=0;
    }

    /**
     * Sets proper cords for monster, when he will be created.
     * Sets proper source cords, to get proper part of external Image.
     */
    public void getStartCords()
    {
       for(int i=0;i<Board.storageOfTowerIdForTowerArea.length;i++)
       {
           if(Board.storageOfTowerIdForTowerArea[i]==rocketTowerId)
           {
               TowerArea actualArea=Board.listOfTowerAreas.get(i);
               this.xBeginMap=actualArea.getxBeginMap();
               this.yBeginMap=actualArea.getyBeginMap();
               this.xEndMap=actualArea.getxBeginMap();
               this.yEndMap=actualArea.getyBeginMap();

               break;
           }
       }
    }

    /**
     * Method will assign rocket a target, basing on tower target.
     */
    private void getRocketTarget()
    {
        Tower actualTower = Board.listOfTowers.get(rocketTowerId);
        this.rocketTowerTarget=actualTower.getTargetedMonster();
    }

    /**
     * Method which is responsible for file management and loading graphics from outside file.
     * @param nameOfFile Name of file from which graphics will be loaded.
     * @return Returns buffer with image data.
     */
    protected BufferedImage loadImage(String nameOfFile)
    {
        BufferedImage img;
        try{
            img= ImageIO.read(new File(""+nameOfFile));
            return img;
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method which is responsible for setting proper health for monster after a hit.
     * Method is also responsible for disappearing rocket after a hit.
     * @param actualMonster actual monster from listOfMonsters
     */
    public void rocketHitMonster(Monster actualMonster)
    {
        Tower actualTower=Board.listOfTowers.get(rocketTowerId);
        //Check what type of tower is it
        //1 is red: Golem, Bat
        //2 is green: All spiders
        //3 is blue: Goblin

        int towerType=actualTower.getTowerType();
        int towerDamage=actualTower.getDamage();
        if(towerType==1)
        {
            if(actualMonster.getMonsterType()==2||actualMonster.getMonsterType()==3)
            {
                towerDamage+=actualTower.getDamage()*0.5;
            }
        }
        else if(towerType==2)
        {
            if(actualMonster.getMonsterType()==4)
            {
                towerDamage+=actualTower.getDamage()*0.5;
            }
        }
        if(towerType==3)
        {
            if(actualMonster.getMonsterType()==0||actualMonster.getMonsterType()==1)
            {
                towerDamage+=actualTower.getDamage()*0.5;
            }
        }

        actualMonster.setHealth(actualMonster.getHealth()-towerDamage);
        actualMonster.checkIfAlive();
        rocketVisible=false;

    }

    /**
     *  Method is responsible for drawing all screen content.
     *  It's called each time, whenever changes on board are made.
     * @param g actual JFrame graphics
     * @param xBeginMap- x point, from which image will be drawn
     * @param yBeginMap- y point, from which image will be drawn
     * @param xEndMap- x point, to which image will be drawn
     * @param yEndMap- y point, to which image will be drawn
     * @param xBeginSrc- source x point, from which image will be loaded
     * @param yBeginSrc- source y point, from which image will be loaded
     * @param xEndSrc- source x point, to which image will be loaded
     * @param yEndSrc- source y point, to which image will be loaded
     */
    public void paintComponent(Graphics g, int xBeginMap, int yBeginMap, int xEndMap, int yEndMap,
                               int xBeginSrc, int yBeginSrc, int xEndSrc, int yEndSrc)
    {
        g.drawImage(mainTexture,xBeginMap,yBeginMap,xEndMap,yEndMap,
                xBeginSrc,yBeginSrc,xEndSrc,yEndSrc,null);
    }

    public int getxBeginMap() {
        return xBeginMap;
    }

    public void setxBeginMap(int xBeginMap) {
        this.xBeginMap = xBeginMap;
    }

    public int getyBeginMap() {
        return yBeginMap;
    }

    public void setyBeginMap(int yBeginMap) {
        this.yBeginMap = yBeginMap;
    }

    public int getxEndMap() {
        return xEndMap;
    }

    public void setxEndMap(int xEndMap) {
        this.xEndMap = xEndMap;
    }

    public int getyEndMap() {
        return yEndMap;
    }

    public void setyEndMap(int yEndMap) {
        this.yEndMap = yEndMap;
    }

    public int getxBeginSrc() {
        return xBeginSrc;
    }

    public void setxBeginSrc(int xBeginSrc) {
        this.xBeginSrc = xBeginSrc;
    }

    public int getyBeginSrc() {
        return yBeginSrc;
    }

    public void setyBeginSrc(int yBeginSrc) {
        this.yBeginSrc = yBeginSrc;
    }

    public int getxEndSrc() {
        return xEndSrc;
    }

    public void setxEndSrc(int xEndSrc) {
        this.xEndSrc = xEndSrc;
    }

    public int getyEndSrc() {
        return yEndSrc;
    }

    public void setyEndSrc(int yEndSrc) {
        this.yEndSrc = yEndSrc;
    }

    public int getRocketTowerId() {
        return rocketTowerId;
    }

    public int getRocketSpeed() {
        return rocketSpeed;
    }

    public void setRocketSpeed(int rocketSpeed) {
        this.rocketSpeed = rocketSpeed;
    }

    public int getRocketTowerTarget() {
        return rocketTowerTarget;
    }

    public int getxDistancePast() {
        return xDistancePast;
    }

    public void setxDistancePast(int xDistancePast) {
        this.xDistancePast = xDistancePast;
    }

    public int getyDistancePast() {
        return yDistancePast;
    }

    public void setyDistancePast(int yDistancePast) {
        this.yDistancePast = yDistancePast;
    }

    public int getRocketIDInArray() {
        return rocketIDInArray;
    }

    public void setRocketIDInArray(int rocketIDInArray) {
        this.rocketIDInArray = rocketIDInArray;
    }

    public boolean isRocketVisible() {
        return rocketVisible;
    }

    public void setRocketVisible(boolean rocketVisible) {
        this.rocketVisible = rocketVisible;
    }
}
