
package oop;


public class Plant {

    public int health = 200;

    public int posX;
    public int posY;

    public GamePanel gamePanel;


    public Plant(GamePanel gamePanel,int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        this.gamePanel = gamePanel;
    }
    
    protected void setHealth(int plantHealth){
       this.health = plantHealth;
    }
    public void removePlant(){
        
    }
    
  

    public void stop(){}
    

}

