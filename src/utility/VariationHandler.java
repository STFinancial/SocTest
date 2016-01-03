package utility;

import java.util.Random;

import sex.Sex;

public final class VariationHandler {
	private static Random random = new Random();
	
	private static Sex[] sexes = Sex.values();
	
	public static Sex getRandomSex() {
		return sexes[random.nextInt(2)];
	}
	
	public static float applyIntelligenceVariation(float baseIntelligence) {
		baseIntelligence += random.nextGaussian() * SimConstants.INTELLIGENCE_STD_DEV;
		return baseIntelligence;
//		if (random.nextInt(1000) > SimConstants.INTELLIGENCE_ANOMALY_THRESHOLD) {
//			return baseIntelligence;
//		} else {
//			return baseIntelligence + ((2 * random.nextInt(2)) - 1) * SimConstants.INTELLIGENCE_ANOMALY_MAGNITUDE;
//		}
	}
	
	//public static float applyPhysicalVariation(float )
}
