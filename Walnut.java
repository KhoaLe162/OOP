
package oop;

import java.awt.*;
import javax.swing.*;

public class Walnut extends Plant {
    
    private int halflife = 200;
    
    
    public Walnut(GamePanel gamePanel, int posX, int posY) {
        super(gamePanel, posX, posY);
        setHealth(halflife*5);
    }
    
    
}
