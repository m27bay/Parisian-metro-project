import java.io.*;

public class Station {
  private String name;
  private int number;

  public Station() {
    this.name   = "Unknown";
    this.number = -1;
  }

  public Station(String name, int number) {
    this.name   = name;
    this.number = number;
  }

  public String get_name() {
    return this.name;
  }

  public int get_number() {
    return this.number;
  }

  public void station_print(Station s) {
    System.out.println("Sation n°: "+s.get_number()+": '"+s.get_name()+"'");
  }
}
