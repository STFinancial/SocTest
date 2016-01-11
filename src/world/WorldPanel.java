package world;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class WorldPanel extends JPanel {
	private World world;
	
	public WorldPanel(World world) {
		this.world = world;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		world.paintComponent(g);
	}
}
