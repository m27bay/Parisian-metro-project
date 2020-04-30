// awt

import java.awt.*;

// For read the file
import java.io.*;
import java.util.Collections;

// swing
import javax.swing.JPanel;

// io

// imageio
import javax.imageio.ImageIO;

public class Panneau extends JPanel {
	
	/**
	 * Draw
	 *
	 * @param g the graphic
	 */
	public void paintComponent( Graphics g )
	{
		// Init graphic
		int police = 16;
		Font font = new Font( "Courier", Font.BOLD, police );
		g.setFont( font );
		g.setColor( Color.white );
		
		int winWidth = 800, winHeight = 800;
		int gap = 25;
		
		// Draw GUI
		drawGUI( g, police, font );
	}
	
	private void drawGUI( Graphics g, int police, Font font )
	{
		// Draw image
		int imgWeight = drawImage( g );
		
		// Draw travel
		drawTravel( g, police, imgWeight, font );
	}
	
	/**
	 * Draw background image
	 *
	 * @param g the graph
	 *
	 * @return image height
	 */
	private int drawImage( Graphics g )
	{
		// Image
		int img1Width = 295, img2Width = 1083;
		int imgHeight = 757;
		try
		{
			Image img = ImageIO.read( new File( "picture/Capture2.jpg" ) );
			Image img2 = ImageIO.read( new File( "picture/Plan.jpg" ) );
			//Pour une image de fond
			g.drawImage( img, 0, 0, img1Width, imgHeight, this );
			g.drawImage( img2, img1Width, 0, img2Width, imgHeight, this );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		return imgHeight;
	}
	
	/**
	 * Draw the travel in the window
	 *
	 * @param g         the graph
	 * @param police    the write size
	 * @param imgHeight the image heigth
	 * @param font      the font
	 */
	private void drawTravel( Graphics g, int police, int imgHeight, Font font )
	{
		//
		int dataSize = 0;
		String data[] = null;
		try
		{
			data = readUserData( "../UserTravel.txt" );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
		//
		for( String s : data )
		{
			if( s != null )
				dataSize++;
		}
		
		//
		g.setFont( new Font( "Courier", Font.BOLD, 15 ) );
		police = 13;
		int posStart = 310;
		int gab = police + police / 3;
		int numCharMax = 18;
		String decay = "                     "; //< DO NOT MODIFIED
		String metroName = null; String line = null;
		
		//
		metroName = data[ 0 ].substring( 0, data[ 0 ].indexOf( "line" ) - 1 );
		if( metroName.length() > numCharMax )
			metroName = metroName.substring( 0, numCharMax ).concat( "..." );
		g.drawString( metroName, 80, 96 );
		
		//
		metroName = data[ dataSize - 2 ].substring( 0, data[ dataSize - 2 ].indexOf( "line" ) - 1 );
		if( metroName.length() > numCharMax )
			metroName = data[ dataSize - 2 ].substring( 0, numCharMax ).concat( "..." );
		g.drawString( metroName, 80, 146 );
		
		// Reset
		g.setColor( Color.black );
		
		//
		metroName = data[ 1 ];
		metroName = metroName.substring( 0, metroName.indexOf( "line" ) - 1 );
		if( metroName.length() > numCharMax )
			metroName = data[ 1 ].substring( 0, numCharMax ).concat( "..." );
		g.drawString( "- Direction : " + metroName, 20, posStart );
		posStart += gab;
		line = data[ 1 ].substring( data[ 1 ].indexOf( "line" ), data[ 1 ].length());
		g.drawString( decay + line, 20, posStart );
		posStart += ( 2 * gab );
		
		//
		for( int i = 2 ; i < dataSize - 3 ; i += 2 )
		{
			if( data[ i ] != null )
			{
				//
				metroName = data[ i ].substring( 0, data[ i ].indexOf( "line" ) - 1 );
				if( metroName.length() > numCharMax )
					metroName = data[ i ].substring( 0, numCharMax ).concat( "..." );
				g.drawString( "- Switch at the station :", 20, posStart );
				posStart += gab;
				g.drawString( decay + metroName, 20, posStart );
				posStart += gab;
				
				//
				metroName = data[ i + 1 ];
				metroName = metroName.substring( 0, metroName.indexOf( "line" ) - 1 );
				if( metroName.length() > numCharMax )
					metroName = data[ i + 1 ].substring( 0, numCharMax ).concat( "..." );
				g.drawString( "- Direction : " + metroName, 20, posStart );
				posStart += gab;
				line = data[ i + 1 ].substring( data[ i + 1 ].indexOf( "line" ), data[ i + 1 ].length() );
				g.drawString( decay + line, 20, posStart );
				posStart += ( 2 * gab );
			}
		}
		
		//
		g.drawString( "Global time : " + data[ dataSize - 1 ], 20, imgHeight - ( 2 * gab ) );
	}
	
	/**
	 * Read in the data file
	 *
	 * @param userDataFile file with user information like start station, stop station, travel,...
	 *
	 * @return data String[]
	 *
	 * @throws IOException
	 */
	private String[] readUserData( String userDataFile ) throws IOException
	{
		// Variables
		BufferedReader read = null;
		String line;
		
		//
		String parts[] = new String[ 4 ];
		String data[] = new String[ 20 ];
		int dataPos = 0;
		
		// Init read
		try
		{
			read = new BufferedReader( new FileReader( userDataFile ) );
		}
		
		// Exception
		catch( FileNotFoundException exception )
		{
			System.out.println( "Error in class 'Panneau', method 'readUserData':" +
					" file not found" );
		}
		
		// Read
		while( ( line = read.readLine() ) != null )
		{
			// Skip header
			if( line.charAt( 0 ) == '#' ) continue;
				
				// Time
			else if( line.charAt( 0 ) == 'T' )
			{
				data[ dataPos ] = line.substring( 2, line.length() );
				dataPos++;
			}
			
			// read user data
			else
			{
				parts = line.split( " ", 4 );
				data[ dataPos ] = parts[ 1 ] + " " + parts[ 2 ] + " " + parts[ 3 ];
				dataPos++;
			}
		}
		
		// Close and exit
		read.close();
		
		return data;
	}
}
