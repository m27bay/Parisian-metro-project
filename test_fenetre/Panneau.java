import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Panneau extends JPanel { 
  public void paintComponent(Graphics g){
    //Vous verrez cette phrase chaque fois que la méthode sera invoquée
    // System.out.println("Je suis exécutée !"); 
    // g.fillOval(20, 20, 75, 75);
    int x1 = this.getWidth()/4;
    int y1 = this.getHeight()/4;                      
    // g.fillOval(x1, y1, this.getWidth()/2, this.getHeight()/2);
    // g.drawOval(x1, y1, this.getWidth()/2, this.getHeight()/2);

    try {
    	Image img = ImageIO.read(new File("plan.jpg"));
    	// g.drawImage(img, 0, 0, this);
    	//Pour une image de fond
    	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }              


    	//x1, y1, x2, y2
    // g.drawLine(0, 0, this.getWidth(), this.getHeight());
    // g.drawLine(0, this.getHeight(), this.getWidth(), 0);

    // g.drawString("DIOOOOOOOOOOOOOOOOOOOO", this.getWidth()/4, this.getHeight()/2);
    

  }               

}