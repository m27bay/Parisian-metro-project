// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

/**
  *
  */
public class TblStations {

  // Attribut(s)
  private int tblSize;
  private String dataFile;
  private int tbl[][];

  /**
    *
    */
  public TblStations( String dataFile ) throws IOException
  {
    //
    this.dataFile = dataFile;

    //
    this.tblSize  = stationCount() + 1;
    this.tbl = new int[ this.tblSize ][ this.tblSize ];
  }

  /**
    * Getter: Get the matrix
    * @return the matrix
    */
  public int[][] getTbl() { return this.tbl; }

  /**
    * Getter: Get the matrix size
    * @return the matrix size
    */
  public int getSize() { return this.tblSize; }

  /**
    * Count the nuumber ouf stations
    * @return the number of stations
    */
  private int stationCount() throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;
    int num_stations = 0;

    // Init 'read'
    try {
      read = new BufferedReader ( new FileReader ( this.dataFile ) );
    }

    // exception
    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Metro', method 'stationCount':"+
                         " file not found");
    }

    // read
    while( ( line = read.readLine() )  !=  null ) {
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' )
        num_stations++;
    }

    // Close & exit
    read.close();
    return num_stations;
  }


  /**
    * Fill the matrix with the travel time between stations
    *
    */
  public void fillTravelTime() throws IOException
  {
    // Variable(s)
    BufferedReader read = null;
    String line;

    // Init 'read'
    try
    {
      read = new BufferedReader ( new FileReader ( this.dataFile ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro', method 'fillTravelTime':"+
                         " file not found");
    }

    // init the matrix with '-1' everywhere
    for( int column = 0 ; column < this.tblSize ; column++ )
    {
      for( int i_line = 0 ; i_line < this.tblSize ; i_line++ )
      {
        // Distance between a station and herself
        if( column == i_line )
          this.tbl[ column ][ i_line ] = 0;
        else
          this.tbl[ column ][ i_line ] = -1;
      }
    }

    // Read file
    int start = 0, stop = 0;
    while( ( line = read.readLine() )  !=  null )
    {

      // Line with travel time begenning with a 'E'
      if( line.charAt(0)  ==  'E' )
      {

        // Fill the case [ start ][ stop ] with the travel time
        int stationStart = 0, stationStop = 0, travelTime = 0;

        int pos = 0, pos2 = 0;

        // Skip space and 'E'
        while( !Character.isDigit( line.charAt(pos2) ) ) pos2++;
        pos = pos2;

        // Read the first number
        while( Character.isDigit( line.charAt(pos2) ) ) pos2++;
        stationStart = Integer.parseInt( line.substring( pos, pos2 ) );
        pos = pos2+=1;

        // Read the second number
        while( Character.isDigit( line.charAt(pos2) ) ) pos2++;
        stationStop = Integer.parseInt( line.substring( pos, pos2 ) );
        pos = pos2+=1;

        // Read the last number
        travelTime = Integer.parseInt( line.substring( pos2, line.length() ) );

        // Fill the matrix with the travel time
        // This 'if' look travel in only one direction (like line 7b, 10, ...)
        if( stationStart  ==  34   &&  stationStop  == 248
        ||  stationStart  ==  248  &&  stationStop  == 280
        ||  stationStart  ==  280  &&  stationStop  == 92
        ||  stationStart  ==  92   &&  stationStop  == 34
        ||  stationStart  ==  36   &&  stationStop  == 198
        ||  stationStart  ==  198  &&  stationStop  == 52
        ||  stationStart  ==  52   &&  stationStop  == 201
        ||  stationStart  ==  201  &&  stationStop  == 145
        ||  stationStart  ==  145  &&  stationStop  == 373
        ||  stationStart  ==  373  &&  stationStop  == 196
        ||  stationStart  ==  196  &&  stationStop  == 259
        ||  stationStart  ==  259  &&  stationStop  == 36
        )
          this.tbl[ stationStart ][ stationStop ] = travelTime;
        else
        {
          this.tbl[ stationStart ][ stationStop ] = travelTime;
          this.tbl[ stationStop ][ stationStart ] = travelTime;
        }

      }
    }

    // Stop reading
    read.close();
    }

  /**
    * Print the matrix
    */
  public void print()
  {
    for( int column = 0 ; column < this.tblSize ; column++ )
    {
      for( int line = 0 ; line < this.tblSize ; line++ )
        System.out.print(" "+this.tbl[ column ][ line ]+" ");
      System.out.println();
    }
  }
}
