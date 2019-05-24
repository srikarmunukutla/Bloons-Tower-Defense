public class DartMonkey extends Monkey {

    public DartMonkey(int a, int b){
        super(a,b,100,"Dart_Monkey.png",1,1,225,1,200);
        width = 50;
        height = 50;
        hasrange = true;
        setImgRect();
    }
    
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
    
}
