package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

import utility.SimObject;

public final class World extends JComponent {
	private static final long serialVersionUID = -6704749150768820789L;

	private WorldPanel worldPanel;
	
	/* Arrays in the form Z, Y, X */
	private WorldBlock[][][] blockLayer;
	private SimObject[][][] objectLayer;
	private SimObject[][][] newObjectLayer;
	
	public World() {
		objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		blockLayer = new WorldBlock[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		newObjectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	World(SimObject[][][] objectLayer, WorldBlock[][][] blockLayer) {
		this.objectLayer = objectLayer;
		this.blockLayer = blockLayer;
		newObjectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	void runForever() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					if (objectLayer[0][y][x] != null) {
						Random r = new Random();
						Direction d = Direction.values()[r.nextInt(4)];
						switch (d) {
						case DOWN:
							if (y < WorldConstants.WORLD_Y - 1) {
								newObjectLayer[0][y + 1][x] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;
						case LEFT:
							if (x > 0) {
								newObjectLayer[0][y][x - 1] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;
						case RIGHT:
							if (x < WorldConstants.WORLD_X - 1) {
								newObjectLayer[0][y][x + 1] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;	
						case UP:
							if (y > 0) {
								newObjectLayer[0][y - 1][x] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;
						}
					}
				}
			}
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					objectLayer[0][y][x] = newObjectLayer[0][y][x];
				}
			}
			
			worldPanel.repaint();
		}
	}
	
	void setPanel(WorldPanel panel) {
		this.worldPanel = panel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int pixelSize = worldPanel.getPixelSize();
		for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
			for (int x = 0; x < WorldConstants.WORLD_X; x++) {
				if (((WorldBlock) blockLayer[0][y][x]).getType() != WorldBlockType.EMPTY) {
					g.setColor(Color.BLACK);
					g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);						
				}
				if (objectLayer[0][y][x] != null) {
					g.setColor(Color.RED);
					g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
				}
			}
		}
	}
}
