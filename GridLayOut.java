
package oop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GridLayOut extends JPanel implements MouseListener {

    public Plant locatePlant;
    
    ActionListener action;

    public GridLayOut(){
        setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        setSize(100,120);
        
        addMouseListener(this);
        
    }

   

    public void setPlant(Plant p){
        locatePlant = p;
    }

    public void removePlant(){
        locatePlant.stop();
        locatePlant = null;
        
    }

    public boolean checkCollision(int posX){
        return (posX > getLocation().x) && (posX < getLocation().x + 50);
    }

    public void setAction(ActionListener action){
        this.action =  action;;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(action != null){
            action.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    
}