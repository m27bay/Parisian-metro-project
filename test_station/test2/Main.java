// Exception
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException
  {
    Metro metro = new Metro();
    metro.initLine("metroL.txt");
    // metro.printStation();
    metro.printTravel();
  }
}
