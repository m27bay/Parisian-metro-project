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
		String choice = "unknown";
		Scanner scan = new Scanner( System.in );
		System.out.println( "\nWrite 'Help' to show all commands" );

		
		// look if program is finish
		while( true )
		{
			System.out.print( " > " );
			choice = scan.nextLine();
			
			//command help
			if( choice.equals( "Help" ) )
			{
				System.out.println("\n Here are all the commands you can do. Please respect the typography. For exit please type Exit.\n");
				System.out.println( " 'Random Travel' : the program takes two stations at random and do the travel." );
				System.out.println( " 'Classic Travel' : the user choose two stations and the program makes the travel." );
				System.out.println( " 'Exit' : to Quit the program.(error 0)\n" );

			}
			
			//do a travel at random
			else if( choice.contains( "Travel" ) )
			{
				int numStart = -1, numStop = -1;
				
				//
				if( choice.equals( "Random Travel" ) )
				{
					Random rand = new Random();
					int max = 375;
					do
					{
						numStart = rand.nextInt( max + 1 );
						numStop = rand.nextInt( max + 1 );
						
					} while( numStart == numStop );
				}
				
				// user choose a travel
				else if( choice.equals( "Classic Travel" ) )
				{
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
				}
				else
				{
					System.out.println( "Command unknown." );
					continue;
				}
				
				//waiting for the travel
				System.out.println( "Travel calcul in process..." );
				this.td.calcul( numStart, numStop );
				
				//do the travel
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
				if( sure.equals( "Yes" ) )
				{	
					System.out.println("Exit Success.");
					System.exit( 0 );
					// return don't work, don't know why
				}
				
			}
			
			// if command unknown
			else
			{
				System.out.print( "Command unknown." );
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
