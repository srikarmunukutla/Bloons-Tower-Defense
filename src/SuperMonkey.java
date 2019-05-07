
public class SuperMonkey extends Monkey {
    public SuperMonkey(int a, int b){
        super(a,b,300,"Super_Monkey.png",1,1,10,1);
    }
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
}