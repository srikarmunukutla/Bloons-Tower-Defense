public class BoomerMonkey extends Monkey {
	
	public BoomerMonkey(int a, int b) {
    	super(a,b,200,"Boomer.png",1,1,300,7,380);
        width = 50;
        height = 50;
        hasrange = true;
        setImgRect();
    }
	
	@Override
    public Projectile getProj() {
        return new Boomerang(this.getX(),this.getY());
    }
}