import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class TackShooter extends Monkey{
	private int secbeforereload = 0;
	public TackShooter(int a, int b) {
		super(a,b,150,"Tack_Shooter.png",8,1,150,1);
	}
	@Override
    public void target(ArrayList<Bloon> al, JPanel panel, HashMap<Integer, Projectile> gameprojectiles){
        //If no bloons, no code to run
		
        if (al.size() == 0){
            return;
        }
        if (secsbefreload > 0){
            secsbefreload--;
            return;
        }
        for (int i = 0; i < al.size(); i++) {
        	if (al.get(i).getRect().intersects(r)) {
        		if (secsbefreload == 0){
                    secsbefreload = getReloadRate();
                }
        		for (int j = 0; j < 8; j++) {
            		Projectile pr = getProj();
            		int random = (int) ((Math.random()) * Integer.MAX_VALUE);
                    gameprojectiles.put(random, pr);
                    double x = this.getX()+17*Math.cos(j*Math.PI/4);
                    double y = this.getY()-17*Math.sin(j*Math.PI/4);
                    gameprojectiles.get(random).launch((int) x,(int) y, panel,gameprojectiles,random, this.getDamage(), al, pierce, r);
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
