package results;

import static data.DataReader.functionalGroups;

public class OrganicMolecules {
	
	public static String getNameFromCarbonChainLength(int length) {
		String[] names = new String[] {"Methan", "Ethan", "Propan", "Butan", "Pentan", "Hexan", "Heptan", "Octan", "Nonan", "Decan"};
		return names[length];
	}
	
	public static void printFunctionalGroups() {
		for (String line : functionalGroups) {
			System.out.println(line);
		}
	}
}
