// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

//
import java.util.ArrayList;

/**
  * Metro_line class
  */
public class Metro_line {

  // Attributs

  // Num metro line
  private String num;

  //
  private int num_stations, num_terminus;
  private Station[] tbl_terminus;
  private Station[] tbl_stations;

  //
  private ArrayList <Station> line;

  // builders
  public Metro_line(String file_name, String num) throws IOException
  {
    this.num = num;

    //
    this.num_stations  = 0;
    this.num_terminus  = 0;

    //
    this.tbl_stations = new Station[this.num_stations];;
    this.tbl_terminus = new Station[this.num_terminus];
  }

  /**
    * Count stations and terminus by metro line with duplicates
    * @param the name file
    */
  private void count(String file_name) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro_line', method 'station_count':"+
                         " file not found");
    }

    // Count
    while( ( line = read.readLine() )  !=  null )
    {
      // line which beginning by a 'V' or 'T'
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' )
      {

        // Check the metro line
        String str_num = line.substring(line.length() - 2, line.length());
        if( str_num.equals(this.num) )
          this.num_stations++;

        // Count terminus
        if( line.charAt(0)  ==  'T'  &&  str_num.equals(this.num) )
          this.num_terminus++;
      }
    }

    // Close and exit
    read.close();
  }

  /**
    * Fill the table with the station by metro line
    * @param the name file
    * @return the table fill
    */
  private void metro_init(String file_name) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro_line', method 'metro_init':"+
                         " file not found");
    }

    // Read
    int i = 0, j = 0;
    while( ( line = read.readLine() )  !=  null )
    {
      //
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' )
      {
        // Check the metro line
        String str_num = line.substring(line.length() - 2, line.length());
        if( str_num.equals( this.num ) ) {

          // Init station number
          String str_number = line.substring(2, 6);
          int number = Integer.parseInt(str_number);

          // Init name station
          String name = line.substring(7, line.length() - 3);

          // Fill stations
          this.tbl_stations[i] = new Station(name, number);
          i++;

          // Fill terminus
          if( line.charAt(0)  ==  'V' )
          {
            this.tbl_terminus[j] = new Station(name, number);
            j++;
          }
        }
      }
    }

    // Close and exit
    read.close();
  }

  /**
    * Print terminus table
    */
  public void terminus_print() {
    for( Station s: this.tbl_terminus )
      System.out.println(s.toString());
  }

  /**
    * Print all attributs
    */
  public void metro_print() {
    System.out.println("Station n°: "+this.num);
    System.out.println("Numbers of stations inside: "+this.num_stations+"\n");
    System.out.println("All stations:");

    if( this.num_stations  ==  0 )
      System.out.println("Empty");
    else {
      int j = 0;
      for( Station s: this.tbl_stations ) {
        if( ( j <  this.num_terminus ) &&
          ( s.get_name() ).equals( tbl_terminus[j].get_name() ) ) {
          System.out.print("terminus ");
          j++;
        }
        System.out.println(s.toString());
      }
    }
  }
}
