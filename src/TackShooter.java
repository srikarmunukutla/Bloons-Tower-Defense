import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JPanel;

public class TackShooter extends Monkey{

	public TackShooter(int a, int b) {
		super(a,b,200,"Tack_Shooter.png",8,1,150,1);
	}
	@Override
    public void target(ArrayList<Bloon> al, JPanel panel, HashMap<Integer, Projectile> gameprojectiles, long ticks){
        if (ticks % getReloadRate() != 0){
            return;
        }
        //If no balloons, no code to run
        if (al.size() == 0){
            return;
        }
        for (int i = 0; i < al.size(); i++) {
        	if (al.get(i).getRect().intersects(r)) {
        		
        		continue;
        	}
        }
    }

}
