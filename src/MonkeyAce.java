
public class MonkeyAce extends Monkey {
	int speed = 12;
	public MonkeyAce(int a, int b) {
		super(a,b,100,"Monkey_Ace.png",8,1,200,1);
	}
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
}
