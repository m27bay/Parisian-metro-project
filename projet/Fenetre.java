// awt
import java.awt.Color;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 */
public class Fenetre extends JFrame {
	
	/**
	 *
	 */
	public Fenetre()
	{
		this.setTitle( "Metro Lines" );
		int winWidth = 1395, wiwnHeight = 795;
		this.setSize( winWidth, wiwnHeight );
		this.setLocationRelativeTo( null );
		//this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setContentPane( new Panneau() );
		
		this.setVisible( true );
	}
}
