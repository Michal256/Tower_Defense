package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class Monster extends GameRoad {

    /**
     * BufferedImage- stores Image data buffer
     * xBeginMap,yBeginMap- point, from which image will be drawn
     * xEndMap,yEndMap- point, to which image will be drawn
     * xBeginSrc,yBeginSrc- source point, from which image will be loaded
     * xEndSrc,yEndSrc- source point, to which image will be loaded
     */
    protected BufferedImage mainTexture;
    private int xBeginMap,yBeginMap,xEndMap,yEndMap;
    private int xBeginSrc,yBeginSrc,xEndSrc,yEndSrc;


    /**
     * name-Name of monster
     * monsterType- type of monster 0-Goblin 1-Grue 2-Golem 3-Bat 4-Spider
     * health- health of monster
     * max health- health with which monster has been created
     * monsterWalkDistanceX/Y- walk distance
     * resourceReward- how much resource player for a monster
     * monsterSize- how many pixels, monster should be additionaly larger
     * firstCross- in which way monster should go, at the first cross
     * secondCross- in which way monster should go, at the second cross
     * actualCross- which cross has been passed by a monster
     * currentSection- at which section of road monster is
     * respawnTime- time to count, for first spawn
     * ycord- monster is going in y axis (0 false/1 true)
     * xcord- monster is going in x axis (0 false/1 true)
     * targetable- monster is targetable by a tower
     * visible- monster is visible by a player
     * gaveResource- decide if player should get any resource for a monster
     * isSelected- specify if monster is actually selected by a player
     * texturePosition- actual position of a monster //0-Dead//1-Right//2-Left//3-Top//4-Bottom
     * actualImageFrame- actual source image loaded from sprite (if actualImageFrame=1 this will be normal texturePosition=1)
     */
    private String name;
    private int monsterType;
    private int health;
    private int maxHealth;
    private int monstersWalkDistanceX;
    private int monstersWalkDistanceY;
    private int resourceReward;
    private int monsterSize;
    private int firstCross;
    private int secondCross;
    private int actualCross=0;
    private int currentSection=0;
    private int respawnTime=0;
    private int ycord=0;
    private int xcord=1;

    private boolean targetable;
    private boolean visible;

    private boolean gaveResource=false;
    private boolean isSelected=false;

    private int texturePosition=1;
    private int actualImageFrame=1;


    //WalkDirection - [0]==1 goes x [1]==1 goes y
    protected int[] walkDirection=new int[2];

    protected int whichSide;


    /**
     * Constructor for a monster object, which will set all necessary components.
     * Constructor will also set necessary default monster cords.
     * @param name Name of monster
     * @param monsterType type of monster 0-Goblin 1-Grue 2-Golem 3-Bat 4-Spider
     * @param health Health of monster
     * @param monstersWalkDistanceX Monster walk speed X
     * @param monstersWalkDistanceY Monster walk speed Y
     * @param resourceReward Reward for a monster
     * @param monsterSize Monster additional size
     * @param whichSide Number of lane he will be walking on.
     * @param respawnTime Time that must pass, to respawn this monster.
     */
    public Monster(String name,int monsterType,int health,int monstersWalkDistanceX,int monstersWalkDistanceY,int resourceReward,int monsterSize,int whichSide,int respawnTime)
    {
        this.name=name;
        this.monsterType=monsterType;
        this.health=health;
        this.monstersWalkDistanceX=monstersWalkDistanceX;
        this.monstersWalkDistanceY=monstersWalkDistanceY;
        this.resourceReward=resourceReward;
        this.monsterSize=monsterSize;
        this.whichSide=whichSide;
        this.maxHealth=health;
        this.targetable=true;
        this.visible=true;
        this.respawnTime=respawnTime;

        setDefaultMonsterCords();

        this.firstCross=pathDrawLots(2)+1;
        this.secondCross=pathDrawLots(2)+3;

    }

    /**
     * Default constructor, which will set all components on base values.
     */
    public Monster()
    {
        this.name="Noname";
        this.monsterType=0;
        this.health=0;
        this.maxHealth=0;
        this.monstersWalkDistanceX=0;
        this.monstersWalkDistanceY=0;
        this.resourceReward=0;
        this.monsterSize=0;
        this.whichSide=0;
        this.firstCross=0;
        this.secondCross=2;
        targetable=false;
        this.visible=false;

        setDefaultMonsterCords();
    }

    /**
     * Sets proper cords for monster, when he will be created.
     * Sets proper source cords, to get proper part of external Image.
     */
    private void setDefaultMonsterCords()
    {
        this.xBeginMap=0;
        this.yBeginMap=0;
        this.xEndMap=16;
        this.yEndMap=16;

        this.xBeginSrc=0;
        this.yBeginSrc=0;
        this.xEndSrc=0;
        this.yEndSrc=0;

    }

    /**
     * Method which is responsible for randomizing monster choice on first and second cross.
     * @param bound to which number it will randomize(including 0)
     * @return returns random number from the bound
     */
    protected int pathDrawLots(int bound)
    {
        Random r= new Random();
        int num = r.nextInt(bound);
        return num;


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
     * Method which will check, if clicked cords, are the cords of monster.
     * If that's true, monster will be selected.
     * @param xMouse x axis cords of clicked point
     * @param yMouse y axis cords of clicked point
     */
    public void checkClickedArea(int xMouse,int yMouse)
    {

        isSelected=false;
        if(targetable==true&&(xMouse>=xBeginMap+15&&xMouse<=xEndMap-15
                &&yMouse>=yBeginMap+15&&yMouse<=yEndMap-15))
        {

            isSelected=true;
        }

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
        if(isSelected==true) {
            g.setColor(new Color(0,255,0,125));
            Graphics2D g2d = (Graphics2D)g;
            Ellipse2D.Double circle = new Ellipse2D.Double(xBeginMap+15,yBeginMap+35, abs(xEndMap - xBeginMap)-30, abs(yEndMap - yBeginMap)-30);
            g2d.fill(circle);
        }
        g.drawImage(mainTexture,xBeginMap,yBeginMap,xEndMap,yEndMap,
                xBeginSrc,yBeginSrc,xEndSrc,yEndSrc,null);



    }

    /**
     * Method checks if monster is alive and treats them in compliance with their status.
     */
    public void checkIfAlive()
    {
        if(this.health<=0)
        {
            targetable=false;
            monstersWalkDistanceX=0;
            monstersWalkDistanceY=0;
            this.health=0;
            new TextureChanger(this,0);
            isSelected=false;
            if(monsterType==1)
            {
                visible=false;
            }
            if(gaveResource==false) {
                int gameResource = Board.gameResources.getGameResources();
                Board.gameResources.setGameResources(gameResource + resourceReward);
                gaveResource=true;
            }
        }
    }


    public String getName() {
        return name;
    }

    public int getMonsterType() { return monsterType; }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getMaxHealth() { return maxHealth; }
    public int getMonstersWalkDistanceX() {
        return monstersWalkDistanceX;
    }

    public int getMonstersWalkDistanceY() {
        return monstersWalkDistanceY;
    }

    public int getResourceReward() {
        return resourceReward;
    }

    public int getMonsterSize() {
        return monsterSize;
    }

    public int getWhichSide() {
        return whichSide;
    }

    public int getFirstCross() {
        return firstCross;
    }

    public int getSecondCross() {
        return secondCross;
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

    public int getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(int currentSection) {
        this.currentSection = currentSection;
    }

    public int getActualCross() {
        return actualCross;
    }

    public void setActualCross(int actualCross) {
        this.actualCross = actualCross;
    }

    public int[] getWalkDirection() {
        return walkDirection;
    }

    public void setWalkDirection(int[] walkDirection) {
        this.walkDirection = walkDirection;
    }

    public int getYcord() {
        return ycord;
    }

    public void setYcord(int ycord) {
        this.ycord = ycord;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXcord(int xcord) {
        this.xcord = xcord;
    }

    public int getTexturePosition() {
        return texturePosition;
    }

    public void setTexturePosition(int texturePosition) {
        this.texturePosition = texturePosition;
    }

    public int getActualImageFrame() {
        return actualImageFrame;
    }

    public void setActualImageFrame(int actualImageFrame) {
        this.actualImageFrame = actualImageFrame;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public boolean isTargetable() {
        return targetable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }
}


