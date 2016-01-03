package application;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import agent.AgentSexMachine;
import sex.Sex;

public class Application {

	public static void main(String[] args) {
		Application app = new Application();
		app.runTest();
	}

	private void runTest() {
		ArrayList<Agent> males = new ArrayList<Agent>();
		ArrayList<Agent> females = new ArrayList<Agent>();
		males.add(AgentSexMachine.generateRandomAgent(Sex.MALE));
		females.add(AgentSexMachine.generateRandomAgent(Sex.FEMALE));
		Random random = new Random();
		
		Agent m;
		Agent f;
		Agent c;
		Sex childSex;
		for (int i = 0; i < 500000; i++) {
			m = males.get(random.nextInt(males.size()));
			f = females.get(random.nextInt(females.size()));
			
			c = AgentSexMachine.makeChild(f, m);
			childSex = c.getSex();
			if (childSex == Sex.MALE) {
				males.add(c);
			} else {
				females.add(c);
			}
		}
		System.out.println("Number of males: " + males.size());
		System.out.println("Number of females: " + females.size());
	}
}
