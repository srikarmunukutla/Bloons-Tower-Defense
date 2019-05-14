import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class SniperMonkey extends Monkey {
	public SniperMonkey(int a, int b) {
		super(a,b,200,"Sniper_Monkey.png",1,2,300,1);
	}
	@Override
    public void update(ArrayList<Bloon> bloonal, ArrayList<Spikes> spikeal, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile){
        //If no bloons, no code to run
		
        if (bloonal.size() == 0){
            return;
        }
        if (secsbefreload > 0){
            secsbefreload--;
            return;
        }
        int index = 0;
        for (int i = 0; i < bloonal.size(); i++) {
        	if (bloonal.get(i).getDistance() > bloonal.get(index).getDistance()) {
        		index = i;
        	}
        }
        bloonal.get(index).hit((int) bloonal.get(index).getX(), (int) bloonal.get(index).getY(), getDamage());
    }
	
    @Override
    public Projectile getProj(){
        return null;
    }
}
