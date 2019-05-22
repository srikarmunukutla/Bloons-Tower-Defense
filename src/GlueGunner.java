public class GlueGunner extends Monkey {

public GlueGunner(int a,int b) {
	super(a,b,80,"Glue_Gunner.png",1,0,150,1,270);
}
	@Override
	public Projectile getProj() {
        return new GlueProjectile(this.getX(),this.getY());
    }
}