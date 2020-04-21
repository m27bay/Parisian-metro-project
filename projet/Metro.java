// For read the file
import java.io.BufferedReader;
import java.io.FileReader;

// Exception
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.LinkedList;

public class Metro {

////////////////////////////////////////////////////////////////////////////

  /**
    * Station class
    */
  public class Station {

    // Attributs
    private String name;
    private int number;
    private boolean isTerminus;

    /**
      * Builder: default
      */
    public Station()
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
    public Station(String name, int number, boolean isTerminus)
    {
      this.name   = name;
      this.number = number;
      this.isTerminus = isTerminus;
    }

    /**
      * Getter: get the name station
      * @return the name station
      */
    public String getName() { return this.name; }

    /**
      * Getter: get the number station
      * @return the number station
      */
    public int getNumber() { return this.number; }

    /**
      *
      */
    public boolean getIsTerminus() { return this.isTerminus; }

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
    public boolean equals( Station other )
    {
      return this.name.equals( other.getName() )
           & this.number  ==  other.getNumber()
           & this.isTerminus  ==  other.getIsTerminus();
    }

    /**
      * Copy this Station
      * @return the copy Sation
      */
    public Station copy()
      { return new Station( this.name, this.number, this.isTerminus ); }
  }

////////////////////////////////////////////////////////////////////////////

  /**
    *
    */
  public class Travel{

    //
    private Station stationStart, stationStop;
    private int time;

    /**
      *
      */
    public Travel()
    {
      this.stationStart = new Station();
      this.stationStop =  new Station();

      this.time = -1;
    }

    /**
      *
      */
    public Travel(Station stationStart, Station stationStop, int time)
    {
      this.stationStart = stationStart;
      this.stationStop  = stationStop;

      this.time = time;
    }

    /**
      *
      */
    public void setStationStart( Station stationStart )
      { this.stationStart = stationStart; }

    /**
      *
      */
    public int getNumStationStart() { return this.stationStart.getNumber(); }

    /**
      *
      */
    public Station getStationStart() { return this.stationStart; }

    /**
      *
      */
    public void setStationStop(  Station stationStop  )
      { this.stationStop  = stationStop ; }

    /**
      *
      */
    public int getNumStationStop() { return this.stationStop.getNumber(); }

    /**
      *
      */
    public Station getStationStop() { return this.stationStop; }

    /**
      *
      */
    public void setTime( int time  ) { this.time  = time; }

    /**
      *
      */
    public int getTime() { return this.time; }

    /**
      *
      */
    public Travel copy()
      { return new Travel( this.stationStart, this.stationStop, this.time); }

    /**
      *
      */
    public Travel switchStation()
    { return new Travel( this.stationStop, this.stationStart, this.time ); }

