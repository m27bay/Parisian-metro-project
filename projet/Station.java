/**
 * Station Class
 */
public class Station {
	
	// Attributes
	private String name;
	private int number;
	private boolean isTerminus;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Builder: default
	 */
	public Station()
	{
		this.name = "Unknown";
		this.number = -1;
		this.isTerminus = false;
	}
	
	/**
	 * Builder: with param
	 *
	 * @param name       station name
	 * @param number     station umber
	 * @param isTerminus boolean is a terminus
	 */
	public Station( String name, int number, boolean isTerminus )
	{
		this.name = name;
		this.number = number;
		this.isTerminus = isTerminus;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Getter: get the Station name
	 *
	 * @return this.name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter: get the number station
	 *
	 * @return the number station
	 */
	public int getNumber()
	{
		return this.number;
	}
	
	/**
	 * Getter: get isTerminus
	 *
	 * @return this.isTerminus
	 */
	public boolean getIsTerminus()
	{
		return this.isTerminus;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Return a string with attributes
	 *
	 * @return String
	 */
	public String toString()
	{
		if( this.isTerminus )
			return "Station n°: " + this.number + ": '" + this.name + "' (terminus)";
		return "Station n°: " + this.number + ": '" + this.name + "'";
	}
	
	/**
	 * Compare this Station to an other
	 *
	 * @param other station for compare
	 *
	 * @return true/false
	 */
	public boolean equals( Station other )
	{
		return this.name.equals( other.getName() )
				& this.number == other.getNumber()
				& this.isTerminus == other.getIsTerminus();
	}
	
	/**
	 * Copy this Station
	 *
	 * @return the copy Sation
	 */
	public Station copy()
	{
		return new Station( this.name, this.number, this.isTerminus );
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////