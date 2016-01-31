package application;

import world.World;
import world.WorldGenerator;

/**
 * The top level class that is at the top of the
 * hierarchy for classes of this application.
 * @author Timothy
 *
 */
public final class Application {
	private GUI gui;
	
	public static void main(String[] args) {
		Application app = new Application();
		app.run();
	}
	
	private void run() {
		World world = WorldGenerator.generateRandomWorld();
		gui = new GUI(this, world);
		world.runForever();
	}
}
