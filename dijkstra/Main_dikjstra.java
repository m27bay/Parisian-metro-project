// Exception
import java.io.IOException;

/**
 * Décrivez votre classe main_dikjstra ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Main_dikjstra
{
  // throws IOException for tbl_stations.fill(), file reader
  public static void main (String[] args) throws IOException{

    /*
    int T[][] = {
                 {0,4,8,-1,11,-1  },
                 {-1,0,3,2,-1,-1  },
                 {-1,-1,0,1,2,-1  },
                 {-1,-1,2,0,5,7   },
                 {-1,-1,-1,-1,0,3 },
                 {-1,-1,-1,-1,-1,0}
                };

    int T2[][] = {
                  {0,30,60,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1  },
                  {-1,0,45,70,100,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 },
                  {-1,-1,0,-1,90,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1  },
                  {-1,-1,-1,0,-1,30,60,-1,-1,-1,-1,-1,-1,-1,-1  },
                  {-1,-1,-1,40,0,-1,-1,75,-1,-1,-1,-1,-1,-1,-1  },
                  {-1,-1,-1,-1,-1,0,25,-1,40,-1,-1,-1,-1,-1,-1  },
                  {-1,-1,-1,-1,-1,-1,0,100,30,45,140,-1,-1,-1,-1},
                  {-1,-1,-1,-1,-1,-1,-1,0,-1,-1,120,-1,-1,-1,-1 },
                  {-1,-1,-1,-1,-1,-1,-1,-1,0,40,-1,35,-1,-1,-1, },
                  {-1,-1,-1,-1,-1,-1,-1,-1,-1,0,80,15,-1,-1,-1  },
                  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,40,-1,50  },
                  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,60,0,30,65,-1  },
                  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,40,60  },
                  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,55  },
                  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0  }
                };

    //
    Matrice A = new Matrice(T2);
    A.printMatrice();

    //
    Tbl_dikjstra D = new Tbl_dikjstra(6);
    D.calcul(A,0);
    D.printTbl_Dikjstra();

    //
    Tbl_dikjstra E = new Tbl_dikjstra();
    int []way = E.way(A,0,14);

    //
    System.out.print("Chemin : ");
    for( int i=0 ; i < way.length ; i++)
          System.out.print(way[i]+" ");
    System.out.println();
    /*

    //
    Tbl_stations tbl_stations = new Tbl_stations("metro.txt");
    tbl_stations.fill_travel_time();
    // tbl_stations.print();
    tbl_stations.write_file();
    */

    // Test //

    //
    Tbl_stations A = new Tbl_stations("metro.txt");
    A.fill_travel_time();

    //
    Matrice M = new Matrice( A.get_tbl() );
    // M.printMatrice();

    //
    int station_start = 0;
    int station_stop = 7;
    Tbl_dikjstra F = new Tbl_dikjstra(376);

    //F.calcul(M,0);
    int []way = F.way(M,station_start,station_stop);

    //
    System.out.print("Chemin : ");
    for( int i=0 ; i < way.length ; i++)
          System.out.print(way[i]+" ");
    System.out.println();
  }
}
