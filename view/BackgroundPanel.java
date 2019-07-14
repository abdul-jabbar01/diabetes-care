package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
 
//Background Image Panel used across all the JFrames
public class BackgroundPanel extends JPanel{
  BufferedImage bi = null;
  
  @Override
  public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.drawImage(bi, 0, 0, this);
    }
  
  
  public BackgroundPanel(String imageURL){
    try{
    	
      String path=imageURL==null ? "../media/background.jpg": imageURL;
      bi=ImageIO.read(getClass().getResource(path));
      this.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
      this.setBounds(100, 100, 676, 414);
      
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}