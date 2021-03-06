import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class SniperMonkey extends Monkey {
	public SniperMonkey(int a, int b) {
		super(a,b,200,"Sniper_Monkey.png",1,2,300,1,350);
		width = 50;
		height = 84;
		hasrange = false;
		setImgRect();
	}
	
	@Override
    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
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
        if (index >= al.size() || !(al.get(index) instanceof Bloon)){
            return;
        }
        Bloon b = (Bloon)al.get(index);
        if (b.getX() < getX()) {
            setAngle(180*Math.atan(1.0*(b.getY() - getY()) / (b.getX() - getX()))/Math.PI-90);
        } else {
            setAngle(180*Math.atan(1.0*(b.getY() - getY()) / (b.getX() - getX()))/Math.PI+90);
        }
        al.addAll(((Bloon)al.get(index)).hit((int) ((Bloon)al.get(index)).getX(), (int) ((Bloon)al.get(index)).getY(), getDamage(),m));
        al.remove(index);
        secsbefreload = getReloadRate();
    }
	
    @Override
    public Projectile getProj(){
        return null;
    }
}
