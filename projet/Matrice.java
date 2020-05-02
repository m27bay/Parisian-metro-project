/**
 * Class Matrice
 */
public class Matrice {
	//line*column
	private int[][] directions;
	
	//
	private int line;
	private int column;
	
	/**
	 * Constructeur d'objets de classe Matrice
	 */
	public Matrice( int[][] M )
	{
		this.directions = M;
		this.column = directions[ 0 ].length;
		this.line = directions.length;
	}
	
	/**
	 * Builder: defaulf
	 */
	public Matrice()
	{
		this.directions = null;
		this.line = 0;
		this.column = 0;
	}
	
	/**
	 * Getter: get count column
	 *
	 * @return count column
	 */
	public int getColumn()
	{
		return this.column;
	}
	
	/**
	 * Getter: get count line
	 *
	 * @return count line
	 */
	public int getLine()
	{
		return this.line;
	}
	
	/**
	 * Getter: get count value at [i][j]
	 *
	 * @param i column
	 * @param j line
	 *
	 * @return the value at directions[i][j]
	 */
	public int getVal( int i, int j )
	{
		return this.directions[ i ][ j ];
	}
	
	/**
	 * Affiche la matrice
	 */
	public void printMatrice()
	{
		int i, j;
		System.out.println( "format : " + this.line + "X" + this.column );
		for( i = 0; i < line ; i++ )
		{
			for( j = 0; j < column ; j++ )
				System.out.print( directions[ i ][ j ] + " " );
			System.out.print( "\n" );
		}
	}
}
