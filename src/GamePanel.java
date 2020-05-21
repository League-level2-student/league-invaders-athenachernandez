import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
	Timer alienSpawn;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager manager = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subTitleFont = new Font("Arial", Font.PLAIN, 29);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
		if (!rocket.isActive) {
			currentState = END;
		}
		manager.update();
	}

	void updateEndState() {
	}

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
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		}
		g.setColor(Color.WHITE);
		g.drawString("current score: " + manager.getScore(), 10, 10);
		manager.draw(g);
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
		g.drawString("your score: " + manager.getScore(), 200, 600);
	}

	void startGame() {
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				//alienSpawn.stop();
				rocket = new Rocketship(250, 700, 50, 50);
				manager = new ObjectManager(rocket);
				currentState = MENU;
			} else {
				if (currentState == GAME) {
					manager.addProjectile(rocket.getProjectile());
				}
				currentState++;
				startGame();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.up();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.down();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.left();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manager.addProjectile(rocket.getProjectile());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.up();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.down();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.left();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.right();
		}

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
