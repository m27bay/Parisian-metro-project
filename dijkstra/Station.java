
/**
  * Station class
  */
public class Station {

  // Attributs
  private String name;
  private int number;
  private int time_next;

  // Builder
  public Station()
  {
    this.name      = "Unknown";
    this.number    = -1;
    // this.time_next = -1;
  }

  public Station(String name, int number)
  {
    this.name      = name;
    this.number    = number;
    // this.time_next = -1;
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
  public String toString()
  {
    return "Station n°: "+this.number+": '"+this.name+"'";
  }

  /**
    * Compare this Stattion to an other
    * @return true/false
    */
  public boolean equals( Station other )
  {
    if( !this.name.equals( other.get_name() ) )
      return false;

    if( this.number  !=  other.get_number() )
      return false;

    return true;
  }

  /**
    * Copy this Station
    * @return the copy Sation
    */
  public Station copy()
  {
    return new Station( this.name, this.number );
  }
}
