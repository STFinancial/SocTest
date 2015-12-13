package agent;

import utility.RandomGenerator;

public final class AgentSexMachine {
	
	
	public static Agent makeChild(Agent female, Agent male) {
		Agent child = new Agent(RandomGenerator.getRandomSex());
		
		float childIntelligence = RandomGenerator.applyIntelligenceVariation((female.intelligence.getBaseIntelligence() + male.intelligence.getBaseIntelligence()) / 2);
		child.intelligence = new Intelligence(childIntelligence);
	}
}
