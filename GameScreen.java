
package oop;

import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameScreen extends JFrame{
    
    static GameScreen gameScreen;
     

    private Menu menu;
    
  

    
 
    enum PlantType{
        None, Sunflower,
        Peashooter, FreezePeashooter,
        Walnut

    }
    
    enum IsShovel{
        None, Shovel
    }
    

    //PlantType activePlantingBrush = PlantType.None;
    
    public GameScreen(){
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        

        JLabel sunScoreBoard = new JLabel("Score");
        
        sunScoreBoard.setLocation(20,5);
        
        sunScoreBoard.setSize(60,60);
        getLayeredPane().add(sunScoreBoard,new Integer(2));

        GamePanel gamePanel = new GamePanel(sunScoreBoard);
        gamePanel.setLocation(0,0);
        getLayeredPane().add(gamePanel,new Integer(0));
        
        Shovel shovel = new Shovel(new ImageIcon(this.getClass().getResource("/Images/shovel.png")).getImage());
        shovel.setLocation(150,8);          
        getLayeredPane().add(shovel,new Integer(2));
        shovel.shovelAction((ActionEvent e) -> {
            gamePanel.pickingShovel = IsShovel.Shovel;
        });  
         
        
        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("/Images/sunFlowerButton.png")).getImage());
        sunflower.setLocation(8, 100);
        sunflower.setAction((ActionEvent e) -> {
            gamePanel.pickingPlant = PlantType.Sunflower;
        });
        getLayeredPane().add(sunflower,new Integer(2));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("/Images/peaShooterCard.png")).getImage());
        peashooter.setLocation(8,195);
        peashooter.setAction((ActionEvent e) -> {
            gamePanel.pickingPlant = PlantType.Peashooter;
        });
        getLayeredPane().add(peashooter,new Integer(2));

        PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("/Images/frozenPeaShooterButton.png")).getImage());
        freezepeashooter.setLocation(8,290);
        freezepeashooter.setAction((ActionEvent e) -> {
            gamePanel.pickingPlant = PlantType.FreezePeashooter;
        });
        getLayeredPane().add(freezepeashooter,new Integer(2));

        PlantCard Walnut = new PlantCard(new ImageIcon(this.getClass().getResource("/Images/wallnutButton.png")).getImage());
        Walnut.setLocation(8,385);
        Walnut.setAction((ActionEvent e) -> {
            gamePanel.pickingPlant = PlantType.Walnut;
        });
        getLayeredPane().add(Walnut,new Integer(2));
        
        setResizable(false);
        setVisible(true);
        
    }
    public GameScreen(boolean bool) {
        menu = new Menu();
        
        menu.setLocation(0,0);

        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu,new Integer(0));
        
        
        this.addMouseListener(new MouseInput());
        
        
        
        menu.repaint();
        setResizable(false);
        setVisible(true);
          
    }
    
    
    
    public static void begin() {
        gameScreen.dispose();
        
        gameScreen = new GameScreen();
        
    }
 
    public static void main(String[] args) {
          
          gameScreen = new GameScreen(true);
          
          
          
    }

}
