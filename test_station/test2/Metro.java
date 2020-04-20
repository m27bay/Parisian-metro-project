// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.LinkedList;

public class Metro {

  /**
    * Station class
    */
  class Station {

    // Attributs
    private String name;
    private int number;
    private boolean isTerminus;

    /**
      * Builder: default
      */
    Station()
    {
      this.name   = "Unknown";
      this.number = -1;
      this.isTerminus = false;
    }

    /**
      * Builder: with param
      * @param name name station
      * @param number number station
      */
    Station(String name, int number, boolean isTerminus)
    {
      this.name   = name;
      this.number = number;
      this.isTerminus = isTerminus;
    }

    /**
      * Getter: get the name station
      * @return the name station
      */
    String getName() { return this.name; }

    /**
      * Getter: get the number station
      * @return the number station
      */
    int getNumber() { return this.number; }

    /**
      *
      */
    boolean getIsTerminus() { return this.isTerminus; }

    /**
      * Return a string with attributs
      * @return a string
      */
    public String toString()
    {
      if( this.isTerminus )
        return "Station n°: "+this.number+": '"+this.name+"' (terminus)";
      return "Station n°: "+this.number+": '"+this.name+"'";
    }

    /**
      * Compare this Stattion to an other
      * @param other station for compare
      * @return true/false
      */
    boolean equals( Station other )
    {
      if( !this.name.equals( other.getName() ) )
        return false;

      if( this.number  !=  other.getNumber() )
        return false;

      if( this.isTerminus  !=  other.getIsTerminus() )
        return false;

      return true;
    }

    /**
      * Copy this Station
      * @return the copy Sation
      */
    Station copy()
      { return new Station( this.name, this.number, this.isTerminus ); }
  }

////////////////////////////////////////////////////////////////////////////

  class Travel{

    //
    private Station stationStart, stationStop;
    private int time;

    /**
      *
      */
    Travel()
    {
      this.stationStart = new Station();
      this.stationStop =  new Station();

      this.time = -1;
    }

    /**
      *
      */
    Travel(Station stationStart, Station stationStop, int time)
    {
      this.stationStart = stationStart;
      this.stationStop  = stationStop;

      this.time = time;
    }

    /**
      *
      */
    void setStationStart( Station stationStart )
      { this.stationStart = stationStart; }

    /**
      *
      */
    void setStationStop(  Station stationStop  )
      { this.stationStop  = stationStop ; }

    /**
      *
      */
    void setTime( int time  ) { this.time  = time; }

    /**
      *
      */
    int getNumStationStart() { return this.stationStart.getNumber(); }

    /**
      *
      */
    int getNumStationStop() { return this.stationStop.getNumber(); }

    /**
      *
      */
    public Travel copy()
      { return new Travel( this.stationStart, this.stationStop, this.time); }

    /**
      *
      */
    public void SwitchStation()
    {
      Station tmp = this.stationStart.copy();
      this.stationStart = this.stationStart.copy();
      this.stationStop = tmp.copy();
    }

    /**
      *
      */
    public String toString()
    {
      return this.stationStart.toString()+"\n\tto "+
             this.stationStop.toString()+"\n\t\t: "+this.time+"\n";
    }
  }

////////////////////////////////////////////////////////////////////////////

  class MetroLine {

    //
    private String name;

    private ArrayList<Travel> listTravel;
    private ArrayList<Station> listStation;

    /**
      * Builder
      */
    public MetroLine()
    {
      this.listTravel   = new ArrayList<Travel>( 50 );
      this.listStation  = new ArrayList<Station>( 50 );
      this.name = "Unknown";
    }

    /**
      *
      */
    public void setName( String newName ) { this.name = newName; }

    /**
      *
      */
    public String getName( ) { return this.name; }

    /**
      *
      */
    public ArrayList<Station> getListStation() { return this.listStation; }

    /**
      *
      */
    public ArrayList<Travel> getListTravel() { return this.listTravel; }

    /**
      *
      */
    public void addStation( Station _new ) { listStation.add( _new ); }

    /**
      *
      */
    public void addTravel( Travel _new ) { listTravel.add( _new ); }

    /**
      *
      */
    public void printStation()
    {
      if( this.listStation.isEmpty() )
        System.out.println("listStation empty");
      else
      {
        for( Station s: listStation )
          System.out.println( s.toString() );
      }
    }

    /**
      *
      */
    public void printTravel()
    {
      if( this.listTravel.isEmpty() )
        System.out.println("listTravel empty");
      else
      {
        for( Travel t: listTravel )
          System.out.println( t.toString() );
      }
    }
  }

////////////////////////////////////////////////////////////////////////////

  //
  private MetroLine[] metro;

  /**
    * Builder
    */
  public Metro()
  {
    this.metro = new MetroLine[16];
  }

  /**
    *
    */
  private Station whatStation( int numStation )
  {
    Station found = new Station();

    for( MetroLine m: metro )
    {
      for( Station s: m.getListStation() )
      {
        if( s.getNumber()  == numStation )
          found = s.copy();
      }
    }

    return found;
  }

