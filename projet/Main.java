// Exception

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main( String[] args ) throws IOException
	{
		//
		String dataFile = "../metroL.txt";
	
		//
		Menu burger = new Menu( dataFile );
		burger.initMenu();
		burger.run();
	}
}
