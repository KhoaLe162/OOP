
package oop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Sunflower extends Plant {

    Timer sunDrop;

    public Sunflower(GamePanel gamePanel,int posX,int posY) {
        super(gamePanel, posX, posY);
        sunDrop = new Timer(3000,(ActionEvent e) -> {
            Sun sta = new Sun(gamePanel,260 + posX*100,110 + posY*120,130 + posY*120);
            gamePanel.sunList.add(sta);
            gamePanel.add(sta,new Integer(1));
        });
        sunDrop.start();
    }
    
    @Override
    public void stop(){
       sunDrop.stop();
    }

}

