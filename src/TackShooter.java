import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class TackShooter extends Monkey{
	private int secbeforereload = 0;
	public TackShooter(int a, int b) {
		super(a,b,150,"Tack_Shooter.png",8,1,250,1,360);
		width = 50;
		height = 50;
		hasrange = true;
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
        for (int i = 0; i < bloonal.size(); i++) {
        	if (bloonal.get(i).getRect().intersects(rangerect)) {
        		if (secsbefreload == 0){
                    secsbefreload = getReloadRate();
                }
        		for (int j = 0; j < 8; j++) {
            		Projectile pr = getProj();
            		int random = (int) ((Math.random()) * Integer.MAX_VALUE);
                    gameprojectile.put(random, pr);
                    double x = this.getX()+17*Math.cos(j*Math.PI/4);
                    double y = this.getY()-17*Math.sin(j*Math.PI/4);
                    gameprojectile.get(random).launch((int) x,(int) y, panel,gameprojectile,random, this.getDamage(), al, pierce, rangerect);
        		}
        		break;
        	}
        }
    }
	
    @Override
    public Projectile getProj(){
        return new Tack(this.getX(),this.getY());
    }
}
