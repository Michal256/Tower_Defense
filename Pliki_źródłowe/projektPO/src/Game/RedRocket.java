package Game;

public class RedRocket extends Rocket {

    /**
     * Basic constructor which will create base rocket object, basing on constructor of inherited class.
     * It also sets rocket mainTexture, depending on type of rocket and sets rocket source cords.
     * @param rocketTowerId Id of Tower from which rocket was fired.
     */
    public RedRocket(int rocketTowerId)
    {
        super(rocketTowerId);
        this.mainTexture=loadImage("img/missile/Balls.png");
        setRockedSrc();
    }

    /**
     * Sets proper source cords, to get proper part of external Image.
     */
    private void setRockedSrc()
    {

        setxBeginSrc(0);
        setyBeginSrc(0);
        setxEndSrc(128);
        setyEndSrc(128);


    }
}
