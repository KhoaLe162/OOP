

package oop;


import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Created by  on 6/25/2016.
 */
public class Peashooter extends Plant {

    public Timer peaShoot;


    public Peashooter(GamePanel gamePanel,int posX,int posY) {
        
        super(gamePanel,posX,posY);
        
        peaShoot = new Timer(2800,(ActionEvent e) -> {
                       
            if(gamePanel.laneZombies.get(posY).size() > 0) {
                gamePanel.lanePeas.get(posY).add(new Pea(gamePanel, posY, 320 + this.posX * 100));
            }
        });
        peaShoot.start();
    }

    @Override
    public void stop(){
        peaShoot.stop();
    }

}

