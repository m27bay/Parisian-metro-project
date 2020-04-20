// Exception
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException
  {
    Metro metro = new Metro();
    metro.initLine("../../metroL.txt");

    String lineTest = "10";

    // metro.printStation();

    metro.printIndexStation( lineTest );

    metro.printIndexTravel( lineTest );

    // metro.printTravel();
  }
}
