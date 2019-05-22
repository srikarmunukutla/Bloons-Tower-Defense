
public class RichardHanson extends Monkey {
	public RichardHanson(int a, int b) {
		super(a,b,100,"Hanson.png",1,1,50,32,1337);
	}
    @Override
    public Projectile getProj(){
        return new API(this.getX(),this.getY());
    }
}
