package CocheArcade;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class cocheArcade extends JFrame {
	public static final long serialVersionUID = 1L;
	public static JPanel canvas;
	public static JPanel ventana;
	public static JPanel botones;
	public static coche car;
 

	public cocheArcade(int width, int height, coche c) {

		//TAMAÑOS DE VENTANA
		car = c;
		setFocusable(true);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );   
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize( width, height); 
		setLocation( screenSize.width/2 - width/2, screenSize.height/2 - height/2);  

		//VENTANA PRINCIPAL
		ventana = new JPanel();
		ventana.setLayout(new BorderLayout(3,3));
		add(ventana);


		//PANEL DE JUEGO
		canvas = new JPanel();
		canvas.setLayout(null);
		canvas.add(car);

		//PANEL DE BOTONES
		botones = new JPanel();

		JButton acelera  = new JButton( "Acelera" );		
		JButton frena = new JButton( "Frena" );
		JButton derecha = new JButton( "Derecha" );
		JButton izquierda = new JButton( "Izquierda" );

		botones.add(acelera);
		botones.add(frena);	
		botones.add(izquierda);
		botones.add(derecha);

		//ACTION LISTENERS

		acelera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acelera();
				repaint();
			}


		});

		frena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frena();
				repaint();
			}


		});

		derecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				derecha();
				repaint();
			}
		});

		izquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izquierda();
				repaint();
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();

				if (key == 37) {
					derecha();
					repaint();	
				}

				if (key == 39) {
					izquierda();
					repaint();
				}

				if (key == 38) {
					acelera();
					repaint();
				}

				if (key == 40) {
					frena();
					repaint();
				}
			}
		});
		
		//TODO CAR.GETX() Y CAR.GETY() SON INT Y HAN DE SER DOUBLES
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				(new Thread() {
					public void run() {
						while(true) {
							double angulo = car.getRotacion();
							double velocidad = car.getVelocidad();
							System.out.println("A: "+angulo +" V: "+velocidad);
							double incX = velocidad*Math.cos(angulo);
							double incY = velocidad*Math.sin(angulo);
							System.out.println("incX: "+incX+" incY "+incY);
							System.out.println("Antes X: "+car.getX()+" Y: "+car.getY());
							double x = car.getX() + incX;
							double y = car.getY() + incY;
							System.out.println("Despues X: "+x+" Y: "+y);
							car.setLocation(x,y);
							car.repaint();
							choca();
							try{Thread.sleep(10);}catch(Exception ex){} 
						}
					}
				}).start();
			}
		});

		//MONTAJE
		ventana.add(canvas, BorderLayout.CENTER);
		ventana.add(botones, BorderLayout.SOUTH);
	}


	
	private static void choca() {
		if (car.getX() >= canvas.getWidth() || car.getY() >= canvas.getHeight() || car.getX() <= 0 || car.getY() <= 0) {
			car.setRot2(car.getRotacion()+Math.PI);
			System.out.println("choque");
		}
	}

	private void acelera() {
		car.setVelocidad(car.getVelocidad()+0.1);	
	}
	
	private void frena() {
		car.setVelocidad(car.getVelocidad()-0.1);	
	}

	private static void izquierda() {
		car.setRot2(car.getRotacion()-0.1);
	}

	private static void derecha() {
		car.setRot2(car.getRotacion()+0.1);
	}
}
