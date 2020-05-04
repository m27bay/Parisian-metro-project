// Exception

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main( String[] args ) throws IOException
	{
		//
		String dataFile = "../metroL.txt";
		
		//
		Menu menu = new Menu( dataFile );
		menu.initMenu();
		
		switch( args.length )
		{
			case 0 :
				menu.run();
			case 1 :
				menu.randomTravel();
			default :
				System.out.println("Unknown");
		}
	}
}
