import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Menu {
	
	//
	private final Metro metro;
	private final TblStations ts;
	private final TblDijkstra td;
	// private final Fenetre win;
	
	/**
	 * @param dataFile file with metro information
	 *
	 * @throws IOException
	 */
	public Menu( String dataFile ) throws IOException
	{
		this.metro = new Metro( dataFile );
		this.ts = new TblStations( dataFile );
		Matrice mat = new Matrice( this.ts.getTbl() );
		this.td = new TblDijkstra( mat );
		// this.win = new Fenetre();
	}
	
	/**
	 * @throws IOException
	 */
	public void initMenu() throws IOException
	{
		this.metro.initLine();
		this.ts.fillTravelTime();
	}
	
	/**
	 *
	 */
	public void run() throws IOException
	{
		//
		boolean notFinish = true;
		String[] tab = new String[3];
		tab[0] = "Help";
		tab[1] = "Random Travel";
		tab[2] = "Classic Travel";
		
		//
		String choice = "unknown";
		Scanner scan = new Scanner( System.in );
		System.out.println( "\nWrite 'Help' to show all commands" );

		
		// look if program is finish
		while( notFinish )
		{
			System.out.print( " > " );
			choice = scan.nextLine();
			
			//command help
			if( choice.equals( "Help" ) )
			{
				System.out.println( "\nExit : to Quit the program." );
				System.out.println( "Random Travel : the program takes two stations at random and do the travel." );
				System.out.println( "Classic Travel : the user choose two stations and the program makes the travel.\n" );
			}
			
			//do a travel at random
			else if( choice.equals( "Random Travel" ) )
			{
				Random rand = new Random();
				int max = 375;
				int min = 0;
				int stationAleatoire = rand.nextInt( max - min + 1 ) + min;
				int stationAleatoire2 = rand.nextInt( max - min + 1 ) + min;
				
				while( stationAleatoire == stationAleatoire2 )
				{
					stationAleatoire = rand.nextInt( max - min + 1 ) + min;
					stationAleatoire2 = rand.nextInt( max - min + 1 ) + min;
				}
				
				//waiting for the travel
				System.out.println( "Travel calcul in process..." );
				this.td.calcul( stationAleatoire, stationAleatoire2 );
				
				//do the travel
				String time = this.td.travelTime();
				metro.printTravelDetail( this.td.getWay(), this.td.getWay().length, time );
				Fenetre fen = new Fenetre();
			}
			
			// user choose a travel
			else if( choice.equals( "Classic Travel" ) )
			{
				int numStart = -1, numStop = -1;
				
				// ask user
				do
				{
					// where are you?
					System.out.println( "Where are you ? Write the name of the Station " );
					System.out.print( " > " );
					String start = scan.nextLine();
					
					// where do you want to go?
					System.out.println( "Where do you want to go ? Write the name of the Station" );
					System.out.print( " > " );
					String stop = scan.nextLine();
					
					//metro name start and stop
					numStart = this.metro.convertNameToNumStation( start );
					numStop = this.metro.convertNameToNumStation( stop );
					
					//if don't know station
					if( numStart == -1 || numStop == -1 )
						System.out.println( "Unknown start or destination. Please enter a right start or destination" );
					
				} while( numStart == -1 || numStop == -1 );
				
				// processing..
				System.out.println( "Travel calcul in process..." );
				this.td.calcul( numStart, numStop );
				
				// do travel
				String time = this.td.travelTime();
				metro.printTravelDetail( this.td.getWay(), this.td.getWay().length, time );
				Fenetre fen = new Fenetre();
			}
			
			// if exit
			else if( choice.equals( "Exit" ) )
			{
				System.out.println( "Are you sure? If yes write 'Yes'" );
				System.out.print( " > " );

				String sure = scan.nextLine();
				
				// sure of exit
				// System.out.print( " > " );
				if( sure.equals( "Yes" ) )
				{	
					System.out.println("Exit Success.");
					return ;
				}
				
			}
			
			//
			else
			{
				// System.out.print( "Choice unknown." );
				// int diff = 0;
				
				// for( String s : tab)
				// {
				// 	// System.out.println(s);
				// 	diff = choice.compareTo(s);
				// 	// System.out.println(diff);
				// 	if(diff < 0)
				// 	{
				// 		diff = -diff;
				// 	}
				// 	if(diff <=32)
				// 	{
				// 		System.out.println(" But, did you mean " + s );
				// 		break;
				// 	}

				// }
				System.out.println();
			}
		}
		
	}
}
