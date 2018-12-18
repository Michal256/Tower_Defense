package Game;

public class GoblinMonster extends Monster {

    /**
     * Basic constructor which will create base monster object, basing on constructor of inherited class.
     * It also sets monster mainTexture, depending on type of Monster and sets Monster beginning cords.
     * @param name Name of monster
     * @param health Health of monster
     * @param monstersWalkDistanceX Monster walk speed X
     * @param monstersWalkDistanceY Monster walk speed Y
     * @param resourceReward Reward for a monster
     * @param monsterSize Monster additional size
     * @param whichSide Number of lane he will be walking on.
     * @param respawnTime Time that must pass, to respawn this monster.
     */
    public GoblinMonster(String name,int health,int monstersWalkDistanceX,int monstersWalkDistanceY,int resourceReward,int monsterSize,int whichSide,int respawnTime)
    {
        super(name,0,health,monstersWalkDistanceX,monstersWalkDistanceY,resourceReward,monsterSize,whichSide,respawnTime);
        this.mainTexture=loadImage("img/goblin/goblinsword.png");
        setMonsterCords();



    }

    /**
     * Sets proper cords for monster, when he will be created.
     * Sets proper source cords, to get proper part of external Image.
     */
    private void setMonsterCords()
    {
        walkDirection[0]=1;
        setxBeginMap((-72+(getWhichSide()*18*(getWalkDirection()[1]))));
        setyBeginMap(334+(getWhichSide()*18*(getWalkDirection()[0])));
        setxEndMap(0+(getWhichSide()*18*(getWalkDirection()[1])));
        setyEndMap(398+(getWhichSide()*18*(getWalkDirection()[0])));


        setxBeginSrc(0);
        setyBeginSrc(64);
        setxEndSrc(64);
        setyEndSrc(128);

    }



}
