import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class SniperMonkey extends Monkey {
	public SniperMonkey(int a, int b) {
		super(a,b,200,"Sniper_Monkey.png",1,2,300,1);
	}
	@Override
    public void update(ArrayList<GameObject> al, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile){
        //If no bloons, no code to run
        ArrayList<Bloon> bloonal = getBloons(al);
        ArrayList<Spikes> spikeal = getSpikes(al);
        if (bloonal.size() == 0){
            return;
        }
        if (secsbefreload > 0){
            secsbefreload--;
            return;
        }
        int index = -1;
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i) instanceof Bloon) {
                if(index == -1){
                    index = i;
                }
                if (((Bloon)al.get(i)).getDistance() > ((Bloon)al.get(index)).getDistance()) {
                    index = i;
                }
            }
        }
        al.addAll(((Bloon)al.get(index)).hit((int) ((Bloon)al.get(index)).getX(), (int) ((Bloon)al.get(index)).getY(), getDamage()));
        al.remove(index);
        secsbefreload = getReloadRate();
    }
	
    @Override
    public Projectile getProj(){
        return null;
    }
}
