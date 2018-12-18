package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tower extends JPanel {
    /**
     * towerType- the type of actual tower 1-Red 2-Green 3-Blue
     * damage- damage which will be dealt to monster when rocket will hit
     * towerRange- range of monster detection from the actual position of tower area
     * level- tower level
     * fireRatio- rockets fire ratio
     * reloadTime- time need to be passed, before next rocket will be launched
     * targetedMonster- chosen monster object, which is in the range of tower/-1 if there is no such object
     */
    private int towerType;
    private int damage;
    private int towerRange;
    private int level=1;
    private double fireRatio=-1;
    private double reloadTime=0;
    private int targetedMonster=-1;
    private int xBeginSrc,yBeginSrc,xEndSrc,yEndSrc;
    protected BufferedImage MainTextures;


    /**
     * Constructor which will set tower range of tower.
     * @param towerRange additional range from the position of tower area.
     */
    public Tower(int towerRange)
    {
        this.towerRange=towerRange;
    }

    /**
     * Constructor which will set tower range of tower and proper texture.
     * @param towerRange additional range from the position of tower area.
     * @param MainTextures stores Image data buffer
     */
    public Tower(int towerRange,BufferedImage MainTextures)
    {
        this.towerRange=towerRange;
        this.MainTextures=MainTextures;
    }

    /**
     * Default constructor, which will set all components on base values.
     */
    public Tower()
    {
        this.towerRange=0;
        this.reloadTime=0;
        this.fireRatio=-1;
        this.level=0;
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
        g.drawImage(MainTextures,xBeginMap,yBeginMap,xEndMap,yEndMap,xBeginSrc,yBeginSrc,xEndSrc,yEndSrc,null);
    }

    public int getTargetedMonster() {
        return targetedMonster;
    }

    protected void setTargetedMonster(int targetedMonster) {
        this.targetedMonster = targetedMonster;
    }

    public int getDamage() {
        return damage;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return towerRange;
    }

    public void setTowerRange(int towerRange) {
        this.towerRange = towerRange;
    }

    public double getFireRatio() {
        return fireRatio;
    }

    protected void setFireRatio(double fireRatio) {
        this.fireRatio = fireRatio;
    }

    public int getTowerType() {
        return towerType;
    }

    protected void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(double reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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


}
