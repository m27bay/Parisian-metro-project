// Exception
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException
  {
    //
    if( args[0].isEmpty()  ==  true ) {
      System.out.println("Error in class 'Main', method 'main':"+
                         "need argument to compile");
      System.exit(0);
    }

    //
    Metro metro = new Metro( args[0] );
    metro.initLine();

    // metro.printStation();
    // metro.printTravel();

    metro.printIndexTravel( "10" );

    //
    TblStations ts = new TblStations( args[0] );
    ts.fillTravelTime();

    //
    Matrice mat = new Matrice( ts.getTbl() );

    //
    System.out.println();
    TblDijkstra td = new TblDijkstra( mat );

    // td.calcul(43, 173);
    // td.calcul(167, 351);
    // td.calcul(362, 92);
    td.calcul(47, 37);

    // td.printTblDijkstra();
    td.printWay();

    //
    System.out.println();
    metro.printTravelDetail( td.getWay(), td.getWay().length );

    //
    System.out.println();
    System.out.println( td.travelTime() );
  }
}
