
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Players implements Runnable {

	static MyCircle c1 = new MyCircle();
	static MyCircle c2 = new MyCircle();

	int x, y, x2, y2, bx, by, bx2, by2;;
	boolean Fire, Fire2, shot = false, shot2 = false, GMOver1 = false, GMOver2 = false;
	int xDirection, xDirection2;

	Rectangle bullet;
	Rectangle bullet2;

	public Players() {
		c2.setY(50);
		c1.setY(250);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);

		g.fillOval(c1.getX(), c1.getY(), c1.getRadius(), c1.getRadius());
		if (shot) {
			g.setColor(Color.BLUE);
			g.fillRect(bullet.x, bullet.y, 10, 10);

		}

		g.setColor(Color.RED);
		g.fillOval(c2.getX(), c2.getY(), c2.getRadius(), c2.getRadius());
		if (shot2) {
			g.setColor(Color.RED);
			g.fillRect(bullet2.x, bullet2.y, 10, 10);
		}
		if (GMOver1 == true) {
			g.setColor(Color.RED);
			g.drawString("GAME OVER! RED WINS", 200, 150);
		}
		if (GMOver2 == true) {
			g.setColor(Color.BLUE);
			g.drawString("GAME OVER! BLUE WINS", 200, 150);

		}

	}

	public void move() {

		c1.setX(c1.getX() + xDirection);
		if (c1.getX() <= 0)
			c1.setX(0);
		if (c1.getX() >= 360)
			c1.setX(360);

		c2.setX(c2.getX() + xDirection2);
		if (c2.getX() <= 0)
			c2.setX(0);
		if (c2.getX() >= 360)
			c2.setX(360);
	}

	public void setXDirection(int xdir) {
		xDirection = xdir;

	}

	public void setXDirection2(int xdir) {

		xDirection2 = xdir;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// for Player1
		if (keyCode == e.VK_LEFT) {
			setXDirection(-1);
		}

		if (keyCode == e.VK_RIGHT) {
			setXDirection(1);
		}

		if (keyCode == e.VK_SPACE) {
			if (bullet == null)
				Fire = true;
			if (Fire) {

				by = c1.getY();
				bx = c1.getX();
				bullet = new Rectangle(bx, by, 3, 5);
				shot = true;
			}
		}

		// for Player2
		if (keyCode == e.VK_A) {
			setXDirection2(-1);
		}
		if (keyCode == e.VK_D) {
			setXDirection2(1);
		}
		if (keyCode == e.VK_F) {
			if (bullet2 == null)
				Fire2 = true;
			if (Fire2) {

				by2 = c2.getY();
				bx2 = c2.getX();
				bullet2 = new Rectangle(bx2, by2, 3, 5);
				shot2 = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();
		// for Player1
		if (keyCode == e.VK_LEFT) {
			setXDirection(0);
		}
		if (keyCode == e.VK_RIGHT) {
			setXDirection(0);
		}
		if (keyCode == e.VK_SPACE) {

			Fire = false;
			if (bullet.y <= -5) {
				bullet = new Rectangle(0, 0, 0, 0);
				shot = false;
				Fire = true;
			}
		}
		// for Player2
		if (keyCode == e.VK_A) {
			setXDirection2(0);
		}
		if (keyCode == e.VK_D) {
			setXDirection2(0);
		}
		if (keyCode == e.VK_F) {

			Fire2 = false;
			if (bullet2.y >= 200) {
				bullet2 = new Rectangle(0, 0, 0, 0);
				shot2 = false;
				Fire2 = true;
			}
		}
	}

	public void KeyTyped(KeyEvent e) {
	}

	public void shoot() {
		// collision check for Player2
		if (shot) {
			bullet.y--;
			for (int i = 0; i < c2.getRadius(); ++i) {
				if (( bullet.y == c2.getY() + i)
						&& (bullet.x == c2.getX() + i || bullet.x == c2.getX() - i)) {
					if (c2.getRadius() - 5 <= 10) {
						c2.setRadius(0);
						GMOver2 = true;

					}

					c2.setRadius(c2.getRadius() - 5);
				}
			}
		}
		// collision check for Player1
		if (shot2) {
			bullet2.y++;
			for (int i = 0; i < c1.getRadius(); ++i) {
				if (( bullet2.y == c1.getY() - i)
						&& (bullet2.x == c1.getX() + i || bullet2.x == c1.getX() - i)) {
					if (c1.getRadius() - 5 <= 10) {
						c1.setRadius(0);
						GMOver1 = true;

					}
					c1.setRadius(c1.getRadius() - 5);

				}
			}

		}

	}

	public void run() {
		try {
			while (true) {
				if (GMOver1 == false && GMOver2 == false)
					shoot();
				move();
				Thread.sleep(3);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}