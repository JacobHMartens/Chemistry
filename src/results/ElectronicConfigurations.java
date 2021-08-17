package results;

import static results.BaseOperations.getIonChargeAndRoot;

import objects.Ion;

import static results.AtomsAndMolecules.getAtomNumberFromElement;
import static data.DataReader.*;

public class ElectronicConfigurations {
	
	public static String getElectronicConfig(String element) {
		Ion ionObj = getIonChargeAndRoot(element);
		int ion = ionObj.getIon();
		element = ionObj.getFormula();
		int atomNumber = getAtomNumberFromElement(element);
		if (ion < 0)
			atomNumber -= ion;
		String electroConfigShort = atomicNumberToElectroConfig.get(atomNumber);
		while (ion > 0) {
			if (atomNumber > 20 && atomNumber <= 30) {
				int idx4s = electroConfigShort.indexOf("4s");
				if (idx4s != -1) {
					int e4s = Integer.valueOf(electroConfigShort.substring(idx4s+2, idx4s+3));
					if (e4s > 0) {
						electroConfigShort = electroConfigShort.replace("4s"+e4s, "4s"+(--e4s));
					}
					electroConfigShort = electroConfigShort.replace("4s0", "");
				}
				else {
					int idx3d = electroConfigShort.indexOf("3d");
					int e3d = Integer.valueOf(electroConfigShort.substring(idx3d+2));
					electroConfigShort = electroConfigShort.replace("3d"+e3d, "3d"+(--e3d));
				}
			}
			else {
				atomNumber--;
				electroConfigShort = atomicNumberToElectroConfig.get(atomNumber);
			}
			ion--;
		}
		
		String electroConfigFull = electroConfigShort;
		while (electroConfigFull.charAt(0) == '[') {
			int nobleGasAtomicNumber = getAtomNumberFromElement(electroConfigFull.substring(1, 3));
			electroConfigFull = atomicNumberToElectroConfig.get(nobleGasAtomicNumber) + electroConfigFull.substring(4);
		}
	    return electroConfigShort + "   " + electroConfigFull;
	}
}
