
package oop;


import javax.swing.*;
import java.awt.*;

public class Pea {
    GridLayOut grid;
    
    
    public int posX;
    protected GamePanel gamePanel;
    public int lane;

    public Pea(GamePanel parent,int lane,int posX){
        this.gamePanel = parent;
        this.lane = lane;
        this.posX = posX;
    }

    public void move(){
        Rectangle pRect = new Rectangle(posX,130+lane*120,28,28);
        for (int i = 0; i < gamePanel.laneZombies.get(lane).size(); i++) {
            
            Zombie zombie = gamePanel.laneZombies.get(lane).get(i);
            
            Rectangle zRect = new Rectangle(zombie.posX,109 + lane*120,400,120);
            
            if(pRect.intersects(zRect)){
                zombie.health -= 300;
                
                if(zombie.health < 0){
                    
                    
                    gamePanel.laneZombies.get(lane).remove(i);
                    

                }
                gamePanel.lanePeas.get(lane).remove(this);
               
            }
        }
      
        posX += 15;
    }

}

