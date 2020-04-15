
/**
  * Station class
  */
public class Station {

  // Attributs
  private String name;
  private int number;
  private int time_next;

  // Builder
  public Station() {
    this.name   = "Unknown";
    this.number = -1;
  }

  public Station(String name, int number) {
    this.name   = name;
    this.number = number;
  }

  // Methods
  /**
    * Getter: get the name station
    * @return the name station
    */
  public String get_name() { return this.name; }

  /**
    * Getter: get the number station
    * @return the number station
    */
  public int get_number() { return this.number; }

  /**
    * Return a string with attributs
    * @return a string
    */
  public String toString(){
    return "Station n°: "+this.number+": '"+this.name+"'";
  }
}
