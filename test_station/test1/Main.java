import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException
  {
    System.out.println("\n");

    if( args[0].isEmpty()  ==  true ) {
      System.out.println("Error in class 'Main', method 'main': need argument to compile");
      return;
    }

    String name_metro[] = { "01", "02", "03", "3b", "04", "05", "06", "07",
                            "7b", "08", "09", "10", "11", "12", "13", "14" };

    Metro_line all_metro[] = new Metro_line[16];
    for( int i=0; i < 16; i++ )
      all_metro[i] = new Metro_line(args[0], name_metro[i]);

    for( Metro_line m: all_metro ) {
      m.metro_print();
      // m.terminus_print();
      System.out.println("\n\n");
    }
  }
}
