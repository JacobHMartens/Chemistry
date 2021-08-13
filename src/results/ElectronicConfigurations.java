package results;

import static results.AtomsAndMolecules.getAtomNumberFromElement;
import static data.DataReader.*;

public class ElectronicConfigurations {
	
	public static String getElectronicConfig(String element) {
		int ion = 0;
		int startIdx = element.indexOf("(") + 1;
		if (startIdx != -1) {
			int endIdx = element.indexOf(")") - 1;
			ion = Integer.valueOf(element.substring(startIdx, endIdx));
			if (element.charAt(endIdx) == '+')
				ion *= -1;
			element = element.substring(0, startIdx-1);	
		}
		int atomNumber = getAtomNumberFromElement(element) + ion;
		
		String electroConfigShort = atomicNumberToElectroConfig.get(atomNumber);
		String electroConfigFull = electroConfigShort;
		while (electroConfigFull.charAt(0) == '[') {
			int nobleGasAtomicNumber = getAtomNumberFromElement(electroConfigFull.substring(1, 3));
			electroConfigFull = atomicNumberToElectroConfig.get(nobleGasAtomicNumber) + electroConfigFull.substring(4);
		}
	    return electroConfigShort + "   " + electroConfigFull;
	}
	// This method is currently incorrect for ions
}
