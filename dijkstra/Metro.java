// Exception
import java.io.IOException;

public class Metro {

  // Attribut
  private Metro_line[] metro;
  private int count_line;

  // builder
  public Metro( int count_line )
  {
    this.count_line = count_line;
    this.metro = new Metro_line[this.count_line];
  }

  public void init(String name_metro[]) throws IOException
  {
    for( int i=0; i < this.count_line; i++ )
      metro[i] = new Metro_line("metroL.txt", name_metro[i]);
  }



  public String what_line( Station s )
  {
    //
    String num_line = "Unknown";

    //
    for( int i = 0; i < this.count_line ; i++ )
    {
      //
      Station[] line = metro[i].get_tbl_stations();

      //
      for( Station s1: line )
      {
        //
        if( s1.equals(s) )
          num_line = metro[i].get_num();
      }
    }

    // Exit
    return num_line;
  }

  public String what_line2( int num_station )
  {
    //
    String num_line = "Unknown";

    //
    for( int i = 0; i < this.count_line ; i++ )
    {
      //
      Station[] line = metro[i].get_tbl_stations();

      //
      for( Station s1: line )
      {
        //
        if( s1.get_number()  == num_station )
          num_line = metro[i].get_num();
      }
    }

    // Exit
    return num_line;
  }

  public Station what_station( int num_station )
  {
    //
    Station station = new Station();

    //
    for( int i = 0; i < this.count_line ; i++ )
    {
      //
      Station[] line = metro[i].get_tbl_stations();

      //
      for( Station s1: line )
      {
        //
        if( s1.get_number()  == num_station )
          station = s1.copy();
      }
    }

    // Exit
    return station;
  }

  public void switch_metro_line( int way[], int way_length )
  {
    //
    String line_start = what_line2( way[0] );
    String line_now = "Unknown";

    //
    for( int i = 1 ; i < way_length ; i++ )
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

  public void travel( int way[], int way_length )
  {
    System.out.println("Start at: "+metro.what_station( way[0] ).toString() );
    switch_metro_line( way, way_length )
    System.out.println("Stop at: " +metro.what_station( way[ way_length - 1 ] ).toString() );
  }


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
