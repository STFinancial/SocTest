package application;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import sex.Sex;
import simobject.agent.Agent;
import simobject.agent.AgentFemale;
import simobject.agent.AgentMale;
import simobject.agent.Mater;
import world.World;
import world.WorldGenerator;
import world.WorldPanel;

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
