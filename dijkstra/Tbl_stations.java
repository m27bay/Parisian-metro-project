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
    this.tbl_size  = station_count( this.path_file );
    this.tbl       = new int[ this.tbl_size + 1 ][ this.tbl_size + 1 ];
  }

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
    * Fill the matrix with the station number
    *
    */
  public void fill_station() throws IOException {

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

    // Read file
    int i = 0;
    while( ( line = read.readLine() )  !=  null ) {

      // Line with num station begenning with a 'V' or 'T'
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' ) {

        // Fill the first line
        this.tbl[0][i] = Integer.parseInt( line.substring(2, 6) );
        i++;
      }
    }

    // Stop reading
    read.close();

    // fill the first column
    for( int column = 0 ; column < this.tbl_size ; column++ )
      this.tbl[ column ][0] = this.tbl[0][ column ];

    // fill the rest of the matrix with '-1'
    for( int column = 0 ; column < this.tbl_size ; column++ ) {
      for( int i_line = 1 ; i_line < this.tbl_size ; i_line++ )
        this.tbl[ column ][ i_line ] = -1;
    }
  }


  /**
    * Fill the matrix with the travel time between stations
    *
    */
  public void fill_travel_time() {

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

    // Read file
    int start = 0, stop = 0;
    while( ( line = read.readLine() )  !=  null ) {

      // Line with travel time begenning with a 'E'
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' ) {

        // Fill the case [ start ][ stop ] with the travel time
        this.tbl[0][i] = Integer.parseInt( line.substring(2, 6) );
        i++;
      }
    }

    // Stop reading
    read.close();
    }
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
