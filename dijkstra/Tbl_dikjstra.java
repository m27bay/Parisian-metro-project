import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

/**
  * Contient un tableau avec les pères de chaque sommet
  * et leur distance pour un certain plus court chemin
  */
public class Tbl_dikjstra
{

  private Matrice M;
  private int[] verif;//vérifi si le sommet a était traité
  private int[] dist;//tableau des distances depuis departure
  private int[] father;//tableau des pères, l'indice de départ aura -1 comme père
  private int[] way;//chemin
  private int departure;//indice de départ pour le chemin
  private int arrived;//indice d'aarivé pour le chemin
  
  private int column;
  private static int TREATED = 1;
  private static int NTREATED = -1;//non traité ou non accessible

  /**
    * Constructeur d'objets de classe Tbl_dikjstra,
    * les valeurs sont nulles
    */
  public Tbl_dikjstra()
  {
    this.M = null;
    this.column = 0;
    this.departure = -1;
    this.arrived = -1;
    this.verif  = null;
    this.father = null;
    this.dist   = null;
  }

  /**
    * Constructeur d'objets de classe Tbl_dikjstra,
    * les valeurs sont nulles, une matrice A est stocké
    */
  public Tbl_dikjstra(Matrice A)
  {
    this.M = A; //récupère la matrice
    this.column = A.getColumn();
    this.departure = -1;
    this.arrived = -1;

    //créer des tableaux remplit de -1 pour NTREATED
    this.verif  = this.init(column);
    this.father = this.init(column);
    this.dist   = this.init(column);
    this.way = new int[column];//sauf celui-ci
  }

  /**
    * Initialise un tableau non-traité
    */
  private int[] init(int column)
  {
    int[] T = new int[column];
    int i;
    for(i=0; i<column; i++)
        T[i] = NTREATED;

    //
    return T;
  }

  /**
    * Getter: Obtenir le tableau de distance
    * @return le tableau de distance
    */
  public int[] get_tbl_dist() { return this.dist; }

  /**
    * Getter: Obtenir le temps pour parcourir la distance global
    * @return le temps de parcours total
    */
  public int get_tmp_total() { return this.dist[ this.arrived ]; }

  /**
    * Getter: Obtenir le nombre de colonne
    * @return le nombre de colonne
    */
  public int get_column() { return this.column; }

  /**
    * Getter: Obtenir le chemin
    * @return le tableau du chemin
    */
  public int[] getWay(){ return this.way; }

  /**
    * Cherche les plus courts chemins depuis vertex et tous les autres sommets
    */
  public void calcul( int at, int to)
  {
    //vérifie si le chemin est valide
    if((at<0 || at>this.column) || (to<0 || to>this.column)){
      System.out.println("Invalide destination");
      return;
    }

    //si on avait déjà calculé un chemin, on remet les variables d'instances initialisées
    if(this.departure != -1)
      reinitialized();

    this.departure = at;
    this.arrived = to;

    //initialisation depuis departure, vertex est traité et on récupère ses distances avec les autres sommets
    int i;
    for(i=0; i<this.column; i++)
    {
      this.dist[i] = this.M.getVal(at, i);
      //si il y a un arc entre i et le départ, on ajoute à i le départ comme père
      if(this.dist[i] != NTREATED && at != i)
          this.father[i] = at;
    }
    this.verif[at] = TREATED;//le départ est traité

    //Calcul des distances et des pères
    int index_min;
    while( !finished() ){//tant que tous les indices ne sont pas traités
      index_min = min();//Retourne l'indice du minimum du tableau dist
      if(index_min == -1)//si graphe non connexe, on arrête
          break;

      this.verif[index_min] = TREATED;
      treatment(index_min);//actualise tbl de distance et des pères
    }

    this.way = CalculWay();//calcul le chemin et le stocke dans way
  }

  /**
    *remet les tableaux à -1 excepté celui du chemin qui est recréé
    */
  private void reinitialized()
  {
    int i;
    for(i=0; i<this.column; i++){
    this.verif[i]  = this.father[i] = this.dist[i] = NTREATED;
    }
     this.way = new int[column];
  }

