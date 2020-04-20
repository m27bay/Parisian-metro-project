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

    //
    TblStations ts = new TblStations( args[0] );
    ts.fillTravelTime();

    //
    Matrice mat = new Matrice( ts.getTbl() );

    //
    TblDijkstra td = new TblDijkstra( mat );
    td.calcul(43, 173);
    td.printTblDijkstra();
    td.printWay();
  }
}
