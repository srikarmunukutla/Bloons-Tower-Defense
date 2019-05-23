
public class RichardHanson extends Monkey {
	public RichardHanson(int a, int b) {
		super(a,b,200,"Hanson.png",1,1,50,32,1337);
		width = 50;
		height = 62;
		setImgRect();
	}
	
    @Override
    public Projectile getProj(){
        return new API(this.getX(),this.getY());
    }
}
