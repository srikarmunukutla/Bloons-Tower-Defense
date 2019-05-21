import java.util.ArrayList;

public class Level {

    private int level = 1;
    private int spawnrate = 200;
    private int wave = 1;
    private int[] whenspawn = {1,5,9,13,17,21,25,29,33,37,41,45,49,1000000000};
    public void spawn(ArrayList<GameObject> al, BTDMap bm, long ticks){
        if (ticks % spawnrate != 0){
            return;
        }
        int numchoice = 0;
        for (int i = 0; i < whenspawn.length; i++){
            if (whenspawn[i] > level){
                numchoice = i-1;
                break;
            }
        }
        int bloon = (int)(Math.random()*numchoice+1);
        for (int i = 0; i < Math.ceil((Math.log(level+1)/Math.log(whenspawn[bloon]+1))); i++) {
            al.add(bm.createBloon(bloon, 4 * i));
        }
        wave++;
    }
    public void changeSpawn(){
        level++;
        if (level % 5 == 0){
            spawnrate += 200;
        }
        spawnrate-=40;
        wave = 0;
    }


    public int getWave(){
        return wave;
    }
}
