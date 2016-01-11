package world;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import utility.SimObject;

public final class World extends JComponent {
	/* Arrays in the form Z, Y, X */
	private SimObject[][][] objectLayer;
	
	public World() {
		objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	World(SimObject[][][] objectLayer) {
		this.objectLayer = objectLayer;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
			for (int x = 0; x < WorldConstants.WORLD_X; x++) {
				if (((WorldBlock) objectLayer[0][y][x]).getType() != WorldBlockType.EMPTY) {
					g.fillRect(x, y, WorldConstants.WORLD_PIXEL_SIZE, WorldConstants.WORLD_PIXEL_SIZE);						
				}
			}
		}
	}
}
