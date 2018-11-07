package farming;

public class mainFile {

    public static void main (String[] args) {
    	//Singleton a = new Singleton();
    	//Testing push
    	Fscreen.createFscreen();
    	//Singleton is implemented because it does not create a new one
    	Fscreen.createFscreen();
    }

}
