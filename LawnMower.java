
package oop;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class LawnMower extends JPanel  { 
    
    ArrayList<LawnMower> mowers = GamePanel.getMowerList();
    
    GamePanel gp;
    Image mowerImage;
    
    
    
    public boolean isMoving = false;
    
    int myX = 180;
    int myY;
    int myLane;
    int lastX;
    
    public LawnMower(GamePanel parent, int myLane){
        this.gp = parent;
        this.myLane = myLane;
    }
    
    
    
    public void move(){
        if(isMoving){
            myX += 10;
            
        }
        
        
        
        Rectangle lRect = new Rectangle(myX,myLane,80,80);
        for (int i = 0; i < gp.laneZombies.get(myLane).size(); i++) {
            Zombie z = gp.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX, myY,400,120);
              
            if(lRect.intersects(zRect)){
                isMoving = true;               
                    gp.laneZombies.get(myLane).remove(i);
                  
                   
                
                
            }
            if(myX > 1000){
            gp.mowers.get(myLane).remove(this);
        }
        }
         
        }
        
}
    
