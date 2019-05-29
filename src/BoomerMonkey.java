public class BoomerMonkey extends Monkey {
	
	public BoomerMonkey(int a, int b) {
    	super(a,b,200,"Boomer_Monkey.png",1,1,400,5,4500);
        width = 50;
        height = 72;
        hasrange = true;
        setImgRect();
    }

    int numboomer = 0;
	public void incboomer(){
	    numboomer++;
    }
    public void decboomer(){
        numboomer--;
    }
    public int getNumboomer(){
	    return numboomer;
    }
	
	@Override
    public Projectile getProj() {
        return new Boomerang(this.getX(),this.getY());
    }
}