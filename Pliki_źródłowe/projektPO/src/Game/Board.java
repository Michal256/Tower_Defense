package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.lang.Math.abs;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Board extends JPanel{

    /**
     * BufferedImage- stores Image data buffer
     * wWidth- stores JFrame width
     * hHeight- stores JFrame height
     * xScale, yScale- stores proper scale value which are used to scale objects
     * gameTime- stores total number of seconds already played
     * gameMinuteDisplay- stores number of minutes played
     * gameSecondsDisplay- stores number of seconds played, calculated with minutes
     */
    private BufferedImage MainTextures,NorthPanelTexture,UpgradeTexture,InformationPanelTexture,GateTexture;
    private BufferedImage[] texturesToBasicDraw=new BufferedImage[6];
    private int wWidth,wHeight;
    private double xScale,yScale;


    static private int gameTime;
    private int gameMinuteDisplay;
    private int gameSecondsDisplay;


    static public GameResources gameResources;
    static public int actualWaveNumber=0;
    static public boolean GameIsWon=false;


    static public ArrayList<TowerArea> listOfTowerAreas= new ArrayList<>();
    static public ArrayList<Tower> listOfTowers= new ArrayList<>();
    //0 Red/ /1 Green //2 Blue - which Tower
    //0 none //1 Red //2 Green //3 Blue - in tower Array

    static public short[] towerAreaSelected =new short[26];

    //It's array of tower id, connected with tower area
    static public int[] storageOfTowerIdForTowerArea=new int[26];

    //TowersCreated works like that: it stores towerArea number and use it as an index, to provide information that there is a tower.
    //So if TowersCreated[21]==1 it means that on 21 towerArea is turret
    //listOfTowerArea[index]=towersCreated[index]
    static public short[] towersCreated =new short[26];

    //StableBuild points to turrets created already and which don't need to be created again
    static public double[] towersStableBuild=new double[26];

    //Allows me to get proper color for menu
    static public boolean towerIsChosen=false;


    static public ArrayList<StringPainter> listOfStrings=new ArrayList<>();

    static public ArrayList<TowerButtons> listOfTowerButtons=new ArrayList<>();
    static public ArrayList<Tower> listOfTowersOnTopMenu=new ArrayList<>();
    static public boolean changeTopInfoForTowers=false;

    static public ArrayList<Monster> listOfMonsters=new ArrayList<>();
    static public MonsterWaves monsterWaves;

    static public Rocket[] listOfRockets=new Rocket[5000];
    static public int rocketCounter=0;

    //0 Is Welcome message/1 is End message/2 Game start/3 Before start of game/4 Hidden
    public static short informationPanelType;


    ListenForMouse lForMouse=new ListenForMouse();

    /**
     * Board Constructor. It runs only once to create whole board.
     */
    public Board()
    {

        addMouseListener(lForMouse);
        initBoard();

    }

    /**
     * Method responds for loading whole game. Creates some necessary objects.
     * It also make sure, that array's are correct state.
     * Also set's variable informationPanelType on proper state to init starting message.
     */
    private void initBoard()
    {
        monsterWaves=new MonsterWaves();
        gameResources=new GameResources(1200);

        loadImages();
        texturesToBasicDraw[0]=MainTextures;
        texturesToBasicDraw[1]=NorthPanelTexture;
        texturesToBasicDraw[2]=UpgradeTexture;
        texturesToBasicDraw[3]=GateTexture;

        //Making sure that arrays are prepared.
        for(int i=0;i<towerAreaSelected.length;i++)
        {
            towerAreaSelected[i]=0;
            towersCreated[i]=0;
            towersStableBuild[i]=0;
            storageOfTowerIdForTowerArea[i]=-1;
        }
        //To init starting message.
        informationPanelType=0;

    }

    /**
     * Method loads all necessary sprites
     */
    private void loadImages()
    {
        this.MainTextures=loadImage("img/base/image.png");
        this.NorthPanelTexture=loadImage("img/background/northPanel.png");
        this.InformationPanelTexture=loadImage("img/background/bg2.png");
        this.UpgradeTexture=loadImage("img/towers/upgrade.png");
        this.GateTexture=loadImage("img/gate/gate.png");
    }

    /**
     * Method which is responsible for file management and loading graphics from outside file.
     * @param nameOfFile Name of file from which graphics will be loaded.
     * @return Returns buffer with image data.
     */
    private BufferedImage loadImage(String nameOfFile)
    {
        BufferedImage img;
        try{
            img=ImageIO.read(new File(""+nameOfFile));
            return img;
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return null;
    }


    /**
     * Method is responsible for drawing all screen content.
     * It's called each time, whenever changes on board are made.
     * Method also creates some objects.
     * For example: monster move, screen resize or repaint method.
     *
     * @param g Actual graphic of JFrame frame.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D graphicSettings = (Graphics2D)g;
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.wWidth=getWidth();
        this.wHeight=getHeight();
        getScaleValueForScreen();

        addGameText(g);
        createTowers();

        //Drawing: Grass, Road, NorthPanel, drawPanelBoxes, NorthPanelText, Detals, SelectedMonsterInfo
        new DrawGameBasicElements(g,texturesToBasicDraw,xScale,yScale);

        //Drawing other elements:
        drawTowers(g);
        drawTurretClickedHighlight(g);
        drawMonsters(g);
        drawRockets(g);
        drawInformationPanel(g);


        turretCheckForEnemy();

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

    /**
     * Method add objects to listOfStrings.
     * @param g Actual JFrame Graphics
     */
    private void addGameText(Graphics g)
    {
        //Information needed to display correct data. Data 0*.
        int displayNextWave=actualWaveNumber+1;
        int displayTimeToNextWave=20-MonsterWaveController.timeCounterToNextWave;

        listOfStrings.clear();
        //Order CAN'T be changed

        //North Panel
        listOfStrings.add(new StringPainter("Fala: "+actualWaveNumber,scaleValueForScreen(472,0),scaleValueForScreen(34,1)));
        listOfStrings.add(new StringPainter("Zasób: "+gameResources.getGameResources(), scaleValueForScreen(348,0),scaleValueForScreen(63,1)));
        listOfStrings.add(new StringPainter("Czas gry: ", scaleValueForScreen(727,0),scaleValueForScreen(29,1)));
        listOfStrings.add(new StringPainter(getGameDisplayTime()+"", scaleValueForScreen(727,0),scaleValueForScreen(58,1)));
        listOfStrings.add(new StringPainter("Wyjdź z gry", scaleValueForScreen(860,0),scaleValueForScreen(46,1)));
        //Information "on click Tower"
        listOfStrings.add(new StringPainter("NULL",scaleValueForScreen(30,0),scaleValueForScreen(34,1)));

        //InfoPanel
        listOfStrings.add(new StringPainter("Witaj!",scaleValueForScreen(235,0),scaleValueForScreen(200,1)));
        listOfStrings.add(new StringPainter("Cel: Twoim celem jest zwalczenie 10 fal wrogów.",scaleValueForScreen(235,0),scaleValueForScreen(230,1)));
        listOfStrings.add(new StringPainter("Po każdej fali następuje 20 sekndowa przerwa.",scaleValueForScreen(235,0),scaleValueForScreen(280,1)));
        listOfStrings.add(new StringPainter("Koszty: Wieża 250 zasobów | Ulepszenie 350*(poziom wieży).",scaleValueForScreen(235,0),scaleValueForScreen(305,1)));
        //Here are two others(below 1*)

        listOfStrings.add(new StringPainter("Na start otrzymujesz 1200 zasobów. Wykorzystaj je mądrze.",scaleValueForScreen(235,0),scaleValueForScreen(380,1)));
        listOfStrings.add(new StringPainter("Przetrwaj wszystkie fale, aby wygrać!",scaleValueForScreen(235,0),scaleValueForScreen(405,1)));
        listOfStrings.add(new StringPainter("Gdy będziesz gotowy, naciśnij Start w lewym górnym rogu ekranu.",scaleValueForScreen(235,0),scaleValueForScreen(430,1)));
        listOfStrings.add(new StringPainter("OK",scaleValueForScreen(483,0)+scaleValueForScreen(5,1)*((int)Math.ceil(xScale)-1),scaleValueForScreen(505,1)));

        listOfStrings.add(new StringPainter("Start",scaleValueForScreen(53,0)-scaleValueForScreen(10,1)*((int)Math.ceil(xScale)-1),scaleValueForScreen(46,1)));

        //Data 0*
        listOfStrings.add(new StringPainter("Fala "+displayNextWave+" za: "+displayTimeToNextWave+"s",scaleValueForScreen(432,0)+scaleValueForScreen(14,0)*((int)Math.ceil(xScale)-1),scaleValueForScreen(34,1)));


        listOfStrings.add(new StringPainter("Obrażenia:",scaleValueForScreen(170,0),scaleValueForScreen(28,1)));
        listOfStrings.add(new StringPainter("Szybkość:",scaleValueForScreen(170,0),scaleValueForScreen(48,1)));
        listOfStrings.add(new StringPainter("Zasięg:",scaleValueForScreen(170,0),scaleValueForScreen(68,1)));
        listOfStrings.add(new StringPainter("Przeciwko:",scaleValueForScreen(30,0),scaleValueForScreen(68,1)));


        //1*
        listOfStrings.add(new StringPainter("Wieże z celem preferowanym zadają 50% obrażeń więcej.",scaleValueForScreen(235,0),scaleValueForScreen(330,1)));
        listOfStrings.add(new StringPainter("Preferencje: Gb(Goblin), Gr(Grue), Gol(Golem), YS-BS-RS(Pająki).",scaleValueForScreen(235,0),scaleValueForScreen(355,1)));



        //InfoPanel
        if(GameIsWon==true) {
            listOfStrings.add(new StringPainter("Wygrana! Czas gry: "+getGameDisplayTime(), scaleValueForScreen(235, 0), scaleValueForScreen(200, 1)));
            listOfStrings.add(new StringPainter("Gratulacje! Udało Ci się pokonać wszystkich wrogów!", scaleValueForScreen(235, 0), scaleValueForScreen(280, 1)));
            listOfStrings.add(new StringPainter("Kliknij OK, aby wyjść z gry.", scaleValueForScreen(235, 0), scaleValueForScreen(305, 1)));

        }
        else
        {
            listOfStrings.add(new StringPainter("Przegrana!", scaleValueForScreen(235, 0), scaleValueForScreen(200, 1)));
            listOfStrings.add(new StringPainter("Niestety nie udało Ci się wygrać. Może innym razem?", scaleValueForScreen(235, 0), scaleValueForScreen(280, 1)));
            listOfStrings.add(new StringPainter("Kliknij OK, aby wyjść z gry.", scaleValueForScreen(235, 0), scaleValueForScreen(305, 1)));

        }
    }

    /**
     * Method which calculate seconds on more common format and pack it to one String.
     * @return String with calculated min/sec of actual time.
     */
    private String getGameDisplayTime()
    {
        String gameDisplayTime="";
        gameMinuteDisplay=gameTime/60;
        int gameMinuteDisplayInSeconds=gameMinuteDisplay*60;
        gameSecondsDisplay=gameTime-gameMinuteDisplayInSeconds;

        if(gameMinuteDisplay<10)
        {
            gameDisplayTime+="0";
        }

        gameDisplayTime+=gameMinuteDisplay+":";

        if(gameSecondsDisplay<10)
        {
            gameDisplayTime+="0";
        }

        gameDisplayTime+=gameSecondsDisplay;
        return gameDisplayTime;
    }

    /**
     * Creates Tower Objects, depending on their type.
     */
    private void createTowers()
    {
        int indexInTowerArray=0;
        for(short tower:towersCreated) {
            //0 None //1 Red //2 Green //3 Blue
            if (towersStableBuild[indexInTowerArray] != 1)
            {
                if (tower == 1) {

                    TowerArea properAreaForTower = listOfTowerAreas.get(indexInTowerArray);
                    listOfTowers.add(new TowerRed(64, MainTextures,false));
                    storageOfTowerIdForTowerArea[listOfTowerAreas.indexOf(properAreaForTower)]=listOfTowers.size()-1;
                    towersStableBuild[indexInTowerArray] = 1;
                } else if (tower == 2) {
                    TowerArea properAreaForTower = listOfTowerAreas.get(indexInTowerArray);
                    listOfTowers.add(new TowerGreen(64, MainTextures,false));
                    storageOfTowerIdForTowerArea[listOfTowerAreas.indexOf(properAreaForTower)]=listOfTowers.size()-1;
                    towersStableBuild[indexInTowerArray] = 1;

                } else if (tower == 3) {
                    TowerArea properAreaForTower = listOfTowerAreas.get(indexInTowerArray);
                    listOfTowers.add(new TowerBlue(64, MainTextures,false));
                    storageOfTowerIdForTowerArea[listOfTowerAreas.indexOf(properAreaForTower)]=listOfTowers.size()-1;
                    towersStableBuild[indexInTowerArray] = 1;
                }
            }
            indexInTowerArray++;
        }
    }

    /**
     * Method is responsible for drawing all turrets on Screen. Depends on listOfTowers
     * @param g Actual JFrame Graphics
     */
    private void drawTowers(Graphics g)
    {
        int indexInTowerArray=0;
            for(short tower:towersCreated)
            {
                if(tower==1||tower==2||tower==3)
                {

                        TowerArea properAreaForTower = listOfTowerAreas.get(indexInTowerArray);

                        Tower temp=Board.listOfTowers.get(storageOfTowerIdForTowerArea[indexInTowerArray]);

                        setProperCordsForTowerTextureAccordingToLevel(temp,tower);

                        temp.paintComponent(g, properAreaForTower.getxBeginMap() - scaleValueForScreen(4, 0),
                                properAreaForTower.getyBeginMap() - scaleValueForScreen(16, 1),
                                properAreaForTower.getxEndMap() - scaleValueForScreen(4, 0),
                                properAreaForTower.getyEndMap() - scaleValueForScreen(16, 1),
                                temp.getxBeginSrc(), temp.getyBeginSrc(), temp.getxEndSrc(), temp.getyEndSrc());

                }
            indexInTowerArray++;

        }
    }

    /**
     * Allows me to set proper cord texture to object depends on level.
     * @param temp Temporary object of TowerList
     * @param tower Type of tower 1-Red 2-Green 3-Blue
     */
    private void setProperCordsForTowerTextureAccordingToLevel(Tower temp,int tower)
    {
        if(temp.getLevel()==2)
        {
            if (tower == 1) {
                temp.setxBeginSrc(64);
                temp.setyBeginSrc(128);
                temp.setxEndSrc(128);
                temp.setyEndSrc(192);
            } else if (tower == 2) {
                temp.setxBeginSrc(64);
                temp.setyBeginSrc(64);
                temp.setxEndSrc(128);
                temp.setyEndSrc(128);
            } else if (tower == 3) {
                temp.setxBeginSrc(64);
                temp.setyBeginSrc(0);
                temp.setxEndSrc(128);
                temp.setyEndSrc(64);
            }
        }
        else if(temp.getLevel()==3)
        {
            if (tower == 1) {
                temp.setxBeginSrc(128);
                temp.setyBeginSrc(128);
                temp.setxEndSrc(192);
                temp.setyEndSrc(192);
            } else if (tower == 2) {
                temp.setxBeginSrc(128);
                temp.setyBeginSrc(64);
                temp.setxEndSrc(192);
                temp.setyEndSrc(128);
            } else if (tower == 3) {
                temp.setxBeginSrc(128);
                temp.setyBeginSrc(0);
                temp.setxEndSrc(192);
                temp.setyEndSrc(64);
            }
        }

    }


    /**
     * Method which is responsible for drawing highlight, if tower is selected, by a player.
     * @param g Actual JFrame Graphics
     */
    private void drawTurretClickedHighlight(Graphics g)
    {
        int cout=0;
        for(TowerArea tempArea:listOfTowerAreas)
        {
            if (towerAreaSelected[cout] == 1)
            {
                int range = 0;

                if (storageOfTowerIdForTowerArea[cout]!=-1) {
                    Tower actualTower = listOfTowers.get(storageOfTowerIdForTowerArea[cout]);
                    range = actualTower.getRange();
                }
                Color greenLight=new Color(47,255,0,70);
                g.setColor(greenLight);
                if(tempArea.getyBeginMap()-scaleValueForScreen(range,1)>=scaleValueForScreen(81,1)) {
                    g.fillRect(tempArea.getxBeginMap() - scaleValueForScreen(range, 0),
                            tempArea.getyBeginMap() - scaleValueForScreen(range, 1),
                            abs(tempArea.getxEndMap() - tempArea.getxBeginMap()) + 2 * scaleValueForScreen(range, 0),
                            abs(tempArea.getyEndMap() - tempArea.getyBeginMap()) + 2 * scaleValueForScreen(range, 1));
                }
                else
                {
                    int diffrenceOfRangeOut=scaleValueForScreen(81,1);
                    g.fillRect(tempArea.getxBeginMap() - scaleValueForScreen(range, 0),
                            diffrenceOfRangeOut,
                            abs(tempArea.getxEndMap() - tempArea.getxBeginMap()) + 2 * scaleValueForScreen(range, 0),
                            abs(tempArea.getyEndMap() - tempArea.getyBeginMap()) + 2 * scaleValueForScreen(range, 1));
                }
            }
            cout++;
        }

    }

    /**
     * Method responsible for drawing Monster objects on Screen. Depends on listOfMonsters.
     * @param g Actual JFrame Graphics
     */
    private void drawMonsters(Graphics g)
    {
        int i=listOfMonsters.size();
        for(int n=0;n<i;n++)
        {
        Monster temp=listOfMonsters.get(n);
            if(temp.isVisible()==true) {
                temp.paintComponent(g,
                        scaleValueForScreen(temp.getxBeginMap() + ((-1) * temp.getMonsterSize()), 0),
                        scaleValueForScreen(temp.getyBeginMap() + ((-1) * temp.getMonsterSize()), 1),
                        scaleValueForScreen(temp.getxEndMap() + ((1) * temp.getMonsterSize()), 0),
                        scaleValueForScreen(temp.getyEndMap() + ((1) * temp.getMonsterSize()), 1),
                        temp.getxBeginSrc(), temp.getyBeginSrc(), temp.getxEndSrc(), temp.getyEndSrc());
            }

        }
    }

    /**
     * Method responsible for drawing rocket objects on Screen. Depends on listOfRockets.
     * @param g Actual JFrame Graphics
     */
    public void drawRockets(Graphics g)
    {

                for(int n=0;n<Board.getRocketCounter();n++)
                {
                    Rocket butterFly=Board.listOfRockets[n];
                    if(butterFly.isRocketVisible()==true) {

                        int sizeOfButterFlyX = scaleValueForScreen(15, 0);
                        int sizeOfButterFlyY = scaleValueForScreen(15, 1);

                        butterFly.getStartCords();
                        butterFly.setxBeginMap(butterFly.getxBeginMap() + scaleValueForScreen(butterFly.getxDistancePast(), 0));
                        butterFly.setxEndMap(butterFly.getxEndMap() + scaleValueForScreen(butterFly.getxDistancePast(), 0));
                        butterFly.setyBeginMap(butterFly.getyBeginMap() + scaleValueForScreen(butterFly.getyDistancePast(), 1));
                        butterFly.setyEndMap(butterFly.getyEndMap() + scaleValueForScreen(butterFly.getyDistancePast(), 1));

                        butterFly.paintComponent(g,
                                butterFly.getxBeginMap(),
                                butterFly.getyBeginMap(),
                                butterFly.getxEndMap() + sizeOfButterFlyX,
                                butterFly.getyEndMap() + sizeOfButterFlyY,
                                butterFly.getxBeginSrc(), butterFly.getyBeginSrc(), butterFly.getxEndSrc(), butterFly.getyEndSrc());
                    }
            }


    }


    /**
     * Method checks, if there is enemy in tower range.
     * Checks every tower every time, whenever Screen changes.
     */
    private void turretCheckForEnemy()
    {
        for(TowerArea towerArea: listOfTowerAreas)
        {
            //So if TowersCreated[21]==1 it means that on 21 towerArea is turret

            if(storageOfTowerIdForTowerArea[listOfTowerAreas.indexOf(towerArea)]!=-1)
            {
                Tower towerAtArea = listOfTowers.get(storageOfTowerIdForTowerArea[listOfTowerAreas.indexOf(towerArea)]);

                int rangeX = scaleValueForScreen(towerAtArea.getRange(), 0) + scaleValueForScreen(40, 0);
                int rangeY = scaleValueForScreen(towerAtArea.getRange(), 1) + scaleValueForScreen(40, 1);

                for (Monster temp : listOfMonsters) {
                    if(temp.isTargetable()==true) {

                        //temp getxBeginMap returns not scaled values!
                        //towerArea returns scaled values!

                        //Add 20px for better detection

                        if ((scaleValueForScreen(temp.getxBeginMap(), 0) >= (towerArea.getxBeginMap() - rangeX)
                                && scaleValueForScreen(temp.getxEndMap(), 0) <= (towerArea.getxEndMap() + rangeX)
                                && scaleValueForScreen(temp.getyBeginMap(), 1) >= (towerArea.getyBeginMap() - rangeY)
                                && scaleValueForScreen(temp.getyEndMap(), 1) <= (towerArea.getyEndMap() + rangeY))) {
                            if (towerAtArea.getTargetedMonster() == -1) {
                                towerAtArea.setTargetedMonster(listOfMonsters.indexOf(temp));
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * Methods is responsible for drawing basic informations for the player.
     * Includes: Welcome Message, Win, Lose.
     * @param g Actual JFrame Graphics
     */
    private void drawInformationPanel(Graphics g)
    {

        //Middle box with information at start and end of game.
        if(informationPanelType==0||informationPanelType==1) {
            g.drawImage(InformationPanelTexture,
                    scaleValueForScreen(208, 0),
                    scaleValueForScreen(160, 1),
                    scaleValueForScreen(808, 0),
                    scaleValueForScreen(560, 1),
                    0, 0, 400, 400, null);
        }

        //Game start message
        if(informationPanelType==0) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(19, 1)));
                listOfStrings.get(6).paintComponent(g);
                g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(13, 1)));
                listOfStrings.get(7).paintComponent(g);
                listOfStrings.get(8).paintComponent(g);
                listOfStrings.get(9).paintComponent(g);
                listOfStrings.get(10).paintComponent(g);
                listOfStrings.get(11).paintComponent(g);
                listOfStrings.get(12).paintComponent(g);
                listOfStrings.get(20).paintComponent(g);
                listOfStrings.get(21).paintComponent(g);

                g.setColor(new Color(50, 180, 200, 220));
                g.fillRect(scaleValueForScreen(445, 0), scaleValueForScreen(465, 1), scaleValueForScreen(110, 0), scaleValueForScreen(65, 1));

                g.setColor(new Color(50, 180, 200, 220));
                g.fillRect(scaleValueForScreen(445, 0), scaleValueForScreen(465, 1), scaleValueForScreen(110, 0), scaleValueForScreen(65, 1));

                g.setColor(Color.WHITE);
                g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(25, 1)));
                listOfStrings.get(13).paintComponent(g);
        }
        //Game end
        else if(informationPanelType==1)
        {
            g.setColor(Color.blue);
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(19, 1)));
            listOfStrings.get(22).paintComponent(g);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(13, 1)));
            listOfStrings.get(23).paintComponent(g);
            listOfStrings.get(24).paintComponent(g);

            g.setColor(new Color(50, 180, 200, 220));
            g.fillRect(scaleValueForScreen(445, 0), scaleValueForScreen(465, 1), scaleValueForScreen(110, 0), scaleValueForScreen(65, 1));

            g.setColor(Color.WHITE);
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(25, 1)));
            listOfStrings.get(13).paintComponent(g);
        }
        //Button Start
        else if(informationPanelType==3&&towerIsChosen==false)
        {

            g.setColor(new Color(100,140,100));
            g.fillRect(scaleValueForScreen(20,0),scaleValueForScreen(10,1),
                    scaleValueForScreen(130,1),scaleValueForScreen(61,1));
            g.setColor(Color.WHITE);
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(20, 1)));
            listOfStrings.get(14).paintComponent(g);

        }

        else if(informationPanelType==2)
        {
            gameTime=0;
            addThreadsToPool();
            informationPanelType=4;
        }


    }

    /**
     * Method which is controler for mouse events. It implements interface-MouseListener.
     */
    private class ListenForMouse implements MouseListener
    {

        /**
         * Mouse event handler. If Mouse is clicked, method will be called.
         * @param e Event call, which provides information for ex. where it was clicked.
         */
        @Override
        public void mouseClicked(MouseEvent e)
        {
            //Before it's necessary to check if any other area is clicked...
            boolean freetowerAreaIsChosen=false;
            for(int x=0;x<26;x++)
            {
                if(towerAreaSelected[x]==1&&towersCreated[x]==0)
                {
                    freetowerAreaIsChosen=true;
                    break;
                }
            }

            if(Board.towerIsChosen==true&&freetowerAreaIsChosen==false)
            {
                listOfTowerButtons.get(3).checkClickedArea(e.getX(),e.getY(),gameResources,false);
            }

            //Later go on...
            if(freetowerAreaIsChosen==true)
            {
                for (TowerButtons temp : listOfTowerButtons) {
                    temp.checkClickedArea(e.getX(), e.getY(), gameResources,true);

                }
            }
            else if(Board.towerIsChosen==false&&informationPanelType==3)
            {
                if(e.getX()>=scaleValueForScreen(20,0)&&e.getX()<=scaleValueForScreen(130,0)&&e.getY()>=scaleValueForScreen(10,1)&&e.getY()<=scaleValueForScreen(61,1)&&changeTopInfoForTowers==false)
                {
                    informationPanelType=2;
                }
            }

            short i=0;

            for(TowerArea temp: listOfTowerAreas)
            {
                temp.checkClickedArea(e.getX(),e.getY(),i);
                i++;
            }


            for(Monster temp: listOfMonsters)
            {
                if(temp.isVisible()==true) {
                    if (xScale == 0 || yScale == 0) {
                        temp.checkClickedArea(e.getX(), e.getY());
                    } else if (xScale == 0 && yScale != 0) {
                        temp.checkClickedArea(e.getX(), (int) ((double) e.getY() / yScale));
                    } else if (xScale != 0 && yScale == 0) {
                        temp.checkClickedArea((int) ((double) e.getX() / xScale), e.getY());
                    } else {
                        temp.checkClickedArea((int) ((double) e.getX() / xScale), (int) ((double) e.getY() / yScale));
                    }
                }
            }


            if(informationPanelType==0&&e.getX()>=scaleValueForScreen(445,0)&&e.getX()<=scaleValueForScreen(555,0)&&e.getY()>=scaleValueForScreen(465,1)&&e.getY()<=scaleValueForScreen(530,1))
            {
                informationPanelType=3;

            }
            if(informationPanelType==1&&e.getX()>=scaleValueForScreen(445,0)&&e.getX()<=scaleValueForScreen(555,0)&&e.getY()>=scaleValueForScreen(465,1)&&e.getY()<=scaleValueForScreen(530,1))
            {
                System.exit(0);
            }




            if(e.getX()>=scaleValueForScreen(854,0)&&e.getX()<=(scaleValueForScreen(854,0)+scaleValueForScreen(120,0))
                    &&e.getY()>=scaleValueForScreen(10,1)&&e.getY()<=(scaleValueForScreen(10,1)+scaleValueForScreen(61,1)))
            {
                System.exit(0);
            }
            repaint();

        }

        /**
         * Mouse event handler.
         * @param e Event call, which provides information for ex. where it was clicked.
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Mouse event handler.
         * @param e Event call, which provides information for ex. where it was clicked.
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Mouse event handler.
         * @param e Event call, which provides information for ex. where it was clicked.
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Mouse event handler.
         * @param e Event call, which provides information for ex. where it was clicked.
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


    /**
     * Getter of gameTime
     * @return Returns actual game time
     */
    public static int getGameTime() {
        return gameTime;
    }

    /**
     * Setter of gameTime
     * @param gameTime Time which will be set to game time global variable.
     */
    public static void setGameTime(int gameTime) {
        Board.gameTime = gameTime;
    }

    /**
     * Getter of rocket counter.
     * @return Returns actual number of rockets.
     */
    public static int getRocketCounter() {
        return rocketCounter;
    }

    /**
     * Setter of rocket counter
     * @param rocketCounter Number of rockets which will be set to rocket counter global variable.
     */
    public static void setRocketCounter(int rocketCounter) {
        Board.rocketCounter = rocketCounter;
    }

    /**
     * Method which is responsible for adding four threads to collection of worker threads.
     */
    public static void addThreadsToPool()
    {
       ScheduledThreadPoolExecutor eventPool=new ScheduledThreadPoolExecutor(4);
        eventPool.scheduleAtFixedRate(new MoveMonsters(),0,80,MILLISECONDS);
        eventPool.scheduleWithFixedDelay(new TowerReloadFire(),0,50,MILLISECONDS);
        eventPool.scheduleWithFixedDelay(new RocketFlyToEnemy(),0,10,MILLISECONDS);
        eventPool.scheduleAtFixedRate(new GetGameTime(),0,1,SECONDS);

    }

}
