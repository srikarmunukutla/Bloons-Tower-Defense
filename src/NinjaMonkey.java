
public class NinjaMonkey extends Monkey {
	public NinjaMonkey(int a, int b) {
		super(a,b,200,"Ninja_Monkey.png",1,1,50,2);
	}
    @Override
    public Projectile getProj(){
        return new Shuriken(this.getX(),this.getY());
    }
}
