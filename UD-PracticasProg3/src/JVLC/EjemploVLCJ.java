package JVLC;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;


@SuppressWarnings("serial")
public class EjemploVLCJ extends JFrame {
	@SuppressWarnings("unused")
	private static final long setrialVersionUID = 1L;
	
	private static EjemploVLCJ miVentana;
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private JProgressBar pbReproduccion;
	public EjemploVLCJ() {
		setTitle("Prueba vlcj");
		setSize(800, 600);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		add( mediaPlayerComponent, BorderLayout.CENTER );
		JPanel pBotonera = new JPanel();
		JButton bPlayPausa = new JButton( "Play/Pausa" );
		pBotonera.add( bPlayPausa );
		pbReproduccion = new JProgressBar( 0, 1000 );
		pbReproduccion.setPreferredSize( new Dimension( 200, 20 ) );
		pBotonera.add( pbReproduccion );
		add( pBotonera, BorderLayout.SOUTH );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.mediaPlayer().controls().stop();
				mediaPlayerComponent.mediaPlayer().release();
			}
		});
		bPlayPausa.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mediaPlayerComponent.mediaPlayer().status().isPlaying())
					mediaPlayerComponent.mediaPlayer().controls().pause();
				else
					mediaPlayerComponent.mediaPlayer().controls().play();
			}
		});
		mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener( new MediaPlayerEventAdapter() {
			@Override
			public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
				pbReproduccion.setValue( (int) (1000L * newTime / mediaPlayer.status().length()) );
			}
		});
		pbReproduccion.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mediaPlayerComponent.mediaPlayer().controls().setTime( 
					mediaPlayerComponent.mediaPlayer().status().length() * e.getX() / pbReproduccion.getWidth() );
			}
		});
	}

	private void lanza(String mrl) {
		mediaPlayerComponent.mediaPlayer().audio().setVolume( 100 );
		mediaPlayerComponent.mediaPlayer().media().play(mrl);
	}

	public static void main(String[] args) {
		boolean found = (new NativeDiscovery()).discover();
    	if (!found) System.setProperty("jna.library.path", "C:\\Program Files (x86)\\VideoLAN\\VLC");
		miVentana = new EjemploVLCJ();
		miVentana.lanza(
				"/src/EjerciciosDeClase/netflixLogo.mp4"
				);
	}
	
}
