package Game;

public class MonsterWaveController
{

    static public int timeMonsterDistance=3;
    final public int timeToNextWave=20;
    static public int timeCounterToNextWave=0;
    static public int timeCounterForDistance=0;
    static public int sizeOfMonsterWave=0;
    static public int actualMonsterInArrayOfWave=0;

    /**
     * Constructor which is controller for monster waves.
     * This constructor also decide when monsters should be created.(Based on MonsterWaves).
     */
    public MonsterWaveController()
    {

        if(Board.getGameTime()==1) {
            Board.actualWaveNumber++;
            getWaveMonsterSpawnTime();
            updateActualWaveSize();
        }
        else if(Board.getGameTime()>1)
        {
            if(timeCounterToNextWave>0)
            {
                timeCounterToNextWave++;
            }


            if(timeCounterToNextWave>=timeToNextWave&&Board.actualWaveNumber<10)
            {
                for(int x=0;x<Board.listOfMonsters.size();x++)
                {
                    Monster temp=Board.listOfMonsters.get(x);
                    temp.setVisible(false);
                }
                Board.actualWaveNumber++;
                sizeOfMonsterWave=0;
                actualMonsterInArrayOfWave=0;
                timeCounterToNextWave=0;
                updateActualWaveSize();
            }
            else if(checkIfMonstersAreAlive()==false&&actualMonsterInArrayOfWave>=sizeOfMonsterWave&&timeCounterToNextWave==0&&Board.actualWaveNumber<10)
            {
                timeCounterToNextWave++;
            }
            else if(actualMonsterInArrayOfWave<sizeOfMonsterWave)
            {
                if(timeCounterForDistance>=timeMonsterDistance)
                {
                    getWaveMonsterSpawnTime();
                    updateWaveMonsterSpawn();
                    timeCounterForDistance=0;
                }
                else {
                    timeCounterForDistance++;
                }
            }
            else if(checkIfMonstersAreAlive()==true)
            {

            }
            else if(Board.actualWaveNumber>=10&&actualMonsterInArrayOfWave>=sizeOfMonsterWave)
            {
                Board.informationPanelType=1;
                Board.GameIsWon=true;
            }
        }

    }

    /**
     * This method is responsible for getting a proper size of upcoming wave.
     */
    public void updateActualWaveSize()
    {
        switch(Board.actualWaveNumber)
        {
            case 1:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave1().size();
                break;
            }
            case 2:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave2().size();
                break;
            }
            case 3:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave3().size();
                break;
            }
            case 4:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave4().size();
                break;
            }
            case 5:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave5().size();
                break;
            }
            case 6:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave6().size();
                break;
            }
            case 7:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave7().size();
                break;
            }
            case 8:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave8().size();
                break;
            }
            case 9:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave9().size();
                break;
            }
            case 10:
            {
                sizeOfMonsterWave=Board.monsterWaves.getWave10().size();
                break;
            }
        }
    }

    /**
     * This method is responsible for adding proper monsters in proper wave, on the board.
     */
    public void updateWaveMonsterSpawn()
    {
        switch(Board.actualWaveNumber)
        {
            case 1:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave1().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 2:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave2().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 3:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave3().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 4:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave4().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 5:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave5().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;

                break;
            }
            case 6:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave6().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 7:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave7().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 8:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave8().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 9:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave9().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
            case 10:
            {
                Monster monsterToSpawn=Board.monsterWaves.getWave10().get(actualMonsterInArrayOfWave);
                Board.listOfMonsters.add(monsterToSpawn);
                actualMonsterInArrayOfWave++;
                break;
            }
        }
    }

    /**
     * This method is responsible for getting appropriate respawn time for next monster in queue.
     */
    public void getWaveMonsterSpawnTime()
    {
        switch(Board.actualWaveNumber)
        {
            case 1:
            {
                if(Board.monsterWaves.getWave1().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave1().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 2:
            {
                if(Board.monsterWaves.getWave2().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave2().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 3:
            {
                if(Board.monsterWaves.getWave3().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave3().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 4:
            {
                if(Board.monsterWaves.getWave4().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave4().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 5:
            {
                if(Board.monsterWaves.getWave5().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave5().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }

                break;
            }
            case 6:
            {
                if(Board.monsterWaves.getWave6().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave6().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 7:
            {
                if(Board.monsterWaves.getWave7().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave7().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 8:
            {
                if(Board.monsterWaves.getWave8().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave8().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 9:
            {
                if(Board.monsterWaves.getWave9().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave9().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
            case 10:
            {
                if(Board.monsterWaves.getWave10().size()>actualMonsterInArrayOfWave+1) {
                    Monster monsterToSpawn = Board.monsterWaves.getWave10().get(actualMonsterInArrayOfWave + 1);
                    timeMonsterDistance = monsterToSpawn.getRespawnTime();
                }
                break;
            }
        }
    }

    /**
     * Method is responsible for checking, if any on monsters on the board are alive.
     * @return true if any of monster on the board is alive
     */
    private boolean checkIfMonstersAreAlive()
    {
        boolean monsterAreAlive=false;
        for(Monster temp: Board.listOfMonsters)
        {
            if(temp.getHealth()!=0)
            {
                monsterAreAlive=true;
                break;
            }
        }
        return monsterAreAlive;
    }



}
