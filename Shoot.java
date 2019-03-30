
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Shoot extends JFrame {
	
	static Players p = new Players();
	
	private Graphics dbg;
	private Image Img;
	

	

	public void paint(Graphics g) {
		Img = createImage(getWidth(), getHeight());
		dbg = Img.getGraphics();
		paintComponent(dbg);
		g.drawImage(Img, 0, 0, this);
	}
	
	public void paintComponent(Graphics g) {
		
		p.draw(g);
		repaint();
	}
	
	public Shoot() {
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		addKeyListener(new MOVE());
		
	}
	
	public class MOVE extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);

		}

		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);

		}
	}

	public static void main(String[] args) {

		Shoot shoot = new Shoot();
		Thread Players = new Thread(p);
		Players.start();

	}

}