package CocheArcade;
import java.awt.Point;

public class main {
	
	public static int width = 900;
	public static int height = 600;
	public static double size = 0.5;
	public static int carX = (int)(300*size);
	public static int carY = (int)(300*size);

	public static void main( String args[] ) {
		coche vehiculo = new coche(carX, carY, new Point((width - carX)/2, (height - carY)/2),0, "src/CocheArcade/coche.png");
		cocheArcade c = new cocheArcade(width,height,vehiculo);
		c.setVisible(true);
	}
	
 
}
