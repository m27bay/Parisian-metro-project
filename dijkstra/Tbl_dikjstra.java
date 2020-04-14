import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
/**
  * contient un tableau avec les pères de chaque sommet et leur distance pour un certain plus court chemin
  */
public class Tbl_dikjstra
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private int[] verif;//vérifi si le sommet a était traité
    private int[] father;
    private int[] dist;
    private int column;
    private static int TREATED = 1;
    private static int NTREATED = -1;//non traité ou non accessible

    /**
      * Constructeur d'objets de classe Tbl_dikjstra,
      *l es valeurs sont nulles
      */
    public Tbl_dikjstra()
    {
        this.column = 0;
        this.verif  = null;
        this.father = null;
        this.dist   = null;
    }

    /**
      * Initialisation avec un certain nombre de colonne,
      * les valeurs sont à -1
      * @param column, le nombre de colonne
      */
    public Tbl_dikjstra(int column)
    {
        this.column = column;
        this.verif  = this.init(column);
        this.father = this.init(column);
        this.dist   = this.init(column);
    }

    /**
      * Initialise un tableau non-traité
      */
    private int[] init(int column){
        int[] T = new int[column];
        int i;
        for(i=0; i<column; i++){
            T[i] = NTREATED;
        }
        return T;
    }

    /**
      * Cherche les plus courts chemins depuis vertex et tous les autres sommets
      */
    public int[][] calcul(Matrice A, int vertex){
        //si pas bon format, on remplace
        if(A.getColumn() != this.column){
            this.column = A.getColumn();
            this.verif = this.init(column);
            this.father = this.init(column);
            this.dist = this.init(column);
        }

        //init, vertex est traité et on récupère ses distances avec les autres sommets
        int i;
        for(i=0; i<this.column; i++){
        	this.dist[i] = A.getVal(vertex, i);
        	if(this.dist[i] != NTREATED && vertex != i)//si il y a un arc on ajoute son père
        		this.father[i] = vertex;
        }
        this.verif[vertex] = TREATED;


        int index_min;
        while( !finished() ){
        	index_min = min();
            if(index_min == -1)
                break;
           // System.out.println("before 'min()': "+index_min);
        	this.verif[index_min] = TREATED;
        	treatment(A, index_min);//actualise tbl de distance et des pères
        }

        int[][] T = {this.dist, this.father};
        return T;
    }

    /**
      * Retourne l'indice du minimum du tableau dist
      */
    private int min(){
    	int min = Integer.MAX_VALUE;
    	int tmp;
    	int index = -1;

      //System.out.println( "column: "+this.column );
      int i;
    	for( i =0 ; i < this.column ; i++ ){
    		tmp = this.dist[i];

            //System.out.println("tmp = "+tmp);
    		if(tmp == NTREATED || tmp == 0)//si pas d'arc trouvé
    			continue;
    		//si il y a un arc vers un sommet i non traité avec une distance de l'origine inférieur à tmp
    		else if(this.verif[i] == NTREATED && tmp < min){
    			min = tmp;
    			index = i;
    		}
    	}
        System.out.println( "index: "+index+ "min :"+min );
    	return index;
    }
    /**
      * Actualise les pères et les pcc des sommets voisins de vertex
      */
    private void treatment(Matrice A, int vertex){
    	int i;
    	for(i=0; i<column; i++){
    		//si i  traité ou n'existe pas un arc de vertex à i
    		if(this.verif[i]==TREATED || A.getVal(vertex,i) == NTREATED)
    			continue;
    			//si la plus courte distance actuel de i à l'origine est plus grande
    			//que celle de origine à vertex + vertex à i, ou que le sommet
    			//n'a pas encore était rencontré alors on remplace
			else if( this.dist[i] > (this.dist[vertex] + A.getVal(vertex, i))
				|| this.dist[i] == NTREATED){
				this.dist[i] = this.dist[vertex] + A.getVal(vertex, i);
				this.father[i] = vertex;
                System.out.println("vertex"+vertex);
			}
    	}
    }
    /**
      * Retourne vrai si tous les sommets ont étaient traités
      */
    public boolean finished(){
        for(int i: this.verif){
            if(i!=1){
                return false;
            }
        }
        return true;
    }

    /**
      * Renvoie le plus court chemin de at à to
      */

    public int[] way(Matrice A, int at, int to){
    	calcul(A, at);
    	int father;
    	int []theway = new int[column];//tableau du chemin
    	theway[0] = to;
    	theway[1] = father = this.father[to];
    	int i = 2;

    	while(father != -1){
    		father = this.father[father];
    		theway[i] = father;
    		i++;
    	}
    	theway = ArrayUtils.subarray(theway,0,i-1);//i-1 pour pas prendre le pere -1
    	ArrayUtils.reverse(theway);
    	return theway;
    }

    /**
      * Affiche le tableau de dikjsra tous les 20 colonnes
      */
    public void printTbl_Dikjstra(){
        int i,j,k,l;
        i = j = k = l = 0;
        while(i < father.length){
            //sommets
            System.out.print("sommet :");
            while(i<father.length && i>=0){//i>= 0 evite les erreurs si tableau est de taile inferieur à 20
                System.out.print(" "+i);
                if(i%20 == 0 && i!=0)
                    break;
                i++;
            }


            //peres
            System.out.println();
            System.out.print("Pere :  ");
            while(j<father.length){
                if(this.verif[j] == TREATED){
                    System.out.print(" "+father[j]);
                    if(j%20 == 0 && j!=0)
                        break;
                }
                j++;
            }

            //distances
            System.out.println();
            System.out.print("Dist :  ");
            while(k<dist.length){
                System.out.print(" "+dist[k]);
                if(k%20 == 0 && k!=0)
                    break;
                k++;
            }
        }
        System.out.println();
    }

}
