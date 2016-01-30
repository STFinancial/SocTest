package simobject.agent;

import sex.Sex;
import simobject.agent.attributes.AttributeBuilder;
import utility.VariationHandler;
import world.World;


/**
 * This class is responsible for taking care of
 * {@link Agent} reproduction.
 * @author Timothy
 * @see VariationHandler
 */
public final class Mater {
	private static AttributeBuilder builder = AttributeBuilder.getInstance();
	private static World world = null;
	
	public static void setWorld(World newWorld) {
		world = newWorld;
	}
	
	public static Agent generateRandomAgent(Sex sex) {
		Agent randAgent = (sex == Sex.FEMALE ? new AgentFemale(world) : new AgentMale(world));
		builder.begin();
		builder.setIntelligence(VariationHandler.getRandomIntelligence());
		builder.setPhysical(VariationHandler.getRandomPhysical());
		randAgent.setAttributeHolder(builder.finishBuild());
		return randAgent;
	}
	
	public static Agent makeChild(AgentFemale female, AgentMale male) {
		Agent child = VariationHandler.getRandomSex() == Sex.MALE ? new AgentMale(world) : new AgentFemale(world);
		
//		float childIntelligence = VariationHandler.applyIntelligenceVariation((female.intelligence.getBaseIntelligence() + male.intelligence.getBaseIntelligence()) / 2);
//		child.intelligence = new Intelligence(childIntelligence);
		return child;
	}
}
