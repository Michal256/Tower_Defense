package Game;

public class AnimatorOfMonsterWalking {
    /**
     * temp- monster object, selected temporary from listOfMonsters
     * typeOfMonster- number of monster type, each monster have his specific number
     */
    private Monster temp;
    private int typeOfMonster;

    /**
     * Constructor responsible for setting proper local values and animateMonsterWalk initialization.
     * @param temp actual monster selected from listOfMonsters
     */
    public AnimatorOfMonsterWalking(Monster temp)
    {
        this.temp=temp;
        this.typeOfMonster=temp.getMonsterType();
        animateMonsterWalk();
    }

    /**
     * Method which will change image source x,y postion to animate monster, while moving.
     *
     */
    private void animateMonsterWalk()
    {
        int actualImageFrame=temp.getActualImageFrame();
        int texturePosition=temp.getTexturePosition();
        new TextureChanger(temp,texturePosition);

        if(typeOfMonster==0)
        {
                //Right
                if(texturePosition==1)
                {

                    switch ((actualImageFrame))
                    {
                        case 1:
                        {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(64);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(128);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        case 2:
                        {
                            temp.setxBeginSrc(128);
                            temp.setyBeginSrc(64);
                            temp.setxEndSrc(192);
                            temp.setyEndSrc(128);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        case 3:
                        {
                            temp.setxBeginSrc(192);
                            temp.setyBeginSrc(64);
                            temp.setxEndSrc(256);
                            temp.setyEndSrc(128);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        default:
                        {
                            new TextureChanger(temp,texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }

                    }
                }
                //Left
                else if(texturePosition==2)
                {
                    switch ((actualImageFrame))
                    {
                        case 1: {
                            temp.setxBeginSrc(256);
                            temp.setyBeginSrc(192);
                            temp.setxEndSrc(320);
                            temp.setyEndSrc(256);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }
                        case 2: {
                            temp.setxBeginSrc(192);
                            temp.setyBeginSrc(192);
                            temp.setxEndSrc(256);
                            temp.setyEndSrc(256);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }
                        case 3: {
                            temp.setxBeginSrc(128);
                            temp.setyBeginSrc(192);
                            temp.setxEndSrc(192);
                            temp.setyEndSrc(256);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }
                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                            }
                    }
                }
                //Top
                else if(texturePosition==3)
                {
                    switch ((actualImageFrame))
                    {
                        case 1: {
                            temp.setxBeginSrc(192);
                            temp.setyBeginSrc(128);
                            temp.setxEndSrc(256);
                            temp.setyEndSrc(192);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;
                        }

                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }
                    }
                }
                //if bottom
                else if(texturePosition==4)
                {
                    switch ((actualImageFrame))
                    {
                        case 1:
                        {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(0);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(64);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        case 2:
                        {
                            temp.setxBeginSrc(192);
                            temp.setyBeginSrc(0);
                            temp.setxEndSrc(256);
                            temp.setyEndSrc(64);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        default:
                        {
                            new TextureChanger(temp,texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }

                    }
                }
        }


        else if(typeOfMonster==2)
        {
                //Right
                if(texturePosition==1)
                {
                    switch ((actualImageFrame))
                    {
                        case 1:
                        {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(192);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(256);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        case 2:
                        {
                            temp.setxBeginSrc(320);
                            temp.setyBeginSrc(192);
                            temp.setxEndSrc(384);
                            temp.setyEndSrc(256);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }

                        default:
                        {
                            new TextureChanger(temp,texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }

                    }
                }
                //Left
                else if(texturePosition==2)
                {
                    switch ((actualImageFrame))
                    {
                        case 1: {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(64);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(128);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }
                        case 2: {
                            temp.setxBeginSrc(320);
                            temp.setyBeginSrc(64);
                            temp.setxEndSrc(384);
                            temp.setyEndSrc(128);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }

                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }
                    }
                }
                //Top
                else if(texturePosition==3)
                {
                    switch ((actualImageFrame))
                    {
                        case 1: {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(0);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(64);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;
                        }
                        case 2: {
                            temp.setxBeginSrc(256);
                            temp.setyBeginSrc(0);
                            temp.setxEndSrc(320);
                            temp.setyEndSrc(64);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;
                        }

                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }
                    }
                }
                //if bottom
                else if(texturePosition==4)
                {
                    switch ((actualImageFrame))
                    {
                        case 1:
                        {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(128);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(192);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        case 2:
                        {
                            temp.setxBeginSrc(256);
                            temp.setyBeginSrc(128);
                            temp.setxEndSrc(320);
                            temp.setyEndSrc(192);
                            temp.setActualImageFrame(actualImageFrame+1);
                            break;

                        }
                        default:
                        {
                            new TextureChanger(temp,texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }

                    }
                }

        }


        else if(typeOfMonster==3)
        {
                //Right
                if (texturePosition == 1) {

                    switch ((actualImageFrame)) {
                        case 1: {
                            temp.setxBeginSrc(96);
                            temp.setyBeginSrc(32);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(64);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }

                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }

                    }
                }
                //Left
                else if (texturePosition == 2) {
                    switch ((actualImageFrame)) {
                        case 1: {
                            temp.setxBeginSrc(96);
                            temp.setyBeginSrc(96);
                            temp.setxEndSrc(128);
                            temp.setyEndSrc(128);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }

                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }
                    }
                }
                //Top
                else if (texturePosition == 3) {
                    //No animation for top
                }
                //if bottom
                else if (texturePosition == 4) {
                    switch ((actualImageFrame)) {
                        case 1: {
                            temp.setxBeginSrc(64);
                            temp.setyBeginSrc(64);
                            temp.setxEndSrc(96);
                            temp.setyEndSrc(96);
                            temp.setActualImageFrame(actualImageFrame + 1);
                            break;

                        }

                        default: {
                            new TextureChanger(temp, texturePosition);
                            temp.setActualImageFrame(1);
                            break;
                        }

                    }
                }
        }


        else if(typeOfMonster==4)
        {
            //Right
            if (texturePosition == 1) {

                switch ((actualImageFrame)) {
                    case 1: {
                        temp.setxBeginSrc(64);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(128);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 2: {
                        temp.setxBeginSrc(256);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(320);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 3: {
                        temp.setxBeginSrc(320);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(384);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 4: {
                        temp.setxBeginSrc(384);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(448);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 5: {
                        temp.setxBeginSrc(448);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(512);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 6: {
                        temp.setxBeginSrc(512);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(576);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 7: {
                        temp.setxBeginSrc(576);
                        temp.setyBeginSrc(192);
                        temp.setxEndSrc(640);
                        temp.setyEndSrc(256);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    default: {
                        new TextureChanger(temp, texturePosition);
                        temp.setActualImageFrame(1);
                        break;
                    }

                }
            }
            //Left
            else if (texturePosition == 2) {
                switch ((actualImageFrame)) {
                    case 1: {
                        temp.setxBeginSrc(64);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(128);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 2: {
                        temp.setxBeginSrc(256);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(320);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 3: {
                        temp.setxBeginSrc(320);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(384);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 4: {
                        temp.setxBeginSrc(384);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(448);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 5: {
                        temp.setxBeginSrc(448);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(512);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 6: {
                        temp.setxBeginSrc(512);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(576);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }

                    case 7: {
                        temp.setxBeginSrc(576);
                        temp.setyBeginSrc(64);
                        temp.setxEndSrc(640);
                        temp.setyEndSrc(128);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    default: {
                        new TextureChanger(temp, texturePosition);
                        temp.setActualImageFrame(1);
                        break;
                    }
                }
            }
            //Top
            else if (texturePosition == 3) {
                switch(actualImageFrame) {
                    case 1: {
                        temp.setxBeginSrc(64);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(128);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 2: {
                        temp.setxBeginSrc(256);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(320);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 3: {
                        temp.setxBeginSrc(320);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(384);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 4: {
                        temp.setxBeginSrc(384);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(448);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 5: {
                        temp.setxBeginSrc(448);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(512);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 6: {
                        temp.setxBeginSrc(512);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(576);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 7: {
                        temp.setxBeginSrc(576);
                        temp.setyBeginSrc(0);
                        temp.setxEndSrc(640);
                        temp.setyEndSrc(64);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    default: {
                        new TextureChanger(temp, texturePosition);
                        temp.setActualImageFrame(1);
                        break;
                    }
                }
            }
            //if bottom
            else if (texturePosition == 4) {
                switch ((actualImageFrame)) {
                    case 1: {
                        temp.setxBeginSrc(64);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(128);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 2: {
                        temp.setxBeginSrc(256);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(320);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 3: {
                        temp.setxBeginSrc(320);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(384);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 4: {
                        temp.setxBeginSrc(384);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(448);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 5: {
                        temp.setxBeginSrc(448);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(512);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 6: {
                        temp.setxBeginSrc(512);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(576);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    case 7: {
                        temp.setxBeginSrc(576);
                        temp.setyBeginSrc(128);
                        temp.setxEndSrc(640);
                        temp.setyEndSrc(192);
                        temp.setActualImageFrame(actualImageFrame + 1);
                        break;

                    }
                    default: {
                        new TextureChanger(temp, texturePosition);
                        temp.setActualImageFrame(1);
                        break;
                    }

                }
            }
        }


    }
}
