package Game;

public class GameRoad {
    /**
     * arrayOfRoads- array which contains points(X and Y), points which will be reached by monsters
     * arrayOfRoads[walkPath][NumberOfCurrentSection][0-X 1-Y]
     * walkPath=[0-Start Main, 1-Top Cross 1, 2-Bottom Cross 1, 3-Top Cross 2, 4-Bottom Cross 2]
     */
    private int[][][] arrayOfRoads= new int[5][7][2];

    public GameRoad()
    {
        fillArrayOfRoads();
    }
    private void fillArrayOfRoads()
    {
        //[0] main road at start
        arrayOfRoads[0][0][0]=192;
        arrayOfRoads[0][0][1]=433;

        //[1] Top road first cross
        arrayOfRoads[1][0][0]=192;
        arrayOfRoads[1][0][1]=241;

        arrayOfRoads[1][1][0]=576;
        arrayOfRoads[1][1][1]=241;

        arrayOfRoads[1][2][0]=576;
        arrayOfRoads[1][2][1]=305;

        //[2] Bottom road first cross
        arrayOfRoads[2][0][0]=192;
        arrayOfRoads[2][0][1]=625;

        arrayOfRoads[2][1][0]=512;
        arrayOfRoads[2][1][1]=625;

        arrayOfRoads[2][2][0]=512;
        arrayOfRoads[2][2][1]=497;

        arrayOfRoads[2][3][0]=384;
        arrayOfRoads[2][3][1]=497;

        arrayOfRoads[2][4][0]=384;
        arrayOfRoads[2][4][1]=369;

        arrayOfRoads[2][5][0]=576;
        arrayOfRoads[2][5][1]=369;

        arrayOfRoads[2][6][0]=576;
        arrayOfRoads[2][6][1]=305;

        //[3] Top road second cross
        arrayOfRoads[3][0][0]=704;
        arrayOfRoads[3][0][1]=305;

        arrayOfRoads[3][1][0]=704;
        arrayOfRoads[3][1][1]=241;

        arrayOfRoads[3][2][0]=896;
        arrayOfRoads[3][2][1]=241;

        arrayOfRoads[3][3][0]=896;
        arrayOfRoads[3][3][1]=433;

        arrayOfRoads[3][4][0]=1006;
        arrayOfRoads[3][4][1]=433;

        //[4] Bottom road second cross
        arrayOfRoads[4][0][0]=704;
        arrayOfRoads[4][0][1]=305;

        arrayOfRoads[4][1][0]=704;
        arrayOfRoads[4][1][1]=497;

        arrayOfRoads[4][2][0]=640;
        arrayOfRoads[4][2][1]=497;

        arrayOfRoads[4][3][0]=640;
        arrayOfRoads[4][3][1]=625;

        arrayOfRoads[4][4][0]=896;
        arrayOfRoads[4][4][1]=625;

        arrayOfRoads[4][5][0]=896;
        arrayOfRoads[4][5][1]=433;

        arrayOfRoads[4][6][0]=1006;
        arrayOfRoads[4][6][1]=433;

    }

    /**
     * Getter for array of roads.
     * @return returns array of roads
     */
    public int[][][] getArrayOfRoads() {
        return arrayOfRoads;
    }
}
