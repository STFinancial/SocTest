package application;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import world.World;
import world.WorldPanel;

/**
 * This class is responsible for holding and displaying
 * all of the JPanels which are part of the application.
 * @author Timothy
 */
public final class GUI {
	private final double ASPECT_RATIO = 5 / 3; /* Common European Aspect Ratio */
	private int GUI_DEFAULT_WIDTH = 1000;
	private int GUI_DEFAULT_HEIGHT = 600;
	private int WORLD_PANEL_DEFAULT_WIDTH = 600;
	private int WORLD_PANEL_DEFAULT_HEIGHT = 600;
	
	
	private Application application;
	private World world;
	
	private JFrame appFrame;
	private JPanel holdingPanel;
	private JPanel worldPanel;
	
	GUI(Application app, World world) {
		this.application = app;
		this.world = world;
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		/* Create Frames and Panels */
		appFrame = new JFrame("Soc Test");
		holdingPanel = new JPanel();
		worldPanel = new WorldPanel(world);
		
		/* Configure the JPanel on which the other panels are held */
		holdingPanel.setSize(new Dimension(GUI_DEFAULT_WIDTH, GUI_DEFAULT_HEIGHT));
		holdingPanel.setPreferredSize(new Dimension(GUI_DEFAULT_WIDTH, GUI_DEFAULT_HEIGHT));
		holdingPanel.setLayout(new FlowLayout());
		appFrame.add(holdingPanel);
		
		/* Configure the JPanel which displays the World */
		worldPanel.setSize(new Dimension(WORLD_PANEL_DEFAULT_WIDTH, WORLD_PANEL_DEFAULT_HEIGHT));
		worldPanel.setPreferredSize(new Dimension(WORLD_PANEL_DEFAULT_WIDTH, WORLD_PANEL_DEFAULT_HEIGHT));
		holdingPanel.add(worldPanel);
		
		/* Add each of the components to the Frame */
		appFrame.setSize(new Dimension(GUI_DEFAULT_WIDTH, GUI_DEFAULT_HEIGHT));
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setLocationRelativeTo(null);
		appFrame.setVisible(true);
		appFrame.repaint();
		
		world.runForever();
	}
}
