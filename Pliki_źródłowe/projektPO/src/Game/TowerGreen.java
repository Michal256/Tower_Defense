package Game;


import java.awt.image.BufferedImage;

public class TowerGreen extends Tower {

    /**
     * Basic constructor which will create tower green object, basing on constructor of inherited class.
     * It also sets rocket mainTexture, depending on type of tower and sets tower source cords.
     * @param towerRange range of monster detection from the actual position of tower area
     * @param MainTextures stores Image data buffer(for specific type of tower)
     * @param loadBaseTexture stores Image data buffer(base type of tower)
     */
    public TowerGreen(int towerRange, BufferedImage MainTextures,boolean loadBaseTexture)
    {
        super(towerRange,MainTextures);
        if(loadBaseTexture==false) {
            this.MainTextures = loadImage("img/towers/towers.png");

            this.setxBeginSrc(0);
            setyBeginSrc(64);
            setxEndSrc(64);
            setyEndSrc(128);


            setDamage(10);
            setFireRatio(3.0);
            setTowerType(2);
        }
    }
}
