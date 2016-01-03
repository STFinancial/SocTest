package agent;

import sex.Sex;
import utility.VariationHandler;


/**
 * This class is responsible for taking care of
 * {@link Agent} reproduction.
 * @author Timothy
 *
 */
public final class AgentSexMachine {
	
	public static Agent generateRandomAgent(Sex sex) {
		return new Agent(sex);
	}
	
	public static Agent makeChild(Agent female, Agent male) {
		Agent child = new Agent(VariationHandler.getRandomSex());
		
//		float childIntelligence = VariationHandler.applyIntelligenceVariation((female.intelligence.getBaseIntelligence() + male.intelligence.getBaseIntelligence()) / 2);
//		child.intelligence = new Intelligence(childIntelligence);
		return child;
	}
}
