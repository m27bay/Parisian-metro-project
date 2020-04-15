// awt
import java.awt.Color;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {
  public Fenetre(){
    this.setTitle("Ma première fenêtre Java");
    this.setSize(900, 900);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setlocation(int x, int y) x de gauche a droite, y de haut en bas
    // empecher redim fenetre : setREsizable(boolean b) : false
    // garder fenetre premier plan : false pour non true pour oui : setAlwaysOnTop(boolean b)
    // retirer contours et boutons de controle : setUndecorated(boolean b)

     //Instanciation d'un objet JPanel
    JPanel pan = new JPanel();
     //Définition de sa couleur de fond
    // pan.setBackground(Color.ORANGE);
    //On prévient notre JFrame que notre JPanel sera son content pane
    // this.setContentPane(pan);
    this.setContentPane(new Panneau());


    this.setVisible(true);


  }

}
