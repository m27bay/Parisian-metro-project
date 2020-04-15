// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
  * Metro_line class
  */
public class Metro_line {

  // Attributs
  private int num_stations, num_terminus;
  private String num;

  //
  private Station[] tbl_terminus;
  private Station[] tbl_stations;

  //
  private ArrayList <Station> line;

  // builders
  public Metro_line(String file_name, String num) throws IOException
  {
    this.num = num;

    //
    this.num_stations  = station_count(file_name);
    this.num_terminus  = terminus_count(file_name);

    //
    this.tbl_stations  = metro_init(file_name);
    this.tbl_terminus  = terminus_init(file_name);
  }

  /**
    * Count stations by metro line with duplicates
    * @param the name file
    * @return the number station
    */
  private int station_count(String file_name) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;
    int num_stations = 0;

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
          num_stations += 1;
      }
    }

    // Close and exit
    read.close();
    return num_stations;
  }

  /**
    * Count terminus by metro line
    * @param the name file
    * @return the number station
    */
  private int terminus_count(String file_name) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;
    int num_terminus = 0;

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro_line', method 'terminus_count':"+
                         " file not found");
    }

    // Count
    while( ( line = read.readLine() )  !=  null )
    {
      //
      if( line.charAt(0)  ==  'T' ) {

        // Check metro line
        String str_num = line.substring(line.length() - 2, line.length());
        if( str_num.equals(this.num) )
          num_terminus += 1;
      }
    }

    // close an dexit
    read.close();
    return num_terminus;
  }

  /**
    * Fill the table with the station by metro line
    * @param the name file
    * @return the table fill
    */
  private Station[] metro_init(String file_name) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    // Init the table
    Station list_stations[] = new Station[this.num_stations];

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
    int i = 0;
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
          String name   = line.substring(7, line.length() - 3);

          // Fill
          list_stations[i] = new Station(name, number);
          i++;
        }
      }
    }

    // Close and exit
    read.close();
    return list_stations;
  }

  /**
    * Fill the terminus table by metro line
    * @param the name file
    * @return the table filled
    */
  private Station[] terminus_init(String file_name) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    // Init the table
    Station terminus[] = new Station[this.num_terminus];

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro_line', method 'terminus_init':"+
                         " file not found");
    }

    // Read
    int j = 0;
    while( ( line = read.readLine() )  !=  null )
    {
      //
      if( line.charAt(0)  ==  'T' )
      {
        // Check metro line
        String str_num = line.substring(line.length() - 2, line.length());
        if( str_num.equals( this.num ) ) {

          // Init the terminus number
          String str_number = line.substring(2, 6);
          int number        = Integer.parseInt(str_number);

          // Init the terminus name
          String name       = line.substring(7, line.length() - 3);

          // Fill
          terminus[j]       = new Station(name, number);
          j++;
        }
      }
    }

    // Close and exit
    read.close();
    return terminus;
  }

  /*
  private ArrayList <Station> (String file_name)
  {
    // Variables
    BufferedReader read = null;
    String line;

    // Init the table
    Station terminus[] = new Station[this.num_terminus];

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro_line', method 'terminus_init':"+
                         " file not found");
    }

    // Read
    while( ( line = read.readLine() )  !=  null )
    {
      //
      if( line.charAt(0)  ==  'E' )
      {
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

        //

      }
    }

    // Close and exit
    read.close();
    return terminus;
  }
  */

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
