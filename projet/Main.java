// Exception
import java.io.IOException;

// awt
import java.awt.Color;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;

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

    td.calcul(36, 15);

    // td.printTblDijkstra();
    td.printWay();

    //
    System.out.println();
    String time = td.travelTime();
    metro.printTravelDetail( td.getWay(), td.getWay().length, time );

    Fenetre fen = new Fenetre();
  }
}
