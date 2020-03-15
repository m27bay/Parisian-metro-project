import java.io.*;
import java.util.ArrayList;

public class Metro_line {
  private int num_stations, num_terminus;
  private String num;
  private Station[] terminus;
  private Station[] list_stations;
  private ArrayList <Station> line;

  public Metro_line(String file_name, String num) throws IOException
  {
    this.num = num;

    this.num_stations  = station_count(file_name);
    this.num_terminus  = terminus_count(file_name);

    this.list_stations = metro_init(file_name);
    this.terminus      = terminus_init(file_name);
  }

  private int station_count(String file_name) throws IOException
  {
    BufferedReader read = null;
    String line;
    int num_stations = 0;

    try {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Station', method 'station_init': file not found");
    }

    while( ( line = read.readLine() )  !=  null ) {
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' ) {

        String str_num = line.substring(line.length() - 2, line.length());
        if( str_num.equals(this.num) )
          num_stations += 1;
      }
    }

    read.close();
   return num_stations;
  }

  private int terminus_count(String file_name) throws IOException
  {
    BufferedReader read = null;
    String line;
    int num_terminus = 0;

    try {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Station', method 'station_init': file not found");
    }

    while( ( line = read.readLine() )  !=  null ) {
      if( line.charAt(0)  ==  'T' ) {

        String str_num = line.substring(line.length() - 2, line.length());
        if( str_num.equals(this.num) )
          num_terminus += 1;
      }
    }

    read.close();
   return num_terminus;
  }

  private Station[] metro_init(String file_name) throws IOException
  {
    BufferedReader read = null;
    String line;

    Station list_stations[] = new Station[this.num_stations];

    try {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Station', method 'station_init': file not found");
    }

    int i = 0, j = 0;
    while( ( line = read.readLine() )  !=  null ) {
      if( line.charAt(0)  ==  'V'  ||  line.charAt(0)  ==  'T' ) {
        String str_number = line.substring(2, 6);
        String str_num = line.substring(line.length() - 2, line.length());

        if( str_num.equals( this.num ) ) {

          int number = Integer.parseInt(str_number);
          String name   = line.substring(7, line.length() - 3);

          list_stations[i] = new Station(name, number);
          i++;
        }
      }
    }

    read.close();
    return list_stations;
  }

  private Station[] terminus_init(String file_name) throws IOException
  {
    BufferedReader read = null;
    String line;

    Station terminus[] = new Station[this.num_terminus];

    try {
      read = new BufferedReader ( new FileReader ( file_name ) );
    }

    catch(FileNotFoundException exception) {
      System.out.println("Error in class 'Station', method 'station_init': file not found");
    }

    int j = 0;
    while( ( line = read.readLine() )  !=  null ) {
      if( line.charAt(0)  ==  'T' ) {
        String str_number = line.substring(2, 6);
        String str_num    = line.substring(line.length() - 2, line.length());

        if( str_num.equals( this.num ) ) {

          int number   = Integer.parseInt(str_number);
          String name  = line.substring(7, line.length() - 3);
          terminus[j]  = new Station(name, number);
          j++;
        }
      }
    }

    read.close();
    return terminus;
  }

  public void terminus_print() {
    for( Station s: this.terminus )
      System.out.println(s.toString());
  }

  public void metro_print() {
    System.out.println("Station n°: "+this.num);
    System.out.println("Numbers of stations inside: "+this.num_stations+"\n");
    System.out.println("All stations:");

    if( this.num_stations  ==  0 )
      System.out.println("Empty");
    else {
      int j = 0;
      for( Station s: this.list_stations ) {
        if( ( j <  this.num_terminus ) &&
          ( s.get_name() ).equals( terminus[j].get_name() ) ) {
          System.out.print("terminus ");
          j++;
        }
        System.out.println(s.toString());
      }
    }
  }
}
