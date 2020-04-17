// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

public class Metro {

  // Attribut
  private Metro_line[] metro;
  private int count_line;

  /**
    * Builder: Init Metro
    * @param count_line the number of line
    */
  public Metro( int count_line )
  {
    this.count_line = count_line;
    this.metro = new Metro_line[this.count_line];
  }

  /**
    * Init all metro line
    * @param name_metro[] the name metro line
    * @param file_name the file for read name station, travel time
    */
  public void init(String name_metro[], String file_name ) throws IOException
  {
    for( int i = 0 ; i < this.count_line; i++ )
      metro[i] = new Metro_line( file_name, name_metro[i] );
  }

  /**
    * Found the metro line where is the station
    * @param s the station
    * @return name metro line
    */
  public String what_line( Station s )
  {
    // Init
    String num_line = "Unknown";

    //
    for( int i = 0; i < this.count_line ; i++ )
    {
      Station[] line = metro[i].get_tbl_stations();

      // Compare other statio in the line
      for( Station s1: line )
      {
        // Found
        if( s1.equals(s) )
          num_line = metro[i].get_num();
      }
    }

    // Exit
    return num_line;
  }

  /**
    * Found the metro line where is the station, with her num
    * @param num_station the station number
    * @return name metro line
    */
  public String what_line2( int num_station )
  {
    // Init
    String num_line = "Unknown";

    //
    for( int i = 0; i < this.count_line ; i++ )
    {
      Station[] line = metro[i].get_tbl_stations();

      // Compare other statio in the line
      for( Station s1: line )
      {
        // Found
        if( s1.get_number()  == num_station )
          num_line = metro[i].get_num();
      }
    }

    // Exit
    return num_line;
  }

  /**
    * Found the station with her num
    * @param num_station the station number
    * @return Station
    */
  public Station what_station( int num_station )
  {
    // Init
    Station station = new Station();

    //
    for( int i = 0; i < this.count_line ; i++ )
    {
      Station[] line = metro[i].get_tbl_stations();

      // Compare to other station
      for( Station s1: line )
      {
        // Found
        if( s1.get_number()  == num_station )
          station = s1.copy();
      }
    }

    // Exit
    return station;
  }

  /**
    * Print where need to switch
    * @param way[] the way with the station number
    * @param way_length the length of the way
    */
  public void switch_metro_line( int way[], int way_length )
  {
    //
    String line_start = this.what_line2( way[0] );
    System.out.println("Take the line "+this.what_line2( way[0] ) );
    String line_now = "Unknown";

    //
    for( int i = 1 ; i < way_length - 1 ; i++ )
    {
      //
      line_now = this.what_line2( way[i] );
      if( !line_start.equals( line_now ) )
      {
        System.out.println("At the station: "+this.what_station( way[i] )+
          ".\nYou switch to the line: "+this.what_line2( way[i] ) );
        line_start = line_now;
      }
    }
  }

  /**
    * Print the travel we have to do
    * @param way[] the way with the station number
    * @param way_length the length of the way
    */
  public void travel( int way[], int way_length )
  {
    System.out.println("Start at: "+this.what_station( way[0] ).toString() );
    switch_metro_line( way, way_length );
    System.out.println("Stop at: " +this.what_station( way[ way_length - 1 ] ).toString() );
  }

  /**
    * Print the travel in detail
    * @param way[] the way with the station number
    * @param way_length the length of the way
    */
  public void travel_detail( int way[], int way_length )
  {
    for( int i=0 ; i < way_length ; i++)
      System.out.println(this.what_station( way[i] ).toString()
                        +" line "+this.what_line2( way[i] ) );
  }

  /**
    * Print data
    */
  public void print()
  {
    for( Metro_line m: metro )
    {
      m.metro_print();
      m.terminus_print();
    }
  }

  /**
    * Calcul global time
    * @param way[] the way with the station number
    * @param way_length the length of the way
    * @param
    */
  public int global_time( int way[], int way_length, String file_name ) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;
    int global_time = 0, i = 0;

    while( i + 1 < way_length )
    {
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
        // line which beginning by a 'E'
        if( line.charAt(0)  ==  'E' )
        {

          // Fill the case [ start ][ stop ] with the travel time
          int station_start = 0, station_stop = 0, travel_time = 0;

          int pos = 0, pos2 = 0;

          // Skip space and 'E'
          while( !Character.isDigit( line.charAt( pos2 ) ) ) pos2++;
          pos = pos2;

          // Read the first number
          while( Character.isDigit( line.charAt( pos2 ) ) ) pos2++;
          station_start = Integer.parseInt( line.substring( pos, pos2 ) );
          pos = pos2+=1;

          // Read the second number
          while( Character.isDigit( line.charAt( pos2 ) ) ) pos2++;
          station_stop = Integer.parseInt( line.substring( pos, pos2 ) );
          pos = pos2+=1;

          // Read the last number
          travel_time = Integer.parseInt( line.substring( pos2, line.length() ) );

          // Ad travel_time
          if( ( way[i]  ==  station_start  &&  way[i + 1]  ==  station_stop )
           || ( way[i]  ==  station_stop   &&  way[i + 1]  ==  station_start ) )
          {
            global_time+=travel_time;
          }
        }
      }

      // Close
      read.close();

      i++;
    }

    // Exit
    return global_time;
  }
}
