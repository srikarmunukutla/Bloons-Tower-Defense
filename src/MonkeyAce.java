import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class MonkeyAce extends Monkey {
	int radius = 100;
	double angle = 0, omega = 36, x, y;
	public MonkeyAce(int a, int b) {
		super(a,b,100,"Monkey_Ace.png",8,1,200,1);
		x = a+radius;
		y = b;
	}
	@Override
    public void update(ArrayList<GameObject> al, double timepassed, JPanel panel, HashMap<Integer,Projectile> gameprojectile){
		//runs regardless if there are bloons
		angle += omega * timepassed;
		setAngle(angle);
		x = getX()+radius*Math.cos(angle*Math.PI/180);
		y = getY()-radius*Math.sin(angle*Math.PI/180);
        if (secsbefreload > 0){
            secsbefreload--;
            return;
        }
        secsbefreload = getReloadRate();
        for (int i = 0; i < 8; i++) {
        	Projectile pr = getProj();
        	int random = (int) ((Math.random()) * Integer.MAX_VALUE);
        	gameprojectile.put(random, pr);
        	int BIGINT = 3780;
        	double x = this.x+BIGINT*Math.cos(i*Math.PI/4+angle);
            double y = this.y-BIGINT*Math.sin(i*Math.PI/4+angle);
            gameprojectile.get(random).launch((int) x,(int) y, panel,gameprojectile,random, this.getDamage(), al, pierce, r);
        }
    }
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
}
