public class Travel {
	
	// Attributes
	private Station stationStart, stationStop;
	private int time;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Builder: default
	 */
	public Travel()
	{
		this.stationStart = new Station();
		this.stationStop = new Station();
		
		this.time = -1;
	}
	
	/**
	 * Builder: with param
	 *
	 * @param stationStart Class Station start
	 * @param stationStop  Class Station stop
	 * @param time         travel time between this two stations
	 */
	public Travel( Station stationStart, Station stationStop, int time )
	{
		this.stationStart = stationStart;
		this.stationStop = stationStop;
		
		this.time = time;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Getter: get number of StationStart
	 *
	 * @return number
	 */
	public int getNumStationStart()
	{
		return this.stationStart.getNumber();
	}
	
	/**
	 * Getter: Get StationStart
	 *
	 * @return StationStart
	 */
	public Station getStationStart()
	{
		return this.stationStart;
	}
	
	/**
	 * Setter: set StationStart
	 *
	 * @param stationStart Class Station start
	 */
	public void setStationStart( Station stationStart )
	{
		this.stationStart = stationStart;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Getter: get number of StationStop
	 *
	 * @return number stationStop
	 */
	public int getNumStationStop()
	{
		return this.stationStop.getNumber();
	}
	
	/**
	 * Getter: get StationStop
	 *
	 * @return StationStop
	 */
	public Station getStationStop()
	{
		return this.stationStop;
	}
	
	/**
	 * Setter: set stationStop
	 *
	 * @param stationStop Class Station stop
	 */
	public void setStationStop( Station stationStop )
	{
		this.stationStop = stationStop;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Getter: get travel time between two stations
	 *
	 * @return travel time
	 */
	public int getTime()
	{
		return this.time;
	}
	
	/**
	 * Setter: set travel time between two stations
	 *
	 * @param time travel time
	 */
	public void setTime( int time )
	{
		this.time = time;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Copy this travel for init a new travel
	 *
	 * @return new Travel
	 */
	public Travel copy()
	{
		return new Travel( this.stationStart, this.stationStop, this.time );
	}
	
	/**
	 * Swap StationStart with StationStop
	 *
	 * @return new Travel
	 */
	public Travel switchStation()
	{
		return new Travel( this.stationStop, this.stationStart, this.time );
	}
	
	/**
	 * Know if two travels are equals
	 *
	 * @param other other travel to compare
	 *
	 * @return true/false
	 */
	public boolean equals( Travel other )
	{
		return other.getStationStart().equals( this.stationStart )
				& other.getStationStop().equals( this.stationStop )
				& other.getTime() == this.getTime();
	}
	
	/**
	 * Return a String with attributes
	 *
	 * @return String
	 */
	public String toString()
	{
		return this.stationStart.toString() + "\n\tto " +
				this.stationStop.toString() + "\n\t\t: " + this.time + "\n";
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////