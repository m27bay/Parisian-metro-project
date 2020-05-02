// For read the file

import java.io.BufferedReader;
import java.io.FileReader;

// WFor write in the file
import java.io.FileWriter;
import java.io.PrintWriter;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

// Tools
import java.util.ArrayList;
import java.util.LinkedList;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class Metro {
	
	// Attributes
	private final MetroLine[] metro;
	private final String dataFile;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Builder
	 */
	public Metro( String dataFile )
	{
		this.dataFile = dataFile;
		this.metro = new MetroLine[ 16 ];
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Found in MetroLine table the station corresponding to 'numStation'
	 *
	 * @param numStation station number need to found
	 *
	 * @return Station
	 */
	private Station whatStation( int numStation )
	{
		Station found = new Station();
		
		for( MetroLine m : metro )
		{
			for( Station s : m.getListStation() )
			{
				if( s.getNumber() == numStation )
					found = s.copy();
			}
		}
		
		return found;
	}
	
	/**
	 * convert name station to his number
	 *
	 * @param nameStation the name station
	 *
	 * @return
	 */
	public int convertNameToNumStation( String nameStation )
	{
		int found = -1;
		
		for( MetroLine m : metro )
		{
			for( Station s : m.getListStation() )
			{
				if( s.getName().equals( nameStation ) )
					found = s.getNumber();
			}
		}
		
		return found;
	}
	
	/**
	 * Found in the MetroLine table the line corresponding to 'numStation'
	 *
	 * @param numStation number station need to found
	 *
	 * @return Line
	 */
	private String whatMetroLine( int numStation )
	{
		String found = "Unknown";
		
		for( MetroLine m : metro )
		{
			for( Station s : m.getListStation() )
			{
				if( s.getNumber() == numStation )
					found = m.getName();
			}
		}
		
		return found;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Convert the line name to this position in the MetroLine table
	 *
	 * @param line the metro line need to convert
	 *
	 * @return position in MetroLine table
	 */
	private int strLineToInt( String line )
	{
		int _line = -1;
		if( line.equals( "3b" ) )
		{
			_line = 3;
		}
		else if( line.equals( "7b" ) )
		{
			_line = 8;
		}
		else
		{
			try
			{
				_line = Integer.parseInt( line );
				// "01" "02" "03"
				if( _line <= 3 )
				{
					_line -= 1;
				}
				else if( _line > 8 )
				{
					_line += 1;
				}
			}
			catch( NumberFormatException exception )
			{
				exception.getMessage();
			}
		}
		
		return _line;
	}
	
	/**
	 * Convert the position in the MetroLine table to his line name
	 *
	 * @param line the metro line need to convert
	 *
	 * @return position in MetroLine table
	 */
	private String intToStrLine( int line )
	{
		String _line = "unknown";
		
		if( line == 3 )
		{
			_line = "3b";
		}
		else if( line == 8 )
		{
			_line = "7b";
		}
		else
		{
			if( line < 3 )
			{
				_line = "0" + String.valueOf( line + 1 );
			}
			else if( line < 8 )
			{
				_line = "0" + String.valueOf( line );
			}
			else
			{
				_line = String.valueOf( line - 1 );
			}
		}
		
		return _line;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Call all init functions
	 *
	 * @throws IOException
	 */
	public void initLine() throws IOException
	{
		initLineStation();
		initLineTravel();
		
		sortTravel();
	}
	
	/**
	 * Read the number station and station number in the dataFile
	 *
	 * @throws IOException
	 */
	public void initLineStation() throws IOException
	{
		// Variables
		BufferedReader read = null;
		String line;
		
		// Init MetroLine table
		for( int i = 0 ; i < 16 ; i++ )
			this.metro[ i ] = new MetroLine();
		
		// Init read
		try
		{
			read = new BufferedReader( new FileReader( this.dataFile ) );
		}
		
		// Exception
		catch( FileNotFoundException exception )
		{
			System.out.println( "Error in class 'Metro', method 'initLineStation':" +
					" file not found" );
		}
		
		// Read
		while( ( line = read.readLine() ) != null )
		{
			// Skip header
			if( line.charAt( 0 ) == '#' ) continue;
				
				// Skip travels
			else if( line.charAt( 0 ) == 'E' ) continue;
				
				// read stations
			else if( line.charAt( 0 ) == 'V' || line.charAt( 0 ) == 'T' )
			{
				// Check the metro line
				String strLine = line.substring( line.length() - 2, line.length() );
				
				// Init station number
				String strNumber = line.substring( 2, 6 );
				int number = Integer.parseInt( strNumber );
				
				// Init name station
				String name = line.substring( 7, line.length() - 3 );
				
				// Init terminus
				Station _new;
				if( line.charAt( 0 ) == 'T' )
					_new = new Station( name, number, true );
				else
					_new = new Station( name, number, false );
				
				// Fill
				this.metro[ strLineToInt( strLine ) ].addStation( _new );
			}
		}
		
		// Close and exit
		read.close();
		
		// Init MetroLine name
		this.metro[ 0 ].setName( "01" );
		this.metro[ 1 ].setName( "02" );
		this.metro[ 2 ].setName( "03" );
		this.metro[ 3 ].setName( "3b" );
		this.metro[ 4 ].setName( "04" );
		this.metro[ 5 ].setName( "05" );
		this.metro[ 6 ].setName( "06" );
		this.metro[ 7 ].setName( "07" );
		this.metro[ 8 ].setName( "7b" );
		this.metro[ 9 ].setName( "08" );
		this.metro[ 10 ].setName( "09" );
		this.metro[ 11 ].setName( "10" );
		this.metro[ 12 ].setName( "11" );
		this.metro[ 13 ].setName( "12" );
		this.metro[ 14 ].setName( "13" );
		this.metro[ 15 ].setName( "14" );
	}
	
	/**
	 * Init Travel
	 *
	 * @throws IOException
	 */
	public void initLineTravel() throws IOException
	{
		// Variables
		BufferedReader read = null;
		String line;
		String parts[] = new String[ 4 ];
		
		// Init read
		try
		{
			read = new BufferedReader( new FileReader( this.dataFile ) );
		}
		
		// Exception
		catch( FileNotFoundException exception )
		{
			System.out.println( "Error in class 'Metro', method 'initLineTravel':" +
					" file not found" );
		}
		
		// Read
		while( ( line = read.readLine() ) != null )
		{
			//
			if( line.charAt( 0 ) == '#'
					|| line.charAt( 0 ) == 'V'
					|| line.charAt( 0 ) == 'T' )
				continue;
				
				//
			else
			{
				// Spilt line with the space
				parts = line.split( " ", 4 );
				
				// Init Travel
				Travel t = new Travel();
				
				int stationStart = Integer.parseInt( parts[ 1 ] );
				int stationStop = Integer.parseInt( parts[ 2 ] );
				int travelTime = Integer.parseInt( parts[ 3 ] );
				
				// Set station start
				t.setStationStart( whatStation( stationStart ) );
				t.setStationStop( whatStation( stationStop ) );
				t.setTime( travelTime );
				
				// Set MetroLine name
				String lineStart = whatMetroLine( stationStart );
				String lineStop = whatMetroLine( stationStop );
				
				// Add travel except liaison
				if( lineStart.equals( lineStop ) )
					this.metro[ strLineToInt( lineStart ) ].addTravel( t );
			}
		}
		
		// Close and exit
		read.close();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Sort travel
	 */
	public void sortTravel()
	{
		for( int i = 0 ; i < 16 ; i++ )
			sortIndexTravel( i );
	}
	
	/**
	 * Sort list travel if the line is a right line
	 *
	 * @param index the line number
	 */
	public void sortIndexTravel( int index )
	{
		// Check if is a right line
		if( index != 7 && index != 11 && index != 14 && index != 8 )
		{
			// Init
			LinkedList < Travel > _new;
			ArrayList < Travel > list;
			int count = 0;
			
			_new = new LinkedList < Travel >();
			list = this.metro[ index ].getListTravel();
			
			// While we haven't see every elements
			while( count < list.size() )
			{
				for( Travel t : list )
				{
					// If linkedList is empty we add the travel at a random place
					if( _new.size() == 0 )
					{
						_new.add( t );
						count++;
					}
					
					// If linkedList have one element
					else if( _new.size() == 1 )
					{
						// If element ins't in the linkedList
						if( t.equals( _new.peek() )
								|| t.switchStation().equals( _new.peek() ) )
							continue;
						
						// We have : travel = (station1, go to, station2)
						//  	  _new.peek = (station1, go to, station3)
						if( t.getNumStationStart() == _new.peek().getNumStationStart() )
						{
							// Add first and switch for have :
							// 		_new.peek = (station3, go to, station1)
							// 		   travel = (station1, go to, station2)
							_new.addFirst( t.switchStation() );
							count++;
						}
						
						// We have : travel = (station2, go to, station1)
						// 		  _new.peek = (station1, go to, station3)
						else if( t.getNumStationStop() == _new.peek().getNumStationStart() )
						{
							// Add first for have :
							// 		_new.peek = (station3, go to, station1)
							// 		   travel = (station1, go to, station2)
							_new.addFirst( t );
							count++;
						}
						
						// We have : travel = (station2, go to, station1)
						// 		  _new.peek = (station3, go to, station1)
						else if( t.getNumStationStop() == _new.peek().getNumStationStop() )
						{
							// Add (last) and switch for have :
							// 		   travel = (station2, go to, station1)
							// 		_new.peek = (station1, go to, station3)
							_new.add( t.switchStation() );
							count++;
						}
						
						// We have : travel = (station1, go to, station2)
						// 		  _new.peek = (station3, go to, station1)
						else if( t.getNumStationStart() == _new.peek().getNumStationStop() )
						{
							// Add (last) for have :
							// 		   travel = (station1, go to, station2)
							// 		_new.peek = (station1, go to, station3)
							_new.add( t );
							count++;
						}
					}
					
					// Same method but with a bigger linkedList
					else
					{
						//
						if( t.equals( _new.peek() ) || t.equals( _new.peekLast() )
								|| t.switchStation().equals( _new.peek() )
								|| t.switchStation().equals( _new.peekLast() ) )
							continue;
						
						//
						if( t.getNumStationStart() == _new.peek().getNumStationStart() )
						{
							_new.addFirst( t.switchStation() );
							count++;
						}
						
						//
						else if( t.getNumStationStop() == _new.peek().getNumStationStart() )
						{
							_new.addFirst( t );
							count++;
						}
						
						//
						else if( t.getNumStationStop() == _new.peekLast().getNumStationStop() )
						{
							_new.add( t.switchStation() );
							count++;
						}
						
						//
						else if( t.getNumStationStart() == _new.peekLast().getNumStationStop() )
						{
							_new.add( t );
							count++;
						}
					}
				}
			}
			
			// Change old list with new new sort list
			this.metro[ index ].setListTravel( new ArrayList < Travel >( _new ) );
			
			// CLear all list
			_new.clear();
			list.clear();
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Found direction
	 *
	 * @param way       dijkstra table
	 * @param pos       position of line switch
	 * @param numLine   line number
	 * @param wayLength size of dijkstra table
	 *
	 * @throws IOException
	 */
	public String knowDirection( int[] way, int pos, int numLine, int wayLength ) throws IOException
	{
		//
		int numStationStart = way[ pos ];
		int numStationStop = way[ pos + 1 ];
		
		//
		String direction;
		
		// Metro line don't right
		int line07 = strLineToInt( "07" );
		int line7b = strLineToInt( "7b" );
		int line10 = strLineToInt( "10" );
		int line13 = strLineToInt( "13" );
		
		// Fork line
		if( numLine == line07
				|| numLine == line10
				|| numLine == line13
				|| numLine == line7b )
			direction = knowDirectionFork( way, pos, wayLength );
			
			// Right line
		else
			direction = knowDirectionRight( numStationStart, numStationStop, numLine );
		
		// Return
		return direction;
	}
	
	/**
	 * Print direction for right metro line
	 *
	 * @param numStationStart number of station start
	 * @param numStationStop  number of station stop
	 * @param numLine         line number
	 */
	public String knowDirectionRight( int numStationStart, int numStationStop, int numLine )
	{
		// get the travel list
		ArrayList < Travel > tmp = this.metro[ numLine ].getListTravel();
		int direction = 0;
		
		// Course in the travel list
		for( Travel t : tmp )
		{
			if( t.getStationStart().equals( whatStation( numStationStart ) )
					&& t.getStationStop().equals( whatStation( numStationStop ) ) )
			{
				direction = tmp.size() - 1;
				break;
			}
		}
		
		// Print direction
		if( direction == 0 )
			return tmp.get( direction ).getStationStart().getName() + " line " + intToStrLine( numLine );
		else
			return tmp.get( direction ).getStationStop().getName() + " line " + intToStrLine( numLine );
	}
	
	/**
	 * Return a String table with all travel by metro line name
	 *
	 * @param metroLine the metro line name
	 *
	 * @return String table with all travel
	 *
	 * @throws IOException
	 */
	private String[] fillTbl( String metroLine ) throws IOException
	{
		// Variables
		BufferedReader read = null;
		String line;
		String[] tbl = new String[ 50 ];
		String[] parts = new String[ 3 ];
		int count = 0;
		
		// Init read
		try
		{
			read = new BufferedReader( new FileReader( this.dataFile ) );
		}
		
		// Exception
		catch( FileNotFoundException exception )
		{
			System.out.println( "Error in class 'Metro', method 'fillTbl':" +
					" file not found" );
		}
		
		// Read
		while( ( line = read.readLine() ) != null )
		{
			//
			if( line.charAt( 0 ) == '#'
					|| line.charAt( 0 ) == 'V'
					|| line.charAt( 0 ) == 'T' )
				continue;
				
				//
			else
			{
				// Spilt line with the space
				parts = line.split( " ", 4 );
				
				// Init Travel
				Travel t = new Travel();
				
				int stationStart = Integer.parseInt( parts[ 1 ] );
				int stationStop = Integer.parseInt( parts[ 2 ] );
				
				// Skip travel with two stations in different metro lined
				if( !whatMetroLine( stationStart ).equals( metroLine )
						|| !whatMetroLine( stationStop ).equals( metroLine ) )
					continue;
				
				// Fill the table
				tbl[ count ] = line;
				
				// Count number of travel
				count++;
			}
		}
		
		// Close and exit
		read.close();
		
		// Create new table
		String[] tbl2 = new String[ count ];
		
		//
		int count2 = 0;
		
		// Add travel in the new table except the empty element
		for( int i = 0 ; i < count ; i++ )
		{
			tbl2[ count2 ] = tbl[ i ];
			count2++;
		}
		
		return tbl2;
	}
	
	/**
	 * Algo know direction with a fork
	 *
	 * @param way       the dijkstra table
	 * @param pos       the position in the dijkstra table
	 * @param wayLength the length of dijkstra table
	 *
	 * @throws IOException
	 */
	public String knowDirectionFork( int way[], int pos, int wayLength ) throws IOException
	{
		// Skip station in dijkstra table if two stations is in the same metro line
		while( whatMetroLine( way[ pos ] ).equals( whatMetroLine( way[ pos + 1 ] ) ) && pos + 1 < wayLength - 1 ) pos++;
		
		// if while break because two station isn't in the same metro line
		if( !whatMetroLine( way[ pos ] ).equals( whatMetroLine( way[ pos + 1 ] ) ) )
			pos--;
		
		// Get all travel
		String[] tbl = fillTbl( whatMetroLine( way[ pos ] ) );
		
		// Don't know direction
		int direction = -1;
		
		//
		int stop, lastSeen = 0;
		lastSeen = way[ pos ];
		stop = way[ pos + 1 ];
		String parts[] = new String[ 4 ];
		
		//
		while( direction == -1 )
		{
			// Course in the table
			for( int i = 0 ; i < tbl.length ; i++ )
			{
				String s = tbl[ i ];
				
				parts = s.split( " ", 4 );
				
				int stationStart = Integer.parseInt( parts[ 1 ] );
				int stationStop = Integer.parseInt( parts[ 2 ] );
				
				// If we have found a station which is a terminus
				if( whatStation( lastSeen ).getIsTerminus() )
					direction = lastSeen;
				else if( whatStation( stop ).getIsTerminus() )
					direction = stop;
					
					// Else check if we have a travel for course in the metro line
				else
				{
					if( stop == stationStart && lastSeen != stationStop )
					{
						lastSeen = stop;
						stop = stationStop;
					}
					else if( stop == stationStop && lastSeen != stationStart )
					{
						lastSeen = stop;
						stop = stationStart;
					}
				}
			}
		}
		
		// print direction
		return whatStation( direction ).getName() + " line " + whatMetroLine( direction );
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Print travel
	 *
	 * @param way       the dijkstra table
	 * @param wayLength the length of dijkstra table
	 * @param time      the travel time formatted (XXhXXminXXsec)
	 *
	 * @throws IOException
	 */
	public void printTravelDetail( int way[], int wayLength, String time ) throws IOException
	{
		// Position in the dijkstra table
		int i = 0;
		
		// Data for write to user
		String[] data = new String[ 20 ];
		int posData = 0;
		
		// If selection a station for start travel and isn't in the good line
		if( whatStation( way[ 0 ] ).getName().equals( whatStation( way[ 1 ] ).getName() ) )
			i = 1;
		
		// Print direction and the start station
		String lineStart = whatMetroLine( way[ i ] );
		System.out.println( "You start at\n" + whatStation( way[ i ] ).toString()
				+ " line " + lineStart );
		data[ posData ] = "S " + whatStation( way[ i ] ).getName() + " line " + lineStart + "\n";
		posData++;
		data[ posData ] = "D " + knowDirection( way, i, strLineToInt( lineStart ), wayLength ) + "\n";
		posData++;
		i++;
		
		// 
		lineStart = whatMetroLine( way[ i ] );
		String lineNow = "Unknown";
		Station stationNow;
		
		// print global travel
		for( ; i < wayLength - 1 ; i++ )
		{
			//
			lineNow = whatMetroLine( way[ i ] );
			stationNow = whatStation( way[ i ] );
			
			//
			if( !lineStart.equals( lineNow ) )
			{
				System.out.println( "\nAt the station\n" + stationNow.toString() +
						".\nYou switch to the line: " + lineNow );
				
				//
				data[ posData ] = "C " + stationNow.getName() + " line " + lineNow + "\n";
				posData++;
				
				//
				String direction = knowDirection( way, i, strLineToInt( lineNow ), wayLength );
				System.out.println( "Direction : " + direction );
				data[ posData ] = "D " + direction + "\n";
				posData++;
				
				//
				lineStart = lineNow;
			}
			
			// print all stations
			// System.out.println(stationNow.toString()+" line "+lineNow );
		}
		System.out.println();
		
		// If selection a station for start travel and isn't in the good line
		if( whatStation( way[ i - 1 ] ).getName().equals( whatStation( way[ i ] ).getName() ) )
			i--;
		
		// Print end of travel
		String lineStop = whatMetroLine( way[ i ] );
		System.out.println( "You stop at\n" + whatStation( way[ i ] ).toString()
				+ " line " + lineStop + "\n" );
		
		System.out.println( "Time: " + time );
		
		//
		data[ posData ] = "E " + whatStation( way[ i ] ).getName() + " line " + lineStop + "\n";
		posData++;
		data[ posData ] = "T " + time + "\n";
		posData++;
		
		writeDataFile( "../UserTravel.txt", data );
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Print all Stations
	 */
	public void printStation()
	{
		for( MetroLine m : metro )
		{
			System.out.println( "//////////////////////////////\n" +
					"////////// name: " + m.getName() + " //////////\n" +
					"//////////////////////////////\n" );
			m.printStation();
		}
	}
	
	/**
	 * Print Station at index
	 */
	public void printIndexStation( String index )
	{
		MetroLine m = this.metro[ strLineToInt( index ) ];
		System.out.println( "//////////////////////////////\n" +
				"////////// name: " + m.getName() + " //////////\n" +
				"//////////////////////////////\n" );
		m.printStation();
		System.out.println();
	}
	
	/**
	 * Print all Travel
	 */
	public void printTravel()
	{
		for( MetroLine m : metro )
		{
			System.out.println( "//////////////////////////////\n" +
					"////////// name: " + m.getName() + " //////////\n" +
					"//////////////////////////////\n" );
			m.printTravel();
		}
	}
	
	/**
	 * Print travel at index
	 */
	public void printIndexTravel( String index )
	{
		MetroLine m = this.metro[ strLineToInt( index ) ];
		System.out.println( "//////////////////////////////\n" +
				"////////// name: " + m.getName() + " //////////\n" +
				"//////////////////////////////\n" );
		m.printTravel();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Write in data file
	 *
	 * @param UserTravelFile the file for write user information
	 *
	 * @throws IOException
	 */
	public void writeDataFile( String UserTravelFile, String[] data ) throws IOException
	{
		FileWriter fileWriter = new FileWriter( UserTravelFile );
		
		PrintWriter printWriter = new PrintWriter( fileWriter );
		
		for( String s : data )
		{
			if( s != null )
				printWriter.print( s );
		}
		
		printWriter.close();
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////