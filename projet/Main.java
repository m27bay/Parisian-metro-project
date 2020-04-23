// Exception
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException
  {
    //
    String dataFile = "../metroL.txt";

    //
    Metro metro = new Metro( dataFile );
    metro.initLine();

    // metro.printStation();
    // metro.printTravel();

    //
    TblStations ts = new TblStations( dataFile );
    ts.fillTravelTime();

    //
    Matrice mat = new Matrice( ts.getTbl() );

    //
    System.out.println();
    TblDijkstra td = new TblDijkstra( mat );

    // td.calcul(43, 173);
    // td.calcul(167, 351);
    // td.calcul(362, 92);

    td.calcul(99, 229);

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
