package Game;

import javax.swing.*;
import java.awt.*;

public class TowerButtons extends JPanel {

    private int towerType,x,y,sizex,sizey;
    /**
     * Constructor which is setting proper variables.
     * //0 Red//1 Green //2 Blue - which Tower
     * //0 none //1 Red //2 Green //3 Blue - in tower Array
     * @param towerType type of the tower 0-Red/1-Green/2-Blue or 3-Upgrade
     * @param x where the button begin x cord
     * @param y where the button begin y cord
     * @param sizex size on x axis
     * @param sizey size on y axis
     */
    public TowerButtons(int towerType, int x,int y,int sizex,int sizey)
    {
        this.towerType=towerType;
        this.x=x;
        this.y=y;
        this.sizex=sizex;
        this.sizey=sizey;

    }

    /**
     * Method which checks which button has been clicked.
     * This will build proper tower or upgrade chosen one.
     * @param xMouse x axis cord of clicked area
     * @param yMouse y axis cord of clicked area
     * @param gameResources actual game resource
     * @param areaIsEmpty tower area
     */
    public void checkClickedArea(int xMouse, int yMouse, GameResources gameResources,boolean areaIsEmpty)
    {
        if((this.x<=xMouse&&(this.x+this.sizex)>=xMouse)
         &&(this.y<=yMouse&&(this.y+this.sizey)>=yMouse))
        {
            if(areaIsEmpty==true) {
                if (towerType == 0) {
                    tryToBuildTower(gameResources, (short) 0);
                } else if (towerType == 1) {
                    tryToBuildTower(gameResources, (short) 1);
                } else if (towerType == 2) {
                    tryToBuildTower(gameResources, (short) 2);
                }
            }
            else{
                if (towerType == 3) {
                    upgradeTower(gameResources);
                }
            }

        }
    }

    /**
     *
     * @param gameResources game resources
     * @param whichTower type of tower(in this case 0-Red 1-Green 2-Blue)
     */
    private void tryToBuildTower(GameResources gameResources,short whichTower)
    {
        int i=-1;
        int tabLength=Board.towerAreaSelected.length;
        int resources=-1;

        do {
            i++;
            if(Board.towerAreaSelected[i]==1&&Board.towersCreated[i]==0)
            {
                resources=gameResources.getGameResources();
                int[] towersPrices=gameResources.getTowersPrices();

                int resourcesAfter=tryToBuy(resources,towersPrices[whichTower]);

                if(resourcesAfter<resources)
                {
                    gameResources.setGameResources(resourcesAfter);
                    if(whichTower==0)
                    {
                        Board.towersCreated[i]=1;

                    }
                    else if(whichTower==1)
                    {
                        Board.towersCreated[i]=2;

                    }
                    else if(whichTower==2)
                    {
                        Board.towersCreated[i]=3;
                    }
                }

            }
        }while(i<tabLength-1&&resources==-1);
    }

    /**
     * If resource is same, something went wrong.
     * @param resources actual game resources
     * @param cost cost of sth.
     * @return resource
     */
    private int tryToBuy(int resources,int cost)
    {
        if(resources>=cost)
        {
            resources=resources-cost;
            return resources;
        }
        else
        {
            return resources;
        }

    }

    /**
     * UpgradeTower is already called when we have chosen tower.
     * @param gameResources gold and others
     */
    private void upgradeTower(GameResources gameResources)
    {
        for(int i=0;i<Board.towerAreaSelected.length;i++) {
            if(Board.towerAreaSelected[i]==1)
            {
                Tower temp=Board.listOfTowers.get(Board.storageOfTowerIdForTowerArea[i]);
                if(temp.getLevel()<3)
                {
                    int resourceBefore=gameResources.getGameResources();
                    int resourceAfter=tryToBuy(resourceBefore,gameResources.getPriceOfUpgrade()*(temp.getLevel()));

                    if(resourceAfter<resourceBefore) {
                        gameResources.setGameResources(resourceAfter);

                        temp.setDamage(temp.getDamage() +(int)(temp.getDamage()*0.5));
                        temp.setFireRatio(temp.getFireRatio() * 2);
                        temp.setTowerRange(temp.getRange() + 32);
                        temp.setLevel(temp.getLevel() + 1);
                    }
                }
                break;
            }
        }
    }
    public void paintComponent(Graphics g)
    {
        g.fillRect(this.x,this.y,
                sizex,sizey);
    }

}
