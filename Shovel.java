
package oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Shovel extends JPanel implements MouseListener {
    
    GamePanel gamePanel;
    
    Image shovelImage;
    
    private boolean mouseClicked;
    
    ActionListener action;
       
    
    public Shovel(Image shovelImage){
        setSize(64,90);
        this.shovelImage = shovelImage;
        addMouseListener(this);
        
    }
    
    public void shovelAction(ActionListener a){
        this.action = a;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(shovelImage,0,0,null);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
      
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
         if(action != null){
            action.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
  
    
}
