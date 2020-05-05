// tool

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class MetroLine {
	
	// Attributes
	private String name;
	
	private ArrayList < Travel > listTravel;
	private ArrayList < Station > listStation;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Builder: default
	 */
	public MetroLine()
	{
		this.listTravel = new ArrayList < Travel >( 50 );
		this.listStation = new ArrayList < Station >( 50 );
		this.name = "Unknown";
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Getter: get name
	 *
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Setter: set name
	 *
	 * @param newName the new name
	 */
	public void setName( String newName )
	{
		this.name = newName;
	}
	
	/**
	 * Getter: get listStation
	 *
	 * @return listStation
	 */
	public ArrayList < Station > getListStation()
	{
		return this.listStation;
	}
	
	/**
	 * Getter: get listTravel
	 *
	 * @return listTravel
	 */
	public ArrayList < Travel > getListTravel()
	{
		return this.listTravel;
	}
	
	/**
	 * Setter: set listTravel
	 *
	 * @param _new the new listTravel
	 */
	public void setListTravel( ArrayList < Travel > _new )
	{
		this.listTravel = _new;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Add a station in the listStation
	 *
	 * @param _new the Station to add
	 */
	public void addStation( Station _new )
	{
		listStation.add( _new );
	}
	
	/**
	 * Add a travel in the listTravel
	 *
	 * @param _new the Travel to add
	 */
	public void addTravel( Travel _new )
	{
		listTravel.add( _new );
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Print all station in the listStation
	 */
	public void printStation()
	{
		if( this.listStation.isEmpty() )
			System.out.println( "listStation empty" );
		else
		{
			for( Station s : listStation )
				System.out.println( s.toString() );
		}
	}
	
	/**
	 * Print all travel in the listTravel
	 */
	public void printTravel()
	{
		if( this.listTravel.isEmpty() )
			System.out.println( "listTravel empty" );
		else
		{
			for( Travel t : listTravel )
				System.out.println( t.toString() );
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////