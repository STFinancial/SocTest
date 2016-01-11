package agent;

import sex.Sex;
import utility.VariationHandler;


/**
 * This class is responsible for taking care of
 * {@link Agent} reproduction.
 * @author Timothy
 * @see VariationHandler
 */
public final class Mater {
	private static AttributeBuilder builder = AttributeBuilder.getInstance();
	
	
	public static Agent generateRandomAgent(Sex sex) {
		Agent randAgent = (sex == Sex.FEMALE ? new AgentFemale() : new AgentMale());
		builder.begin();
		builder.setIntelligence(VariationHandler.getRandomIntelligence());
		builder.setPhysical(VariationHandler.getRandomPhysical());
		randAgent.setAttributeHolder(builder.finishBuild());
		return randAgent;
	}
	
	public static Agent makeChild(AgentFemale female, AgentMale male) {
		Agent child = VariationHandler.getRandomSex() == Sex.MALE ? new AgentMale() : new AgentFemale();
		
//		float childIntelligence = VariationHandler.applyIntelligenceVariation((female.intelligence.getBaseIntelligence() + male.intelligence.getBaseIntelligence()) / 2);
//		child.intelligence = new Intelligence(childIntelligence);
		return child;
	}
}
