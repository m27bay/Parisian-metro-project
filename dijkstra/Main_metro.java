// Exception
import java.io.IOException;

public class Main_metro
{
  // throws IOException for tbl_stations.fill(), file reader
  public static void main (String[] args) throws IOException {

    // Init matrix with number station and travel time
    Tbl_stations A = new Tbl_stations("metroL.txt");
    A.fill_travel_time();
    Matrice M = new Matrice( A.get_tbl() );

    // test between two stations

    // int station_start = 0;
    // int station_stop = 7;

    // global time2 = -1
    // int station_start = 231;
    // int station_stop = 235;

    // global time2 = -1
    // int station_start = 262;
    // int station_stop = 298;

    int station_start = 351;
    int station_stop = 286;

    // Dikjstra
    Tbl_dikjstra F = new Tbl_dikjstra(376);
    int []way = F.way( M, station_start, station_stop );

    // Init name metro
    String name_metro[] = { "01", "02", "03", "3b", "04", "05", "06", "07",
                            "7b", "08", "09", "10", "11", "12", "13", "14" };



    Metro metro = new Metro(16);
    metro.init( name_metro,"metroL.txt" );
    // metro.print();

    System.out.println();
    metro.travel_detail( way, way.length );

    System.out.println("\n");
    metro.travel( way, way.length, "metroL.txt" );

    System.out.println();
    int global_time = metro.global_time( way, way.length, "metroL.txt" );
    System.out.println( "Global time: "+global_time+" sec."+
                        "Soit "+global_time/60.0+" min" );
    System.out.println("Global time2: (wrong)"+F.get_tmp_total() );
  }
}
