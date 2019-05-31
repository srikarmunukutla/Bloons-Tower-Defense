public class BoomerMonkey extends Monkey {
	private boolean ontowerpanel;
	
	public BoomerMonkey(int a, int b, boolean tp) {
    	super(a,b,200,"Glaive_Lord.png",1,1,400,5,100);
        hasrange = true;
        ontowerpanel = tp;
        if(ontowerpanel) {
        	width = 50;
            height = 56;
        }
        else {
        	width = 68;
        	height = 76;
        }
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