public class DartMonkey extends Monkey {

    public DartMonkey(int a, int b){
        super(a,b,100,"Dart_Monkey.png",1,1,150,1,200);
<<<<<<< HEAD
=======
        width = 50;
        height = 50;
        setImgRect();
>>>>>>> f65744a21e1bb3f99d844f3f83ada52e6adeeef0
    }
    
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
}