    /**
      *
      */
    public boolean equals( Travel other )
    {
      return other.getStationStart().equals( this.stationStart )
           & other.getStationStop().equals( this.stationStop )
           & other.getTime()  ==  this.getTime();
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

  /**
    *
    */
  public class MetroLine {

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
    public void setListTravel( ArrayList<Travel> _new )
    { this.listTravel = _new; }

    /**
      *
      */
    public String getName() { return this.name; }

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



////////////////////////////////////////////////////////////////////////////

  //
  private MetroLine[] metro;
  private String dataFile;

  /**
    * Builder
    */
  public Metro( String dataFile )
  {
    this.dataFile = dataFile;
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
  public void initLine() throws IOException
  {
    initLineStation();
    initLineTravel();

    sortTravel();
  }

  /**
    *
    */
  public void initLineStation() throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    for( int i = 0 ; i < 16 ; i++ )
      this.metro[i] = new MetroLine();

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( this.dataFile ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro', method 'initLineStation':"+
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
  public void initLineTravel() throws IOException
  {
    // Variables
    BufferedReader read = null;
    String line;

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( this.dataFile ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro', method 'initLineTravel':"+
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

        int stationStart = 0, stationStop = 0, travelTime = 0;
        int pos = 0, pos2 = 0;

        // Skip space and 'E'
        while( !Character.isDigit( line.charAt( pos2 ) ) ) pos2++;
        pos = pos2;

        // Read the first number
        while( Character.isDigit( line.charAt( pos2 ) ) ) pos2++;
        stationStart = Integer.parseInt( line.substring( pos, pos2 ) );
        pos = pos2+=1;

        // Read the second number
        while( Character.isDigit( line.charAt( pos2 ) ) ) pos2++;
        stationStop = Integer.parseInt( line.substring( pos, pos2 ) );
        pos = pos2+=1;

        // Read the last number
        travelTime = Integer.parseInt( line.substring( pos2, line.length() ) );

        t.setStationStart( whatStation( stationStart ) );
        t.setStationStop( whatStation( stationStop ) );
        t.setTime( travelTime );

        //
        String lineStart = whatMetroLine( stationStart );
        String lineStop  = whatMetroLine( stationStop  );

        if( lineStart.equals( lineStop ) )
          this.metro[ strLineToInt( lineStart ) ].addTravel( t );
      }
    }

    // Close and exit
    read.close();
  }

  /**
    *
    */
  public void sortTravel()
  {
    for( int i = 0 ; i < 16 ; i++ )
      sortIndexTravel(i);
  }

  /**
    *
    */
  public void sortIndexTravel( int index )
  {
    if( index  !=  7  &&  index  !=  11  &&  index  !=  14 )
    {
      LinkedList<Travel> _new;
      ArrayList<Travel> list;
      int count = 0;

      _new = new LinkedList<Travel>();
      list = this.metro[index].getListTravel();

      //
      while( count < list.size() )
      {
        for( Travel t : list )
        {
          //
          if( _new.size()  ==  0 )
          {
            _new.add( t );
            count++;
          }

          //
          else if( _new.size()  ==  1 )
          {
            if( t.equals( _new.peek() )
             || t.switchStation().equals( _new.peek() ) )
              continue;

            if( t.getNumStationStart()  ==  _new.peek().getNumStationStart() )
            {
              _new.addFirst( t.switchStation() );
              count++;
            }

            //
            else if( t.getNumStationStop()  ==  _new.peek().getNumStationStart() )
            {
              _new.addFirst( t );
              count++;
            }

            //
            else if( t.getNumStationStop()  ==  _new.peek().getNumStationStop() )
            {
              _new.add( t.switchStation() );
              count++;
            }

            //
            else if( t.getNumStationStart()  ==  _new.peek().getNumStationStop() )
            {
              _new.add( t );
              count++;
            }
          }

          //
          else
          {
            //
            if( t.equals( _new.peek() )  ||  t.equals( _new.peekLast() )
             || t.switchStation().equals( _new.peek() )
             || t.switchStation().equals( _new.peekLast() ) )
              continue;

            //
            if( t.getNumStationStart()  ==  _new.peek().getNumStationStart() )
            {
              _new.addFirst( t.switchStation() );
              count++;
            }

            //
            else if( t.getNumStationStop()  ==  _new.peek().getNumStationStart() )
            {
              _new.addFirst( t );
              count++;
            }

            //
            else if( t.getNumStationStop()  ==  _new.peekLast().getNumStationStop() )
            {
              _new.add( t.switchStation() );
              count++;
            }

            //
            else if( t.getNumStationStart()  ==  _new.peekLast().getNumStationStop() )
            {
              _new.add( t );
              count++;
            }
          }
        }
      }

      this.metro[index].setListTravel( new ArrayList<Travel>( _new ) );

      //
      _new.clear();
      list.clear();
    }
  }

  /**
    *
    */
  public void knowDirection( int way[], int pos, int numLine, int wayLength ) throws IOException
  {
    //
    int numStationStart = way[pos];
    int numStationStop  = way[ pos+1 ];

    //
    int line07 = strLineToInt( "07" );
    int line7b = strLineToInt( "7b" );
    int line10 = strLineToInt( "10" );
    int line13 = strLineToInt( "13" );

    // Right line
    if( numLine  !=  line07
    &&  numLine  !=  line7b
    &&  numLine  !=  line10
    &&  numLine  !=  line13 )
      knowDirectionRight( numStationStart, numStationStop, numLine );

    // loop line
    else if( numLine  ==  line7b )
      knowDirectionLoop( numStationStart, numStationStop, numLine );

    // fork line
    else if( numLine  ==  line07
         ||  numLine  ==  line10
         ||  numLine  ==  line13 )
      knowDirectionFork(  way, pos, wayLength );
  }

  /**
    *
    */
  public void knowDirectionRight( int numStationStart, int numStationStop, int numLine )
  {
    //
    ArrayList<Travel> tmp = this.metro[numLine].getListTravel();
    int direction = 0;

    //
    for( Travel t : tmp )
    {
      if( t.getStationStart().equals( whatStation( numStationStart ) )
      &&  t.getStationStop().equals( whatStation( numStationStop ) ) )
      {
        direction = tmp.size() - 1;
        break;
      }
    }

    //
    if( direction  ==  0 )
      System.out.println("Direction: "+tmp.get( direction ).getStationStart()+"\n" );
    else
      System.out.println("Direction: "+tmp.get( direction ).getStationStop()+"\n" );
  }

  /**
    *
    */
  public void knowDirectionLoop( int numStationStart, int numStationStop, int numLine )
  {
    //
    ArrayList<Travel> tmp = this.metro[numLine].getListTravel();
    int direction = 0;

    //
    for( Travel t : tmp )
    {
      if( t.getStationStart().equals( whatStation( numStationStart ) )
      &&  t.getStationStop().equals( whatStation( numStationStop ) ) )
      {
        direction = 5;
        break;
      }
    }

    //
    if( direction  ==  0 )
      System.out.println("Direction: "+tmp.get( direction ).getStationStart()+"\n" );
    else
      System.out.println("Direction: "+tmp.get( direction ).getStationStop()+"\n" );
  }

  /**
    *
    */
  public void knowDirectionFork( int way[], int pos, int wayLength ) throws IOException
  {
    int tmp = pos;
    while( whatMetroLine( way[tmp] ).equals( whatMetroLine( way[pos] ) )  )
    {
      if( tmp  ==  wayLength - 1 )
        break;
      tmp++;
    }

    // Variables
    BufferedReader read = null;
    String line;

    // Init read
    try
    {
      read = new BufferedReader ( new FileReader ( this.dataFile ) );
    }

    // Exception
    catch(FileNotFoundException exception)
    {
      System.out.println("Error in class 'Metro', method 'knowDirection':"+
              " file not found");
    }

    int start = way[tmp - 1], stop = way[tmp];
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
        int stationStart = 0, stationStop = 0, travelTime = 0;

        int pos1 = 0, pos2 = 0;

        // Skip space and 'E'
        while( !Character.isDigit( line.charAt(pos2) ) ) pos2++;
        pos1 = pos2;

        // Read the first number
        while( Character.isDigit( line.charAt(pos2) ) ) pos2++;
        stationStart = Integer.parseInt( line.substring( pos1, pos2 ) );
        pos1 = pos2+=1;

        if( whatStation( start ).getIsTerminus()  )
          System.out.println("Direction: "+whatStation( start )+"\n" );
        else if( whatStation( stop ).getIsTerminus() )
          System.out.println("Direction: "+whatStation( stop )+"\n" );
        else
        {
          if( stationStart  ==  start  ||  stationStart  ==  stop
          ||  stationStop   ==  start  ||  stationStop  ==  stop
          ||  stationStart  ==  36   &&  stationStop  == 198
          ||  stationStart  ==  198  &&  stationStop  == 52
          ||  stationStart  ==  52   &&  stationStop  == 201
          ||  stationStart  ==  201  &&  stationStop  == 145
          ||  stationStart  ==  145  &&  stationStop  == 372
          ||  stationStart  ==  373  &&  stationStop  == 196
          ||  stationStart  ==  196  &&  stationStop  == 259
          ||  stationStart  ==  259  &&  stationStop  == 36 )
          {
            start = stationStart;
            stop = stationStop;
          }
        }
      }
    }

    // Close and exit
    read.close();
  }

  /**
    *
    */
  public void printTravelDetail( int way[], int wayLength ) throws IOException
  {
    //
    int i = 0;

    //
    if( whatStation( way[0] ).getName().equals( whatStation( way[1] ).getName() ) )
      i = 1;

    //
    String lineStart = whatMetroLine( way[i] );
    System.out.println( "You start at\n" + whatStation( way[i] ).toString()
                      + " line "+lineStart );
    knowDirection( way, i, strLineToInt( lineStart ), wayLength );
    i++;

    //
    lineStart = whatMetroLine( way[i] );
    String lineNow  = "Unknown";
    Station stationNow;

    //
    for(; i < wayLength - 1; i++ )
    {
      //
      lineNow = whatMetroLine( way[i] );
      stationNow = whatStation( way[i] );

      //
      if( !lineStart.equals( lineNow ) )
      {
        System.out.println("\nAt the station\n"+stationNow.toString()+
          ".\nYou switch to the line: "+lineNow );
        knowDirection( way, i, strLineToInt( lineNow ), wayLength );
        lineStart = lineNow;
      }

      //
      System.out.println(stationNow.toString()+" line "+lineNow );
    }
    System.out.println();

    //
    System.out.println( "You stop at\n" + whatStation( way[i] ).toString()
                      + " line "+whatMetroLine( way[i] )+"\n" );
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
  public void printIndexStation( String index )
  {
    MetroLine m = this.metro[ strLineToInt( index ) ];
    System.out.println("//////////////////////////////\n"+
                       "////////// name: "+m.getName() +" //////////\n"+
                       "//////////////////////////////\n");
    m.printStation();
    System.out.println();
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

  /**
    *
    */
  public void printIndexTravel( String index )
  {
    MetroLine m = this.metro[ strLineToInt( index ) ];
    System.out.println("//////////////////////////////\n"+
                         "////////// name: "+m.getName() +" //////////\n"+
                         "//////////////////////////////\n");
    m.printTravel();
  }
}
