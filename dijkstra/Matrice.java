
/**
 * Décrivez votre classe Matrice ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

public class Matrice
{
  //line*column
  private int[][] directions;

  //
  private int line;
  private int column;

  /**
    * Constructeur d'objets de classe Matrice
    */
  public Matrice(int[][] M)
  {
    this.directions = M;
    this.column = directions[0].length;
    this.line = directions.length;
  }

  /**
    *
    */
  public Matrice()
  {
    this.directions = null;
    this.line = this.column = 0;
  }

  /**
    *
    */
  public int getColumn() { return this.column; }

  /**
    *
    */
  public int getLine() { return this.line; }

  /**
    *
    */
  public int getVal(int i, int j) { return this.directions[i][j]; }

  /**
    * Affiche la matrice
    */
  public void printMatrice()
  {
    int i, j;
    System.out.println("format : "+this.line+"X"+this.column);
    for(i=0; i<line; i++)
    {
      for(j=0; j<column; j++)
          System.out.print(directions[i][j]+" ");
      System.out.print("\n");
    }
  }
}
