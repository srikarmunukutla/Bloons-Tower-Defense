import java.util.ArrayList;

public class Level {

    private int level = 1;
    private int[] whenspawn = {1,5,9,13,17,21,25,29,33,37,41,45,49,1000000000};
    public void spawn(ArrayList<GameObject> al, BTDMap bm){
        int numchoice = 0;
        for (int i = 0; i < whenspawn.length; i++){
            if (whenspawn[i] > level){
                numchoice = i;
                break;
            }
        }
        int bloon = (int)(Math.random()*numchoice);
        for (int i = 0; i < (Math.log(level+1)/Math.log(whenspawn[numchoice-1])); i++){
            al.add(bm.createBloon(bloon));
        }
    }
}
