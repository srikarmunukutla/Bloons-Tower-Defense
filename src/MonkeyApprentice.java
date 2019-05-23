public class MonkeyApprentice extends Monkey {
	
    public MonkeyApprentice(int a, int b) {
        super(a,b,200,"Monkey_Apprentice.png",1,1,300,2,550);
        width = 50;
        height = 50;
        setImgRect();
    }
    
    @Override
    public Projectile getProj() {
        return new Magic(this.getX(),this.getY());
    }

}