  /**
    * Retourne l'indice du minimum du tableau dist
    */
  private int min()
  {
      int min = Integer.MAX_VALUE;
      int tmp;
      int index = -1;

    int i;
      for( i =0 ; i < this.column ; i++ )
      {
        tmp = this.dist[i];

        if(tmp == NTREATED || tmp == 0)//si pas d'arc trouvé
            continue;
        //si il y a un arc vers un sommet i non traité avec une distance de l'origine inférieur à tmp
        else if(this.verif[i] == NTREATED && tmp < min)
        {
            min = tmp;
            index = i;
        }
      }

    return index;
  }

  /**
    * Actualise les pères et les plus court chemins des sommets voisins de vertex
    */
  private void treatment(int vertex)
  {
    int i;
    for(i=0; i<column; i++)
    {
      //si i  traité ou n'existe pas un arc de vertex à i
      if(this.verif[i]==TREATED || this.M.getVal(vertex,i) == NTREATED)
          continue;

      //si la plus courte distance actuel de i à l'origine est plus grande
      //que celle de origine à vertex + vertex à i, ou que le sommet
      //n'a pas encore était rencontré alors on remplace
      else if( this.dist[i] > (this.dist[vertex] + this.M.getVal(vertex, i))
            || this.dist[i] == NTREATED)
        {
          this.dist[i] = this.dist[vertex] + this.M.getVal(vertex, i);
          this.father[i] = vertex;;
        }
    }
  }

  /**
    * @return true, si tous les indices ont étaient traités
    */
  private boolean finished()
  {
    for(int i: this.verif)
    {
      if(i != TREATED)
        return false;
    }
    return true;
  }

    /**
    * Renvoie le plus court chemin de at à to
    * @return tableau de chemin ou null si chemin non calculé avant
    **/
  private int[] CalculWay(){
    //Cas où le chemin n'a pas était calculé avant
      if(this.dist == null){
        System.out.println("Please use Calcul method before.");
        return null;
      }

      //pars de l'arrivée jusqu'au départ, stocke le chemin à l'envers dans way
      int position;//indice du père
      this.way[0] = this.arrived;
      this.way[1] = position = this.father[this.arrived];//sommet père de l'arrivé

      //
      int i = 2;

      //tant que l'on est pas au départ, on stocke les pères
      while(position != -1){
        position = this.father[position];//récupère son père
        this.way[i] = position;//le stock
        i++;
      }
      this.way[i] = this.departure;//met l'indice de départ

      //remet le chemin à l'endroit
      this.way = ArrayUtils.subarray(this.way,0,i-1);
      ArrayUtils.reverse(this.way);
      return this.way;
    }


  /**
    * Affiche le tableau de dikjsra tous les 20 colonnes
    */
  public void printTbl_Dikjstra()
  {
    int i,j,k,l;
    i = j = k = l = 0;
    while(i < father.length)
    {
      //sommets
      System.out.print("sommet :");
      while(i<father.length && i>=0)
      {
        //i>= 0 evite les erreurs si tableau est de taile inferieur à 20
        System.out.print(" "+i);
        if(i%20 == 0 && i!=0)
            break;
        i++;
      }


      //peres
      System.out.println();
      System.out.print("Pere :  ");
      while(j<father.length){
        if(this.verif[j] == TREATED)
        {
          System.out.print(" "+father[j]);
          if(j%20 == 0 && j!=0)
              break;
        }

        //
        j++;
      }

      //distances
      System.out.println();
      System.out.print("Dist :  ");
      while(k<dist.length)
      {
        System.out.print(" "+dist[k]);
        if(k%20 == 0 && k!=0)
            break;
        k++;
      }
    }

    //
    System.out.println();
  }

  public void printWay(){
    //Cas où le chemin n'a pas était calculé avant
    if(this.dist == null){
        System.out.println("Please use Calcul method before.");
        return;
      }

    int i;
    System.out.println("Chemin :");
    for(i=0; i<this.way.length; i++){
        if(i != 0 && (i%20) == 0)
          System.out.println();
        System.out.print(this.way[i]+" ");
    }
    System.out.println();
  }
}
