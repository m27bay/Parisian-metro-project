// Exception

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main( String[] args ) throws IOException
	{
		
		//
		String dataFile = "../metroL.txt";
		
		// diff exit Exit 32
		// diff random travel Random Travel
		String test = "exit";
		int diff = test.compareTo( "Exit" );
		if( diff < 0 )
			diff = -diff;
		System.out.println( "diff : "+ diff);
		System.out.println("Did you mean : 'Exit' ?");
		
		//
		/*
		Menu burger = new Menu( dataFile );
		burger.initMenu();
		burger.run();
		*/
	}
}
