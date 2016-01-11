package application;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import agent.AgentFemale;
import agent.AgentMale;
import agent.Mater;
import sex.Sex;
import world.WorldGenerator;
import world.WorldPanel;

public class Application {
	
	
	public static void main(String[] args) {
		Application app = new Application();
		app.guiTest();
		
	}

	private void guiTest() {
		JFrame frame = new JFrame();
		frame.add(new WorldPanel(WorldGenerator.generateRandomWorld()));
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.repaint();
	}
}
