// awt
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

// swing
import javax.swing.JPanel;

// io
import java.io.File;
import java.io.IOException;

// imageio
import javax.imageio.ImageIO;

public class Panneau extends JPanel {
  // public void paintComponent(Graphics g){
   
  //   // g.fillOval(20, 20, 75, 75);
  //   int x1 = this.getWidth()/4;
  //   int y1 = this.getHeight()/4;
  //   // g.fillOval(x1, y1, this.getWidth()/2, this.getHeight()/2);
  //   // g.drawOval(x1, y1, this.getWidth()/2, this.getHeight()/2);

  //   try {
  //   	Image img = ImageIO.read(new File("picture/plan.jpg"));
  //   	// g.drawImage(img, 0, 0, this);
  //   	//Pour une image de fond
  //   	g.drawImage(img, 0, 0, 1014, 709, this);
  //   } catch (IOException e) {
  //     e.printStackTrace();
  //   }


  //   	//x1, y1, x2, y2
  //   // g.drawLine(0, 0, this.getWidth(), this.getHeight());
  //   // g.drawLine(0, this.getHeight(), this.getWidth(), 0);

  //   //g.drawString("Start station :", 1050, 50);
  //   g.drawString("End station :", 1051, 100);
  //   g.drawString("Travel :", 1050, 150);
  //   g.drawString("Time:", 1050, 200);
    
  //   }

  public void paintComponent(Graphics g){                
    Font font = new Font("Courier", Font.BOLD, 20);

     try {
     Image img = ImageIO.read(new File("picture/plan.jpg"));
     //Pour une image de fond
     g.drawImage(img, 0, 0, 1014, 709, this);
    } catch (IOException e) {
      e.printStackTrace();
    }
 
     g.setFont(font);
     g.setColor(Color.black);    

     g.drawString("Travel Plan" , 1100, 15);
     g.drawString("Start station : " , 1050, 50);
     g.drawString("End station :", 1051, 100);
     g.drawString("Time :", 1050, 150);
     g.drawString("Travel :", 1050, 200); 
    
    }        

}
