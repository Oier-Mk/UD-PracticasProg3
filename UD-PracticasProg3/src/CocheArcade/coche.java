package CocheArcade;
import java.awt.Point;

public class coche extends JLabelGrafico{

	private static final long serialVersionUID = 1L;
	String path;
	double velocidad = 0.1;
	double rot2;

 

	public coche( int ancho, int alto ,Point location, double rotation, String image) {
		super(image,ancho,alto);
		setBounds((main.width-ancho)/2, (main.height-alto)/2, alto, ancho);
		setRot2(rotation);

	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad1) {
		if (velocidad1 <= 2 && velocidad1 >= -2){
			this.velocidad = velocidad1;		
		}
	}


	public void setRot2(double rot2) {
		if (rot2 < 0.0) {
			rot2 += 2*Math.PI;
		}
		if (rot2 > 2*Math.PI) {
			rot2 -= 2*Math.PI;
		}
		this.rot2 = rot2;
		setRotacion(rot2);
	}


	public String toString() {
		return  getX() + "," + getY() + " R " + this.getRotacion() ;
	}

}
