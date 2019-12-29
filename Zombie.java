
package oop;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class Zombie {

    public int health = 1500;

    private GamePanel gamePanel;

    public int posX = 1000;
    
    public int myLane;
    
    public boolean isMoving = true;
    
    public int isSlowing = 0;

    public Zombie(GamePanel gamePanel,int lane){
        this.gamePanel = gamePanel;
        myLane = lane;
    }
    
    
    public void slow(){
        isSlowing = 1000;      
    }

    public void move(){
        if(isMoving) {
            
            boolean inCollision = false;
            
            GridLayOut grids = null;
            for (int i = myLane * 8; i < (myLane + 1) * 8; i++) {
                
                if (gamePanel.grid[i].locatePlant != null && gamePanel.grid[i].checkCollision(posX)) {
                    
                    inCollision = true;
                    
                    grids = gamePanel.grid[i];
                }
            }
            
            if (!inCollision) {
                if(isSlowing>0){
                    if(isSlowing % 2 == 0) {
                        posX--;
                    }
                    isSlowing--;
                }else {
                    posX -= 1;
                }
            } else {
                grids.locatePlant.health -= 5;
                if (grids.locatePlant.health < 0) {
                    grids.removePlant();
                }
            }
            if (posX < 100) {
                isMoving = false;
                
                
                
                JOptionPane.showMessageDialog(gamePanel,"YOU LOST" + '\n' 
                                                  + "Try again next time");
                
                
                System.exit(0);
                //GameScreen.gameScreen.dispose();
                //GameScreen.gameScreen = new GameScreen(true);
                
            }
        }
    }
    

    public static Zombie getZombie(String type,GamePanel gamePanel, int lane) {
        
        Zombie z = new Zombie(gamePanel, lane);
        
       switch(type) {
           case "NormalZombie" : z = new NormalZombie(gamePanel, lane);
                                 break;
           case "ConeHeadZombie" : z = new ConeHeadZombie(gamePanel, lane);
                                 break;
    }
       return z;
    }

}

