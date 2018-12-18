package Game;

import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawGameBasicElements
{
    /**
     * Screen- JFrame frame
     * wWidth- stores JFrame width
     * hHeight- stores JFrame height
     * MainTextures, NorthPanelTexture,Upgrade Texture, Gate Texture- stores buffered image data
     * g stores Actual JFrame frame graphics
     */
    private JFrame Screen;
    private int wWidth,wHeight;
    private BufferedImage MainTextures;
    private BufferedImage NorthPanelTexture;
    private BufferedImage UpgradeTexture;
    private BufferedImage GateTexture;
    private double xScale,yScale;
    private Graphics g;

    /**
     * Constructor which initialize drawing basic game elements.
     * @param g Actual JFrame frame graphic
     * @param texturesToBasicDraw Array of buffered image elements
     * @param xScale Actual xScale calculated in Board
     * @param yScale Actual yScale calculated in Board
     */
    public DrawGameBasicElements(Graphics g,BufferedImage[] texturesToBasicDraw,double xScale,double yScale)
    {
        this.MainTextures=texturesToBasicDraw[0];
        this.NorthPanelTexture=texturesToBasicDraw[1];
        this.UpgradeTexture=texturesToBasicDraw[2];
        this.GateTexture=texturesToBasicDraw[3];

        this.xScale=xScale;
        this.yScale=yScale;
        this.g=g;

        this.Screen= Game.getScreen();
        this.wWidth=Screen.getContentPane().getWidth();
        this.wHeight=Screen.getContentPane().getHeight();

        drawGrass();
        drawRoad();
        drawDetailElements();
        drawNorthPanel();

        drawPanelBoxes();
        drawNorthPanelText();
        drawSelectedMonsterInfo();

        //It also draws 3 towers on top menu
        drawTowerArea();

        checkAreaSelect();
        drawTopPanelTowers();



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
     * Method responsible for drawing 64x64 Images(grass texture)
     */
    private void drawGrass()
    {
        for(int x=0;x<(wWidth/64)+1;x++)
        {
            for(int y=0;y<(wHeight/64)+1;y++)
            {
                g.drawImage(MainTextures,x*64,y*64,x*64+64,y*64+64,448,128,512,192,null);
            }
        }
    }

    /**
     * Method responsible for DetailElements.
     * Details: Two trees and gate.
     */
    private void drawDetailElements()
    {
        g.drawImage(MainTextures,
                scaleValueForScreen(0, 0),
                scaleValueForScreen(305, 1),
                scaleValueForScreen(64, 0),
                scaleValueForScreen(369, 1),
                224, 416, 286, 512, null);

        g.drawImage(MainTextures,
                scaleValueForScreen(0, 0),
                scaleValueForScreen(433, 1),
                scaleValueForScreen(64, 0),
                scaleValueForScreen(497, 1),
                224, 416, 286, 512, null);

        g.drawImage(GateTexture,
                scaleValueForScreen(952, 0),
                scaleValueForScreen(241, 1),
                scaleValueForScreen(1062, 0),
                scaleValueForScreen(433, 1),
                0, 0, 128, 149, null);

    }

    /**
     * Method responsible for drawing 64x64 textures(pieces of road).
     * Final result brings whole road, which one monsters are moving on.
     */
    private void drawRoad()
    {

        //draw Road episode 1
        for(int x=0;x<3;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(64,0)*x,
                    scaleValueForScreen((320+81),1)-scaleValueForScreen(32,1),
                    scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen((320+81),1)+scaleValueForScreen(32,1),
                    288,320,352,384,null);

        }
        //Draw road episode up bottom
        for(int y=0;y<3;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(128,0),
                    scaleValueForScreen(64,1)*y+scaleValueForScreen(433,1),
                    scaleValueForScreen(192,0),
                    scaleValueForScreen(64,1)*(y+1)+scaleValueForScreen(433,1),
                    288,320,352,384,null);

            g.drawImage(MainTextures,
                    scaleValueForScreen(128,0),
                    scaleValueForScreen(369,1)-scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(192,0),
                    scaleValueForScreen(369,1)-scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);

        }

        //Draw road episode bottom 1
        for(int x=0;x<5;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(64,0)*x+scaleValueForScreen(192,0),
                    scaleValueForScreen(561,1),
                    scaleValueForScreen(64,0)*(x+1)+scaleValueForScreen(192,0),
                    scaleValueForScreen(625,1),
                    288,320,352,384,null);
        }
        //Draw road episode up 1
        for(int x=0;x<6;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(64,0)*x+scaleValueForScreen(192,0),
                    scaleValueForScreen(241,1),
                    scaleValueForScreen(64,0)*(x+1)+scaleValueForScreen(192,0),
                    scaleValueForScreen(177,1),
                    288,320,352,384,null);
        }

        //Draw episode up bottom 1
        for(int y=0;y<1;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(512,0),
                    scaleValueForScreen(241,1)+scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(576,0),
                    scaleValueForScreen(241,1)+scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }

        //Draw road episode bottom y2
        for(int y=0;y<2;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(448,0),
                    scaleValueForScreen(561,1)-scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(512,0),
                    scaleValueForScreen(561,1)- scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }
        //Draw road episode bottom back
        for(int x=0;x<2;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(448,0)-scaleValueForScreen(64,0)*x,
                    scaleValueForScreen(497,1),
                    scaleValueForScreen(448,0)-scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen(433,1),
                    288,320,352,384,null);
        }
        //Draw road episode bottom-> up -> right
        for(int y=0;y<2;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(384,0),
                    scaleValueForScreen(433,1)-scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(320,0),
                    scaleValueForScreen(433,1)-scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }
        //Draw road episode to the fore
        for(int x=0;x<3;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(384,0)+scaleValueForScreen(64,0)*x,
                    scaleValueForScreen(305,1),
                    scaleValueForScreen(384,0)+scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen(369,1),
                    288,320,352,384,null);
        }

        //Draw road first cross
        for(int x=0;x<2;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(576,0)+scaleValueForScreen(64,0)*x,
                    scaleValueForScreen(241,1),
                    scaleValueForScreen(576,0)+scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen(305,1),
                    288,320,352,384,null);
        }

        //Draw road after first cross up
        for(int y=0;y<1;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(640,0),
                    scaleValueForScreen(241,1)-scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(704,0),
                    scaleValueForScreen(241,1)-scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }

        for(int x=0;x<3;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(704,0)+scaleValueForScreen(64,0)*x,
                    scaleValueForScreen(241,1),
                    scaleValueForScreen(704,0)+scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen(177,1),
                    288,320,352,384,null);
        }
        for(int y=0;y<3;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(832,0),
                    scaleValueForScreen(241,1)+scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(896,0),
                    scaleValueForScreen(241,1)+scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }
        //Draw after first cross bottom
        for(int y=0;y<3;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(640,0),
                    scaleValueForScreen(305,1)+scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(704,0),
                    scaleValueForScreen(305,1)+scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }
        //Draw after cross ->bottom->left
        for(int x=0;x<1;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(640,0)-scaleValueForScreen(64,0)*x,
                    scaleValueForScreen(433,1),
                    scaleValueForScreen(640,0)-scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen(497,1),
                    288,320,352,384,null);
        }
        //Draw after bottom-> left bottom
        for(int y=0;y<2;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(576,0),
                    scaleValueForScreen(497,1)+scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(640,0),
                    scaleValueForScreen(497,1)+scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }
        //Bottom to right
        for(int x=0;x<4;x++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(640,0)+scaleValueForScreen(64,0)*x,
                    scaleValueForScreen(561,1),
                    scaleValueForScreen(640,0)+scaleValueForScreen(64,0)*(x+1),
                    scaleValueForScreen(625,1),
                    288,320,352,384,null);
        }

        //Bottom to middle
        for(int y=0;y<3;y++)
        {
            g.drawImage(MainTextures,
                    scaleValueForScreen(832,0),
                    scaleValueForScreen(625,1)-scaleValueForScreen(64,1)*y,
                    scaleValueForScreen(896,0),
                    scaleValueForScreen(625,1)-scaleValueForScreen(64,1)*(y+1),
                    288,320,352,384,null);
        }
        //End draw last episode
        for(int x=0;x<2;x++)
        {
            g.drawImage(MainTextures,
                        scaleValueForScreen(896, 0) + scaleValueForScreen(64, 0) * x,
                        scaleValueForScreen(369, 1),
                        scaleValueForScreen(896, 0) + scaleValueForScreen(64, 0) * (x + 1),
                        scaleValueForScreen(433, 1),
                        288, 320, 352, 384, null);

        }
    }

    /**
     * Method responsible for adding 26 objects to listOfTowerAreas
     * and drawing 64x64 textures(tower areas).
     */
    private void drawTowerArea()
    {
        Board.listOfTowerAreas.clear();
        //1
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(64,0),
                scaleValueForScreen(305,1),
                scaleValueForScreen(128,0),
                scaleValueForScreen(369,1),
                352, 288, 448, 384));

        //2
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(192,0),
                scaleValueForScreen(369,1),
                scaleValueForScreen(256,0),
                scaleValueForScreen(433,1),
                352, 288, 448, 384));
        //12

        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(64,0),
                scaleValueForScreen(433,1),
                scaleValueForScreen(128,0),
                scaleValueForScreen(497,1),
                352, 288, 448, 384));

        //3

        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(64,0),
                scaleValueForScreen(561,1),
                scaleValueForScreen(128,0),
                scaleValueForScreen(625,1),
                352, 288, 448, 384));

        //11
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(256,0),
                scaleValueForScreen(625,1),
                scaleValueForScreen(320,0),
                scaleValueForScreen(689,1),
                352, 288, 448, 384));
        //4
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(512,0),
                scaleValueForScreen(561,1),
                scaleValueForScreen(576,0),
                scaleValueForScreen(625,1),
                352, 288, 448, 384));

        //5
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(384,0),
                scaleValueForScreen(369,1),
                scaleValueForScreen(448,0),
                scaleValueForScreen(433,1),
                352, 288, 448, 384));

        //6
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(576,0),
                scaleValueForScreen(305,1),
                scaleValueForScreen(640,0),
                scaleValueForScreen(369,1),
                352, 288, 448, 384));

        //7
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(128,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(192,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));

        //8

        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(192,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(256,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));

        //9
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(320,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(384,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));
        //10
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(512,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(576,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));
        //13
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(640,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(704,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));
        //14
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(704,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(768,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));

        //15
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(768,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(832,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));

        //16
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(832,0),
                scaleValueForScreen(113,1),
                scaleValueForScreen(896,0),
                scaleValueForScreen(177,1),
                352, 288, 448, 384));

        //17
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(896,0),
                scaleValueForScreen(177,1),
                scaleValueForScreen(960,0),
                scaleValueForScreen(241,1),
                352, 288, 448, 384));

        //18

        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(896,0),
                scaleValueForScreen(305,1),
                scaleValueForScreen(960,0),
                scaleValueForScreen(369,1),
                352, 288, 448, 384));
        //19
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(896,0),
                scaleValueForScreen(433,1),
                scaleValueForScreen(960,0),
                scaleValueForScreen(497,1),
                352, 288, 448, 384));

        //20
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(768,0),
                scaleValueForScreen(369,1),
                scaleValueForScreen(832,0),
                scaleValueForScreen(433,1),
                352, 288, 448, 384));

        //21
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(640,0),
                scaleValueForScreen(497,1),
                scaleValueForScreen(704,0),
                scaleValueForScreen(561,1),
                352, 288, 448, 384));

        //22
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(640,0),
                scaleValueForScreen(625,1),
                scaleValueForScreen(704,0),
                scaleValueForScreen(689,1),
                352, 288, 448, 384));
        //23
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(768,0),
                scaleValueForScreen(625,1),
                scaleValueForScreen(832,0),
                scaleValueForScreen(689,1),
                352, 288, 448, 384));
        //24
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(832,0),
                scaleValueForScreen(625,1),
                scaleValueForScreen(896,0),
                scaleValueForScreen(689,1),
                352, 288, 448, 384));

        //25
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(704,0),
                scaleValueForScreen(241,1),
                scaleValueForScreen(768,0),
                scaleValueForScreen(305,1),
                352, 288, 448, 384));

        //26
        Board.listOfTowerAreas.add(new TowerArea(MainTextures,
                scaleValueForScreen(768,0),
                scaleValueForScreen(241,1),
                scaleValueForScreen(832,0),
                scaleValueForScreen(305,1),
                352, 288, 448, 384));

        int i=0;
        for(TowerArea temp:Board.listOfTowerAreas)
        {
            temp.paintComponent(g,i);
            i++;
        }



    }


    /**
     * Draws proper information about tower if it's the time.
     */
    private void checkAreaSelect()
    {
        for(int whichTower=0;whichTower<Board.listOfTowerAreas.size();whichTower++)
        {
            if (Board.towerAreaSelected[whichTower] == 1) {
                g.setColor(Color.BLACK);

                if (Board.towersCreated[whichTower] == 1 || Board.towersCreated[whichTower] == 2 || Board.towersCreated[whichTower] == 3) {
                    g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(14, 1)));
                    Tower temp=Board.listOfTowers.get(Board.storageOfTowerIdForTowerArea[whichTower]);
                    Board.listOfStrings.get(16).setText("Obrażenia:" + temp.getDamage());
                    g.setColor(new Color(180,0,0,255));
                    Board.listOfStrings.get(16).paintComponent(g);

                    Board.listOfStrings.get(17).setText("Szybkość:" + temp.getFireRatio());
                    g.setColor(new Color(0,180,0,255));
                    Board.listOfStrings.get(17).paintComponent(g);

                    Board.listOfStrings.get(18).setText("Zasięg:" + temp.getRange());
                    g.setColor(new Color(0,0,180,255));
                    Board.listOfStrings.get(18).paintComponent(g);
                    g.setColor(new Color(0,0,0,255));

                    g.drawImage(UpgradeTexture,
                            scaleValueForScreen(283, 0),
                            scaleValueForScreen(10, 1),
                            scaleValueForScreen(333, 0),
                            scaleValueForScreen(71, 1),
                            0, 0, 62, 63, null);
                }

                if (Board.towersCreated[whichTower] == 1) {
                    Tower temp=Board.listOfTowers.get(Board.storageOfTowerIdForTowerArea[whichTower]);
                    g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(15, 1)));
                    Board.listOfStrings.get(5).setText("Czerwona Lv."+temp.getLevel());
                    Board.listOfStrings.get(5).paintComponent(g);

                    Board.listOfStrings.get(19).setText("P:Gol,BAT");
                    g.setColor(new Color(140,140,0,255));
                    Board.listOfStrings.get(19).paintComponent(g);
                    g.setColor(new Color(0,0,0,255));



                } else if (Board.towersCreated[whichTower] == 2) {
                    Tower temp=Board.listOfTowers.get(Board.storageOfTowerIdForTowerArea[whichTower]);
                    g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(15, 1)));
                    Board.listOfStrings.get(5).setText("Zielona Lv."+temp.getLevel());
                    Board.listOfStrings.get(5).paintComponent(g);

                    Board.listOfStrings.get(19).setText("P:YS,BS,RS");
                    g.setColor(new Color(140,140,0,255));
                    Board.listOfStrings.get(19).paintComponent(g);
                    g.setColor(new Color(0,0,0,255));

                } else if (Board.towersCreated[whichTower] == 3) {
                    Tower temp=Board.listOfTowers.get(Board.storageOfTowerIdForTowerArea[whichTower]);
                    g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(15, 1)));
                    Board.listOfStrings.get(5).setText("Niebieska Lv."+temp.getLevel());
                    Board.listOfStrings.get(5).paintComponent(g);

                    Board.listOfStrings.get(19).setText("P:Gb,Gr");
                    g.setColor(new Color(140,140,0,255));
                    Board.listOfStrings.get(19).paintComponent(g);
                    g.setColor(new Color(0,0,0,255));

                }
                Board.towerIsChosen = true;

            }
        }
    }

    /**
     * Draws three tower objects on top panel.
     */
    private void drawTopPanelTowers()
    {
        if(Board.changeTopInfoForTowers==true)
        {
            createMenuTowers();
            Board.listOfTowersOnTopMenu.get(0).paintComponent(g, scaleValueForScreen(30,0),
                    scaleValueForScreen(4,1),
                    scaleValueForScreen(94,0),
                    scaleValueForScreen(68,1),
                    448,448,512,512);

            Board.listOfTowersOnTopMenu.get(1).paintComponent(g, scaleValueForScreen(123,0),
                    scaleValueForScreen(4,1),
                    scaleValueForScreen(187,0),
                    scaleValueForScreen(68,1),
                    448,448,512,512);

            Board.listOfTowersOnTopMenu.get(2).paintComponent(g,scaleValueForScreen(216,0),
                    scaleValueForScreen(4,1),
                    scaleValueForScreen(280,0),
                    scaleValueForScreen(68,1),
                    448,448,512,512);
            Board.listOfTowersOnTopMenu.clear();
        }
    }

    /**
     * Drawing North panel, depending on wWidth and wHeight.
     */
    private void drawNorthPanel()
    {
        for(int x=0;x<(wWidth/400)+1;x++)
        {
            g.drawImage(NorthPanelTexture,x*400,0,x*400+400,scaleValueForScreen(81,1),0,0,400,150,null);

        }
    }

    /**
     * Drawing panel boxes in north panel, on which are string texts.
     */
    private void drawPanelBoxes()
    {

        addPanelButtons();
        g.setColor(new Color(100,120,120));

        //Color if field is checked
        if(Board.towerIsChosen==true)
        {
            g.setColor(new Color(70,70,220));
        }
        //Middle info panel
        g.fillRect(scaleValueForScreen(343,0),scaleValueForScreen(10,1),
                scaleValueForScreen(320,0),scaleValueForScreen(61,1));

        //End game button
        g.setColor(new Color(100,120,120));
        g.fillRect(scaleValueForScreen(854,0),scaleValueForScreen(10,1),
                scaleValueForScreen(120,1),scaleValueForScreen(61,1));


    }

    /**
     * Drawing panel boxes, which are under tower buttons, after choosing empty area field.
     */
    private void addPanelButtons()
    {
        Board.listOfTowerButtons.clear();
        //Three Buttons showing when selected without tower
        Board.listOfTowerButtons.add(new TowerButtons(0,scaleValueForScreen(32,0),scaleValueForScreen(10,1),
                scaleValueForScreen(61,0),scaleValueForScreen(61,1)));

        Board.listOfTowerButtons.add(new TowerButtons(1,scaleValueForScreen(125,0),scaleValueForScreen(10,1),
                scaleValueForScreen(61,0),scaleValueForScreen(61,1)));

        Board.listOfTowerButtons.add(new TowerButtons(2,scaleValueForScreen(218,0),scaleValueForScreen(10,1),
                scaleValueForScreen(61,0),scaleValueForScreen(61,1)));

        Board.listOfTowerButtons.add(new TowerButtons(3,scaleValueForScreen(283,0),scaleValueForScreen(10,1),
                scaleValueForScreen(50,0),scaleValueForScreen(61,1)));

    }

    /**
     * Creating towers for north panel, which will be drawn after choosing empty field.
     */
    private void createMenuTowers()
    {
        Board.listOfTowersOnTopMenu.add(new TowerRed(0,MainTextures,true));

        Board.listOfTowersOnTopMenu.add(new TowerGreen(0,MainTextures,true));

        Board.listOfTowersOnTopMenu.add(new TowerBlue(0,MainTextures,true));
    }

    /**
     * Drawing north panel basic text. Depending on listOfStrings.
     */
    private void drawNorthPanelText()
    {
        g.setFont(new Font("Lucida Sans Typewriter",Font.BOLD,scaleValueForScreen(16,1)));
        g.setColor(Color.WHITE);
        if(MonsterWaveController.timeCounterToNextWave==0)
        {
            Board.listOfStrings.get(0).paintComponent(g);
        }
        if(MonsterWaveController.timeCounterToNextWave!=0)
        {
            Board.listOfStrings.get(15).paintComponent(g);
        }

        Board.listOfStrings.get(1).paintComponent(g);

        g.setColor(Color.BLACK);
        Board.listOfStrings.get(2).paintComponent(g);
        Board.listOfStrings.get(3).paintComponent(g);


        g.setColor(Color.WHITE);
        Board.listOfStrings.get(4).paintComponent(g);

    }

    /**
     * Drawing information about monster, when clicked.
     */
    private void drawSelectedMonsterInfo()
    {
        for(int n=0;n<Board.listOfMonsters.size();n++)
        {
            Monster temp=Board.listOfMonsters.get(n);

                if (temp.getIsSelected() == true) {
                    Board.listOfStrings.get(5).setText(temp.getName() + "   Życie: " + temp.getHealth() + "/" + temp.getMaxHealth());
                    g.setColor(Color.BLACK);
                    Board.listOfStrings.get(5).paintComponent(g);

                    break;
                }

        }
    }





}
