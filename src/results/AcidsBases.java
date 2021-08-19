package results;

import static data.DataReader.strongWeakAcids;

public class AcidsBases {
	
	public static String getStrongWeakAcids() {
		String list = "";
		for (String line : strongWeakAcids) {
			list += line + "\n";
		}
		return list;
	}
	
	/*
	public static double getPHFromWeakAcidStrongBaseAndVolume(String acidFormula, String baseFormula, double pKa, double Volume) {
		// Q9 i maple, Q 13 i F17 exam
	}
	*/
}
