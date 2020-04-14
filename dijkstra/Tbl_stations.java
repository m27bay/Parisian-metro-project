// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// For write the file
import java.io.PrintWriter;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

public class Tbl_stations {

  // Attribut(s)
  private int tbl_size;
  private String path_file;
  private int tbl[][];

  // Builder(s)
  public Tbl_stations(String path_file) throws IOException{
    this.path_file = path_file;

    //
    this.tbl_size  = station_count( this.path_file ) + 1;
    this.tbl       = new int[ this.tbl_size ][ this.tbl_size ];
  }

  // Getter

  /**
    *
    *
    */
  public int[][] get_tbl() { return this.tbl; }

  /**
    *
    *
    */
  public int get_size() { return this.tbl_size; }

  // Method(s)

  /**
    *
    *
    */
  private int station_count(String file_name) throws IOException
  {
    BufferedReader read = null;
    String line;
    int num_stations = 0;

    try {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Tbl_station', method 'station_count':"+
                         " file not found");
    }

    while( ( line = read.readLine() )  !=  null ) {
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' )
        num_stations++;
    }

    read.close();
   return num_stations;
  }


  /**
    * Fill the matrix with the travel time between stations
    *
    */
  public void fill_travel_time() throws IOException {

    // Variable(s)
    BufferedReader read = null;
    String line;

    // Init 'read'
    try {
      read = new BufferedReader ( new FileReader ( this.path_file ) );
    }

    // Exception
    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Tbl_station', method 'fillTbl':"+
                         " file not found");
    }

    // init the matrix with '-1' everywhere
    for( int column = 0 ; column < this.tbl_size ; column++ ) {
      for( int i_line = 0 ; i_line < this.tbl_size ; i_line++ )

        // Distance between a station and herself
        if( column == i_line )
          this.tbl[ column ][ i_line ] = 0;
        else
          this.tbl[ column ][ i_line ] = -1;
    }

    // Read file
    int start = 0, stop = 0;
    while( ( line = read.readLine() )  !=  null ) {

      // Line with travel time begenning with a 'E'
      if( line.charAt(0)  ==  'E' ) {

        // Fill the case [ start ][ stop ] with the travel time
        int station_start = 0, station_stop = 0, travel_time = 0;

        int pos = 0, pos2 = 0;

        // Skip space and 'E'
        while( !Character.isDigit( line.charAt(pos2) ) ) pos2++;
        pos = pos2;

        // Read the first number
        while( Character.isDigit( line.charAt(pos2) ) ) pos2++;
        station_start = Integer.parseInt( line.substring( pos, pos2 ) );
        pos = pos2+=1;

        // Read the second number
        while( Character.isDigit( line.charAt(pos2) ) ) pos2++;
        station_stop = Integer.parseInt( line.substring( pos, pos2 ) );
        pos = pos2+=1;

        // Read the last number
        travel_time = Integer.parseInt( line.substring( pos2, line.length() ) );

        // Fill the matrix with the travel time
        this.tbl[ station_start ][ station_stop ] = travel_time;
        this.tbl[ station_stop ][ station_start ] = travel_time;

      }
    }

    // Stop reading
    read.close();
    }


  /**
    * Print the matrix
    *
    */
  public void print() {
    for( int column = 0 ; column < this.tbl_size ; column++ ) {
      for( int line = 0 ; line < this.tbl_size ; line++ )
        System.out.print(" "+this.tbl[ column ][ line ]+" ");
      System.out.println();
    }
  }


  /**
    * Write the matrix in the file 'out/output'
    *
    */
  public void write_file() throws IOException{

    // Variable(s)
    PrintWriter writer = null;

    // Init and creat the file
    try {
      writer = new PrintWriter("out/output.txt", "UTF-8");
    }

    // Exception
    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Tbl_station', method 'write_file':"+
                         " file not found");
    }

    // Write matrix
    for( int column = 0 ; column < this.tbl_size ; column++ ) {
      for( int line = 0 ; line < this.tbl_size ; line++ )
        writer.print(" "+this.tbl[ column ][ line ]+" ");
      writer.println();
    }

    // Stop writing
    writer.close();
  }
}
