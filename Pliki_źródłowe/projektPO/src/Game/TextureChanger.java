package Game;

public class TextureChanger {

    /**
     * temp- actual monster, chosen from listOfMonsters
     * typeOfMonster- type of monster 0-Goblin 1-Grue 2-Golem 3-Bat 4-Spider
     * newTexturePosition- on which side/position monster should turn
     */
    private Monster temp;
    private int typeOfMonster;
    private int newTexturePosition;
    //0-Goblin//1-Grue Monster

    /**
     * Constructor which will set proper variables and
     * initiate changing a texture.
     * @param temp actual monster from listOfMonster
     * @param newTexturePosition on which texture it should be changed //0-Dead/1-Left/2-Right/3-Top/4-Bottom
     */
    public TextureChanger(Monster temp,int newTexturePosition)
    {
    this.temp=temp;
    this.typeOfMonster=temp.getMonsterType();
    this.newTexturePosition=newTexturePosition;

    changeTexturePosition();
    temp.setTexturePosition(newTexturePosition);
    }

    private void changeTexturePosition()
    {
        if(typeOfMonster==0) {
            switch (newTexturePosition) {
                case 0: {
                    //Dead
                    temp.setxBeginSrc(192);
                    temp.setyBeginSrc(256);
                    temp.setxEndSrc(256);
                    temp.setyEndSrc(320);
                    break;
                }
                case 1: {
                    //Left
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(64);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(128);
                    break;
                }
                case 2: {
                    //Right
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(192);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(256);
                    break;
                }
                case 3: {
                    //Top
                    temp.setxBeginSrc(64);
                    temp.setyBeginSrc(128);
                    temp.setxEndSrc(128);
                    temp.setyEndSrc(192);
                    break;
                }
                case 4: {
                    //Bottom
                    temp.setxBeginSrc(64);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(128);
                    temp.setyEndSrc(64);
                    break;
                }
            }
        }
        else if(typeOfMonster==1) {
            switch (newTexturePosition) {
                case 0: {
                    //Dead
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(0);
                    temp.setyEndSrc(0);
                    break;
                }
                case 1: {
                    //Right
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(57);
                    temp.setyEndSrc(70);
                    break;
                }
                case 2: {
                    //Left
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(71);
                    temp.setxEndSrc(57);
                    temp.setyEndSrc(142);
                    break;
                }
                case 3: {
                    //Top
                    temp.setxBeginSrc(58);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(112);
                    temp.setyEndSrc(70);
                    break;
                }
                case 4: {
                    //Bottom
                    temp.setxBeginSrc(58);
                    temp.setyBeginSrc(71);
                    temp.setxEndSrc(112);
                    temp.setyEndSrc(142);
                    break;
                }
            }
        }
        else if(typeOfMonster==2) {
            switch (newTexturePosition) {
                case 0: {
                    //Dead
                    temp.setxBeginSrc(320);
                    temp.setyBeginSrc(320);
                    temp.setxEndSrc(384);
                    temp.setyEndSrc(384);
                    break;
                }
                case 1: {
                    //Right
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(192);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(256);
                    break;
                }
                case 2: {
                    //Left
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(64);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(128);
                    break;
                }
                case 3: {
                    //Top
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(64);
                    break;
                }
                case 4: {
                    //Bottom
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(256);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(320);
                    break;
                }
            }
        }

        else if(typeOfMonster==3) {
            switch (newTexturePosition) {
                case 0: {
                    //Dead
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(0);
                    temp.setyEndSrc(0);
                    break;
                }
                case 1: {
                    //Right
                    temp.setxBeginSrc(32);
                    temp.setyBeginSrc(32);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(64);
                    break;
                }
                case 2: {
                    //Left
                    temp.setxBeginSrc(32);
                    temp.setyBeginSrc(96);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(128);
                    break;
                }
                case 3: {
                    //Top
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(64);
                    temp.setxEndSrc(32);
                    temp.setyEndSrc(96);
                    break;
                }
                case 4: {
                    //Bottom
                    temp.setxBeginSrc(96);
                    temp.setyBeginSrc(64);
                    temp.setxEndSrc(128);
                    temp.setyEndSrc(96);
                    break;
                }
            }
        }

        else if(typeOfMonster==4) {
            switch (newTexturePosition) {
                case 0: {
                    //Dead
                    temp.setxBeginSrc(192);
                    temp.setyBeginSrc(256);
                    temp.setxEndSrc(256);
                    temp.setyEndSrc(320);
                    break;
                }
                case 1: {
                    //Right
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(192);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(256);
                    break;
                }
                case 2: {
                    //Left
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(64);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(128);
                    break;
                }
                case 3: {
                    //Top
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(0);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(64);
                    break;
                }
                case 4: {
                    //Bottom
                    temp.setxBeginSrc(0);
                    temp.setyBeginSrc(128);
                    temp.setxEndSrc(64);
                    temp.setyEndSrc(192);
                    break;
                }
            }
        }



    }
}
