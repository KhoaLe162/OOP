/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Sun extends JPanel implements MouseListener {

    GamePanel gamePanel;
    
    
    Image sunImage;

    int myX;
    int myY;
    int endY;

    int time = 200;

    public Sun(GamePanel gamePanel,int startX,int startY,int endY){
        this.gamePanel = gamePanel;
        
        this.endY = endY;
        
        setSize(80,80);
        setOpaque(false);
        
        myX = startX;
        
        myY = startY;
        
        setLocation(myX,myY);
        
        sunImage = new ImageIcon(this.getClass().getResource("/Images/sun.png")).getImage();
        
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage,0,0,null);
    }

    public void drop(){
        if(myY < endY) {
            
            myY+= 5;
            
        }else{
            time--;
            if(time<0){
                gamePanel.remove(this);
                gamePanel.sunList.remove(this);
            }
        }
        
        setLocation(myX,myY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        gamePanel.setSunScore(gamePanel.getSunScore()+25);
        
        gamePanel.remove(this);
        
        gamePanel.sunList.remove(this);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
