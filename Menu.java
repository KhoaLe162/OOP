
package oop;


import java.awt.*;
import javax.swing.*;




public class Menu extends JPanel {
    
    

    Image menuImage;
    
    public Menu() {     
       
        setSize(1012, 785);
        
        menuImage  = new ImageIcon(this.getClass().getResource("/Images/Mainmenu.png")).getImage();
        
    }

  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage,0,0,null);
    
    }
   
    
    }

    


    
 
  



