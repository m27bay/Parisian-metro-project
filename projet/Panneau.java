// awt

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

// For read the file
import java.io.*;

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
		Font font = new Font( "Courier", Font.BOLD, 20 );
		
		try
		{
			Image img = ImageIO.read( new File( "picture/plan.jpg" ) );
			//Pour une image de fond
			g.drawImage( img, 0, 0, 1014, 709, this );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
		g.setFont( font );
		g.setColor( Color.black );
		
		int winWidth = 1500;
		int winHeight = 750;
		
		int imgWidth = 1014;
		int imgHeight = 709;
		
		int widthStay = winWidth - imgWidth;
		int heightStay = winHeight - imgHeight;
		
		int numDraw = 1;
		int posDraw = 50;
		
		g.drawString( "Travel Plan", winWidth - widthStay + widthStay / 3, 17 );
		String data[] = null;
		
		try
		{
			data = readUserData( "UserTravel.txt" );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
		int dataSize = 0;
		for( String s : data )
		{
			if( s != null )
				dataSize++;
		}
		
		g.drawString( "Start station : " + data[ 0 ], 1050, posDraw * numDraw );
		numDraw++;
		g.drawString( "Direction : " + data[ 1 ], 1050, posDraw * numDraw );
		numDraw++;
		
		for( int i = 2 ; i < dataSize - 3 ; i++ )
		{
			if( data[ i ] != null )
			{
				g.drawString( "Switch line at the station : " + data[ i ], 1050, posDraw * numDraw );
				numDraw++;
				g.drawString( "Direction : " + data[ i + 1 ], 1050, posDraw * numDraw );
				numDraw++;
			}
		}
		
		g.drawString( "End station : " + data[ dataSize - 2 ], 1050, posDraw * numDraw );
		numDraw++;
		g.drawString( "Global time : " + data[ dataSize - 1 ], 1050, posDraw * numDraw );
		numDraw++;
		
		try
		{
			resetDataFile( "UserTravel.txt" );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
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
	
	/**
	 * Reset in data file
	 *
	 * @param UserTravelFile the file for write user information
	 *
	 * @throws IOException
	 */
	public void resetDataFile( String UserTravelFile ) throws IOException
	{
		FileWriter fileWriter = new FileWriter( UserTravelFile );
		
		PrintWriter printWriter = new PrintWriter( fileWriter );

		printWriter.print( "\n" );
		
		printWriter.close();
	}
}
