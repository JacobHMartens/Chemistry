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
}
