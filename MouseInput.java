
package oop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class MouseInput implements MouseListener {

    GamePanel gamePanel;
    
    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
         
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        int myX = e.getX();
        int myY = e.getY();
        
        if(myX >= 520 && myX <= 950 ){
            if(myY >= 150  && myY <= 300){
                JOptionPane.showInputDialog(gamePanel,"Welcome to PlantvsZombies" + '\n' 
                                                  + '\t' + "Please enter your name");
                GameScreen.begin();
            }
        }
        
        if(myX >= 850 && myX <= 1000){
            if(myY >= 600 && myY <= 700){
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
         
    }
    
}
