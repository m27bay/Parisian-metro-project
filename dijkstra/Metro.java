// Exception
import java.io.IOException;

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
    */
  public void init(String name_metro[]) throws IOException
  {
    for( int i=0; i < this.count_line; i++ )
      metro[i] = new Metro_line("metroL.txt", name_metro[i]);
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
    String line_start = what_line2( way[0] );
    String line_now = "Unknown";

    //
    for( int i = 1 ; i < way_length - 1 ; i++ )
    {
      //
      line_now = what_line2( way[i] );
      if( !line_start.equals( line_now ) )
      {
        System.out.println("At the station: "+what_station( way[i] )+
          " you switch to the line: "+what_line2( way[i] ) );
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
    * Print data
    */
  public void print()
  {
    for( Metro_line m: metro )
    {
      m.metro_print();
      // m.terminus_print();
      System.out.println("\n\n");
    }
  }
}
