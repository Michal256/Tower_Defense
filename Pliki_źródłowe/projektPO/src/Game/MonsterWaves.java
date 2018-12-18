package Game;

import java.util.ArrayList;

public class MonsterWaves {

    /**
     * whichSide- on which side the monster will be spawned (0-bottom 1-middle 2-top)
     * wave(number)- contains list of Monster objects, which will be taking part in game, at this specific wave.
     */
    private int whichSide=0;
    private ArrayList<Monster> wave1=new ArrayList<>();
    private ArrayList<Monster> wave2=new ArrayList<>();
    private ArrayList<Monster> wave3=new ArrayList<>();
    private ArrayList<Monster> wave4=new ArrayList<>();
    private ArrayList<Monster> wave5=new ArrayList<>();
    private ArrayList<Monster> wave6=new ArrayList<>();
    private ArrayList<Monster> wave7=new ArrayList<>();
    private ArrayList<Monster> wave8=new ArrayList<>();
    private ArrayList<Monster> wave9=new ArrayList<>();
    private ArrayList<Monster> wave10=new ArrayList<>();

    /**
     * Constructor which will be used at the start of the game, while loading the board.
     * Constructor will create all waves.
     */
    public MonsterWaves()
    {
        createWaves();
    }

    /**
     * Creates waves by adding new Monster objects.
     */
    public void createWaves() {
        for (int i = 1; i <= 10; i++) {
            switch (i) {
                case 1: {

                    for(int n=1;n<=18;n++) {
                        wave1.add(new GoblinMonster("Goblin", 250, 2, 2, 40, 0, whichSide,3));
                        if(n>10)
                        {
                            wave1.get(wave1.size()-1).setRespawnTime(2);
                        }
                        changeSide();
                    }

                    break;
                }
                case 2: {
                    for(int n=1;n<=3;n++) {
                        wave2.add(new GoblinMonster("Goblin", 250, 2, 2, 40, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave2.add(new GolemMonster("Golem", 850, 2, 2, 80, 0, whichSide,3));
                        changeSide();
                    }
                    for(int n=1;n<=5;n++) {
                        wave2.add(new GoblinMonster("Goblin", 250, 2, 2, 40, 0, whichSide,2));
                        if(n==1)
                        {
                            wave2.get(wave2.size()-1).setRespawnTime(5);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave2.add(new GolemMonster("Golem", 850, 2, 2, 80, 0, whichSide,3));
                        changeSide();
                    }
                    for(int n=1;n<=5;n++) {
                        wave2.add(new GoblinMonster("Goblin", 250, 2, 2, 40, 0, whichSide,1));
                        if(n==1)
                        {
                            wave2.get(wave2.size()-1).setRespawnTime(3);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=2;n++)
                    {
                        wave2.add(new GolemMonster("Golem", 850, 2, 2, 80, 0, whichSide,2));
                        if(n==1)
                        {
                            wave2.get(wave2.size()-1).setRespawnTime(7);
                        }
                        changeSide();
                    }

                    break;
                }
                case 3: {
                    for(int n=1;n<=4;n++) {
                        wave3.add(new GoblinMonster("Goblin", 350, 2, 2, 80, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave3.add(new YellowSpiderMonster("Yellow Spider", 200, 4, 4, 45, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=2;n++) {
                        wave3.add(new GoblinMonster("Goblin", 350, 2, 2, 40, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=2;n++)
                    {
                        wave3.add(new GolemMonster("Golem", 850, 2, 2, 80, 0, whichSide,1));
                        if(n==1)
                        {
                            wave3.get(wave3.size()-1).setRespawnTime(5);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=2;n++)
                    {
                        wave3.add(new YellowSpiderMonster("Yellow Spider", 200, 4, 4, 45, 0, whichSide,1));
                        if(n==1)
                        {
                            wave3.get(wave3.size()-1).setRespawnTime(4);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=8;n++) {
                        wave3.add(new GoblinMonster("Goblin", 350, 2, 2, 40, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave3.add(new GolemMonster("Golem", 850, 2, 2, 80, 0, whichSide,2));
                        changeSide();
                    }
                    for(int n=1;n<=6;n++) {
                        wave3.add(new GoblinMonster("Goblin", 350, 2, 2, 40, 0, whichSide,1));
                        if(n==1)
                        {
                            wave3.get(wave3.size()-1).setRespawnTime(4);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave3.add(new YellowSpiderMonster("Yellow Spider", 200, 4, 4, 45, 0, whichSide,1));
                        changeSide();
                    }


                    break;
                }
                case 4: {
                    for(int n=1;n<=3;n++)
                    {
                        wave4.add(new YellowSpiderMonster("Yellow Spider", 250, 4, 4, 45, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=3;n++)
                    {
                        wave4.add(new RedSpiderMonster("Red Spider", 350, 4, 4, 55, 0, whichSide,2));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++) {
                        wave4.add(new GoblinMonster("Goblin", 400, 2, 2, 40, 0, whichSide,1));
                        if(n==1)
                        {
                            wave4.get(wave4.size()-1).setRespawnTime(4);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=2;n++)
                    {
                        wave4.add(new GolemMonster("Golem", 950, 2, 2, 80, 0, whichSide,2));
                        changeSide();
                    }

                    for(int n=1;n<=4;n++)
                    {
                        wave4.add(new BatMonster("Bat", 150, 5, 5, 35, 0, whichSide,1));
                        changeSide();
                    }

                    for(int n=1;n<=10;n++) {
                        wave4.add(new GoblinMonster("Goblin", 400, 2, 2, 40, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=3;n++)
                    {
                        wave4.add(new YellowSpiderMonster("Yellow Spider", 250, 4, 4, 45, 0, whichSide,2));
                        if(n==1)
                        {
                            wave4.get(wave4.size()-1).setRespawnTime(4);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave4.add(new RedSpiderMonster("Red Spider", 350, 4, 4, 55, 0, whichSide,1));
                        changeSide();
                    }

                    for(int n=1;n<=7;n++)
                    {
                        wave4.add(new BatMonster("Bat", 150, 5, 5, 35, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++) {
                        wave4.add(new GoblinMonster("Goblin", 400, 2, 2, 40, 0, whichSide,1));
                        changeSide();
                    }

                    break;
                }
                case 5: {
                    for(int n=1;n<=4;n++)
                    {
                        wave5.add(new GolemMonster("Golem", 1050, 2, 2, 80, 0, whichSide,2));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++) {
                        wave5.add(new GoblinMonster("Goblin", 450, 3, 3, 40, 0, whichSide,1));
                        if(n==1)
                        {
                            wave5.get(wave5.size()-1).setRespawnTime(7);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=3;n++)
                    {
                        wave5.add(new RedSpiderMonster("Red Spider", 400, 4, 4, 55, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=6;n++)
                    {
                        wave5.add(new BatMonster("Bat", 150, 5, 5, 35, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++) {
                        wave5.add(new GoblinMonster("Goblin", 450, 3, 3, 40, 0, whichSide,0));
                        if(n==1)
                        {
                            wave5.get(wave5.size()-1).setRespawnTime(3);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=5;n++)
                    {
                        wave5.add(new YellowSpiderMonster("Yellow Spider", 300, 4, 4, 45, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++) {
                        wave5.add(new GoblinMonster("Goblin", 450, 3, 3, 40, 0, whichSide,0));
                        if(n==1)
                        {
                            wave5.get(wave5.size()-1).setRespawnTime(3);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=8;n++)
                    {
                        wave5.add(new BatMonster("Bat", 150, 5, 5, 35, 0, whichSide,1));
                        changeSide();
                    }
                    //Boss
                    for(int n=1;n<=1;n++) {
                        wave5.add(new GoblinMonster("Boss Goblin", 6500, 2, 2, 500, 16, whichSide,10));
                        changeSide();
                    }


                    break;
                }
                case 6: {
                    for(int n=1;n<=4;n++)
                    {
                        wave6.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++)
                    {
                        wave6.add(new BatMonster("Bat", 200, 5, 5, 35, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=16;n++) {
                        wave6.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++)
                    {
                        wave6.add(new RedSpiderMonster("Red Spider", 400, 5, 5, 55, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++)
                    {
                        wave6.add(new GolemMonster("Golem", 1050, 3, 3, 80, 0, whichSide,1));
                        if(n==1)
                        {
                            wave6.get(wave6.size()-1).setRespawnTime(2);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=4;n++)
                    {
                        wave6.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,1));
                        if(n==1)
                        {
                            wave6.get(wave6.size()-1).setRespawnTime(2);
                        }
                        changeSide();
                    }
                    break;
                }
                case 7: {
                    for(int n=1;n<=10;n++)
                    {
                        wave7.add(new RedSpiderMonster("Red Spider", 400, 5, 5, 55, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=7;n++)
                    {
                        wave7.add(new BlackSpiderMonster("Black Spider", 500, 5, 5, 65, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=8;n++)
                    {
                        wave7.add(new GolemMonster("Golem", 1050, 3, 3, 80, 0, whichSide,0));
                        if(n==1)
                        {
                            wave7.get(wave7.size()-1).setRespawnTime(1);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=7;n++)
                    {
                        wave7.add(new BlackSpiderMonster("Black Spider", 500, 5, 5, 65, 0, whichSide,2));
                        if(n==1)
                        {
                            wave7.get(wave7.size()-1).setRespawnTime(3);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=8;n++)
                    {
                        wave7.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,0));
                        if(n==1)
                        {
                            wave7.get(wave7.size()-1).setRespawnTime(4);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=10;n++)
                    {
                        wave7.add(new BatMonster("Bat", 200, 5, 5, 35, 0, whichSide,1));
                        changeSide();
                    }

                    break;
                }
                case 8: {
                    for(int n=1;n<=30;n++) {
                        wave8.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=8;n++)
                    {
                        wave8.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,1));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave8.add(new RedSpiderMonster("Red Spider", 400, 6, 6, 55, 0, whichSide,0));
                        if(n==1)
                        {
                            wave8.get(wave8.size()-1).setRespawnTime(2);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=12;n++)
                    {
                        wave8.add(new GolemMonster("Golem", 1050, 3, 3, 80, 0, whichSide, 0));
                        if(n==1)
                        {
                            wave8.get(wave8.size()-1).setRespawnTime(3);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=20;n++) {
                        wave8.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave8.add(new BlackSpiderMonster("Black Spider", 500, 6, 6, 65, 0, whichSide,1));
                        if(n==1)
                        {
                            wave8.get(wave8.size()-1).setRespawnTime(3);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave8.add(new BlueSpiderMonster("Big Blue Spider", 7000, 4, 4, 500, 32, whichSide,5));
                        changeSide();
                    }
                    //Boss
                    for(int n=1;n<=1;n++)
                    {
                        wave8.add(new BlackSpiderMonster("Boss Black Spider", 14000, 2, 2, 900, 64, whichSide,10));
                        changeSide();
                    }



                    break;
                }
                case 9: {
                    for(int n=1;n<=16;n++)
                    {
                        wave9.add(new GolemMonster("Golem", 1050, 3, 3, 80, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=10;n++)
                    {
                        wave9.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,0));
                        if(n==1)
                        {
                            wave9.get(wave9.size()-1).setRespawnTime(1);
                        }
                        changeSide();
                    }
                    for(int n=1;n<=30;n++) {
                        wave9.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave9.add(new GolemMonster("Golem", 1050, 3, 3, 80, 0, whichSide,0));
                        if(n==1)
                        {
                            wave9.get(wave9.size()-1).setRespawnTime(2);
                        }
                        changeSide();
                    }
                    //Boss
                    for(int n=1;n<=1;n++)
                    {
                        wave9.add(new GolemMonster("Boss Big Golem", 19000, 2, 2, 1000, 16, whichSide,1));
                        changeSide();
                    }

                    break;
                }
                case 10: {

                    for(int n=1;n<=40;n++) {
                        wave10.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=30;n++)
                    {
                        wave10.add(new BatMonster("Bat", 200, 5, 5, 35, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=20;n++)
                    {
                        wave10.add(new GolemMonster("Golem", 1050, 3, 3, 80, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave10.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=30;n++) {
                        wave10.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave10.add(new YellowSpiderMonster("Yellow Spider", 250, 6, 6, 45, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=20;n++) {
                        wave10.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave10.add(new GrueMonster("Grue Monster", 750, 3, 3, 100, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=12;n++)
                    {
                        wave10.add(new RedSpiderMonster("Red Spider", 400, 6, 6, 55, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=15;n++)
                    {
                        wave10.add(new GrueMonster("Grue Monster", 750, 4, 4, 100, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=8;n++)
                    {
                        wave10.add(new BlackSpiderMonster("Black Spider", 500, 5, 5, 65, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=40;n++) {
                        wave10.add(new GoblinMonster("Goblin", 450, 4, 4, 40, 0, whichSide,0));
                        changeSide();
                    }
                    for(int n=1;n<=1;n++)
                    {
                        wave10.add(new GrueMonster("Final Boss", 45000, 1, 1, 5000, 16, whichSide,1));
                        changeSide();
                    }

                    break;

                }
            }
        }
    }

    private void changeSide()
    {
        if(whichSide==0)
        {
            whichSide=1;
        }
        else if(whichSide==1)
        {
            whichSide=2;
        }
        else if(whichSide==2)
        {
            whichSide=0;
        }
    }

    public ArrayList<Monster> getWave1() {
        return wave1;
    }

    public ArrayList<Monster> getWave2() {
        return wave2;
    }

    public ArrayList<Monster> getWave3() {
        return wave3;
    }

    public ArrayList<Monster> getWave4() {
        return wave4;
    }

    public ArrayList<Monster> getWave5() {
        return wave5;
    }

    public ArrayList<Monster> getWave6() {
        return wave6;
    }

    public ArrayList<Monster> getWave7() {
        return wave7;
    }

    public ArrayList<Monster> getWave8() {
        return wave8;
    }

    public ArrayList<Monster> getWave9() {
        return wave9;
    }

    public ArrayList<Monster> getWave10() {
        return wave10;
    }
}
