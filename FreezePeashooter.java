
package oop;


import java.awt.event.ActionEvent;
import javax.swing.*;

public class FreezePeashooter extends Plant {

    public Timer freezePeaShoot;


    public FreezePeashooter(GamePanel gamePanel,int posX,int posY) {
        super(gamePanel,posX,posY);
        freezePeaShoot = new Timer(2000,(ActionEvent e) -> {
            
            
            if(gamePanel.laneZombies.get(posY).size() > 0) {
                
                gamePanel.lanePeas.get(posY).add(new FreezePea(gamePanel, posY, 300 + this.posX * 100));
            }
        });
        freezePeaShoot.start();
    }

    @Override
    public void stop(){
        freezePeaShoot.stop();
    }

}
