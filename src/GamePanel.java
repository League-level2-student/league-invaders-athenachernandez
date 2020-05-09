import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font subTitleFont;
    Timer frameDraw;
    
    GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	subTitleFont = new Font("Arial", Font.PLAIN, 29);
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
    }
    
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		} else if(currentState == GAME){
		    drawGameState(g);
		} else if(currentState == END){
		    drawEndState(g);
		}
	}
	void updateMenuState() {  }
	void updateGameState() {  }
	void updateEndState()  {  }
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("LEAGUE INVADERS", 30, 100);
		g.setFont(subTitleFont);
		g.drawString("Press ENTER to start", 120, 300);
		g.setFont(subTitleFont);
		g.drawString("Press SPACE for instructions", 70, 500);
	}
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
	}
	void drawEndState(Graphics g) {  
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 125, 100);
		g.setFont(subTitleFont);
		g.drawString("You killed enemies", 130, 300);
		g.setFont(subTitleFont);
		g.drawString("Press ENTER to restart", 100, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		} else if(currentState == GAME) {
		    updateGameState();
		} else if(currentState == END) {
		    updateEndState();
		}
		System.out.println("action");
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}
		    if (e.getKeyCode()==KeyEvent.VK_UP) {
		        System.out.println("UP");
		    } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		        System.out.println("DOWN");
		    } else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		        System.out.println("LEFT");
		    } else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		        System.out.println("RIGHT");
		    }
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
