package Game;

public class GameResources {
    /**
     * gameResources- it's resource available in game for purchases
     * priceOfRedTower etc.- amount of resource, necessary to buy Red tower
     * priceOfUpgrade- amount of resource, necessary to upgrade any tower on level
     **/
    private int gameResources;
    private int priceOfRedTower=250;
    private int priceOfGreenTower=250;
    private int priceOfBlueTower=250;
    private int priceOfUpgrade=350;


    /**
     * Constructor which assigns resource amount while game begins.
     * @param resourcesOnStart resources given at the start of the game
     */
    public GameResources(int resourcesOnStart)
    {
        this.gameResources=resourcesOnStart;
    }

    /**
     * Resource getter
     * @return game resource
     */
    public int getGameResources() {
        return gameResources;
    }

    /**
     * Resource setter
     * @param gameResources amount of game resource to be set
     */
    public void setGameResources(int gameResources) {
        this.gameResources = gameResources;
    }

    /**
     * Getter which will return array of tower prices.
     * @return array of tower prices(each type is another element in array)
     */
    public int[] getTowersPrices()
    {
        int [] towersPrices={this.priceOfBlueTower,this.priceOfGreenTower,this.priceOfRedTower};
        return towersPrices;
    }

    /**
     * Getter for amount of resource necessary to purchase tower upgrade.
     * @return amount of resource necessary to purchase tower upgrade
     */
    public int getPriceOfUpgrade() {
        return priceOfUpgrade;
    }
}
