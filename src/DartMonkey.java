public class DartMonkey extends Monkey {

    public DartMonkey(int a, int b){
        super(a,b,100,"Dart_Monkey.png",1,1,150,1,200);
    }
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
}
