package apptest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import sex.Sex;
import simobject.agent.Agent;
import simobject.agent.AgentFemale;
import simobject.agent.AgentMale;
import simobject.agent.Mater;
import utility.VariationHandler;

public class AppTest {

	@Test
	public void testChildSexEvenness() {
		ArrayList<Agent> males = new ArrayList<Agent>();
		ArrayList<Agent> females = new ArrayList<Agent>();
		males.add(Mater.generateRandomAgent(Sex.MALE));
		females.add(Mater.generateRandomAgent(Sex.FEMALE));
		Random random = new Random();
		
		Agent m;
		Agent f;
		Agent c;
		Sex childSex;
		for (int i = 0; i < 500000; i++) {
			m = males.get(random.nextInt(males.size()));
			f = females.get(random.nextInt(females.size()));
			
			c = Mater.makeChild((AgentFemale) f, (AgentMale) m);
			childSex = c.getSex();
			if (childSex == Sex.MALE) {
				males.add(c);
			} else {
				females.add(c);
			}
		}
		assertTrue(Math.abs(males.size() - females.size()) < 3000);
	}
	
	@Test
	public void testRandomAgentGen() {
		Agent kidA = Mater.generateRandomAgent(VariationHandler.getRandomSex());
		assertTrue(kidA.getIntelligence() < 150);
		assertTrue(kidA.getPhysical() < 156.25);
	}
	
	@Test
	public void testRandomAgentPop() {
		int numGens = 10000;
		
		Agent current;
		double intAvg;
		double phyAvg;
		
		double totalInt = 0;
		double totalPhy = 0;
		for (int i = 0; i < numGens; i++) {
			current = Mater.generateRandomAgent(Sex.MALE);
			totalInt += current.getIntelligence();
			totalPhy += current.getPhysical();
		}
		intAvg = totalInt / numGens;
		phyAvg = totalPhy / numGens;
		assertTrue(Math.abs(intAvg - 100) < 5);
		assertTrue(Math.abs(phyAvg - 100) < 5);
		
		totalInt = 0;
		totalPhy = 0;
		for (int i = 0; i < numGens; i++) {
			current = Mater.generateRandomAgent(Sex.FEMALE);
			totalInt += current.getIntelligence();
			totalPhy += current.getPhysical();
		}
		intAvg = totalInt / numGens;
		phyAvg = totalPhy / numGens;
		assertTrue(Math.abs(intAvg - 100) < 5);
		assertTrue(Math.abs(phyAvg - 100) < 5);
		
	}

}