  /**
    *
    */
  private String whatMetroLine( int numStation )
  {
    String found = "Unknown";

    for( MetroLine m: metro )
    {
      for( Station s: m.getListStation() )
      {
        if( s.getNumber()  == numStation )
          found = m.getName();
      }
    }

    return found;
  }

  /**
    *
    */
  private int strLineToInt( String line )
  {
    switch( line )
    {
      case "01":
        return 0;
      case "02":
        return 1;
      case "03":
        return 2;
      case "3b":
        return 3;
      case "04":
        return 4;
      case "05":
        return 5;
      case "06":
        return 6;
      case "07":
        return 7;
      case "7b":
        return 8;
      case "08":
        return 9;
      case "09":
        return 10;
      case "10":
        return 11;
      case "11":
        return 12;
      case "12":
        return 13;
      case "13":
        return 14;
      case "14":
        return 15;
    }

    return -1;
  }

  /**
    *
    */
  public void initLine( String file_name ) throws IOException
  {
    initLineStation( file_name );
    initLineTravel(  file_name );
  }

  /**
    *
    */
  public void initLineStation( String file_name ) throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    for( int i = 0 ; i < 16 ; i++ )
      this.metro[i] = new MetroLine();

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'MetroLine', method 'initLine':"+
                         " file not found");
    }

    // Read
    while( ( line = read.readLine() )  !=  null )
    {
      //
      if( line.charAt(0)  ==  '#' ) continue;

      //
      else if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' )
      {
        // Check the metro line
        String strLine = line.substring( line.length() - 2, line.length() );

        // Init station number
        String strNumber = line.substring(2, 6);
        int number = Integer.parseInt(strNumber);

        // Init name station
        String name   = line.substring(7, line.length() - 3);

        // Fill
        Station _new;
        if( line.charAt(0) == 'T' )
          _new = new Station( name, number, true );
        else
          _new = new Station( name, number, false );

        //
        this.metro[ strLineToInt( strLine ) ].addStation( _new );
      }
    }

    // Close and exit
    read.close();

    this.metro[ 0].setName("01");
    this.metro[ 1].setName("02");
    this.metro[ 2].setName("03");
    this.metro[ 3].setName("3b");
    this.metro[ 4].setName("04");
    this.metro[ 5].setName("05");
    this.metro[ 6].setName("06");
    this.metro[ 7].setName("07");
    this.metro[ 8].setName("7b");
    this.metro[ 9].setName("08");
    this.metro[10].setName("09");
    this.metro[11].setName("10");
    this.metro[12].setName("11");
    this.metro[13].setName("12");
    this.metro[14].setName("13");
    this.metro[15].setName("14");
  }

  /**
    *
    */
  public void initLineTravel( String file_name ) throws IOException
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
      System.out.println("Error in class 'MetroLine', method 'initLine':"+
                         " file not found");
    }

    // Read
    while( ( line = read.readLine() )  !=  null )
    {
      //
      if( line.charAt(0)  ==  '#'
      ||  line.charAt(0)  ==  'V'
      ||  line.charAt(0)  ==  'T')
        continue;

      //
      else
      {
        Travel t = new Travel();

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

        t.setStationStart( whatStation( station_start ) );
        t.setStationStop( whatStation( station_stop ) );
        t.setTime( travel_time );

        //
        String line_found = whatMetroLine( station_start );
        this.metro[ strLineToInt( line_found ) ].addTravel( t );
      }
    }

    // Close and exit
    read.close();
  }

  /**
    *
    */
  public ArrayList<Travel> sortTravel()
  {
    // for( int i = 0 ; i < 16 ; i++ )
    // {
    //   for( ArrayList<Travel> list: this.metro[i].getListTravel() )
    //   {

    //   }
    // }

    LinkedList<Travel> _new = new LinkedList<Travel>();
    for( ArrayList<Travel> list: this.metro[ strLineToInt("7b") ].getListTravel() )
    {
      for( Travel t : list )
      {
        //
        if( _new.size  ==  0 )
          _new.add( t );

        //
        else if( _new.size  ==  1 )
        {
          if( t.getNumStationStart().getNumber()  ==  _new.peek().getNumber() )
            _new.addFirst( t.SwitchStation() );
          else
            _new.add( t.SwitchStation() );
        }

        //
        else if( _new.size  >  1 )
        {
          if( t. )
        }
      }
    }
  }

  /**
    *
    */
  public void printStation()
  {
    for( MetroLine m: metro)
    {
      System.out.println("//////////////////////////////\n"+
                         "////////// name: "+m.getName() +" //////////\n"+
                         "//////////////////////////////\n");
      m.printStation();
    }
  }

  /**
    *
    */
  public void printTravel()
  {
    for( MetroLine m: metro )
    {
      System.out.println("//////////////////////////////\n"+
                         "////////// name: "+m.getName() +" //////////\n"+
                         "//////////////////////////////\n");
      m.printTravel();
    }
  }
}
