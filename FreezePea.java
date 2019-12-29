
package oop;


import java.awt.*;


public class FreezePea extends Pea {

    public FreezePea(GamePanel parent,int lane,int startX){
        super(parent,lane,startX);
    }

    @Override
    public void move(){
        Rectangle pRect = new Rectangle(posX,130+lane*120,28,28);
        
        for (int i = 0; i < gamePanel.laneZombies.get(lane).size(); i++) {
            
            Zombie zombie = gamePanel.laneZombies.get(lane).get(i);
            
            Rectangle zRect = new Rectangle(zombie.posX,109 + lane*120,400,120);
            if(pRect.intersects(zRect)){
                
                zombie.health -= 300;
                
                zombie.slow();
                
                if(zombie.health < 0){         
                    
                    gamePanel.laneZombies.get(lane).remove(i);
                    
                }
                gamePanel.lanePeas.get(lane).remove(this);
                
            }
        }
        
        posX += 15;
    }

}

