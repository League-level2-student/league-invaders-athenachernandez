import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	void update() {
		for (Alien alien : aliens) {
			alien.update();
			if (alien.y > LeagueInvaders.HEIGHT - 75) {
				alien.isActive = false;
			}
		}
		for (Projectile projectile : projectiles) {
			projectile.update();
			if (projectile.y > LeagueInvaders.HEIGHT - 75) {
				projectile.isActive = false;
			}
		}
	}
	void draw(Graphics g) {
		rocket.draw(g);
		for (Alien alien : aliens) {
			alien.draw(g);
		}
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
		}
	}
	void purgeObjects() {
		for(int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		for(int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
	}

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
	}
}
