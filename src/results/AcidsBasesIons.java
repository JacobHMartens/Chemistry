package results;

import static data.DataReader.*;

public class AcidsBasesIons {
	
	public static String getNameFromFormula(String formula) {
		try {
			return acidBaseFormulaToName.get(formula);
		}
		catch (Exception e) {
			 try {
				 return ionsFormulaToName.get(formula);
			 }
			 catch (Exception e2) {
		            System.out.println(String.format("Formula: %s not in data base", formula));
			 }
		}
		return null;
	}
	
	public static String getNormalFromName(String name) {
		try {
			return acidBaseNameToFormula.get(name.toLowerCase());
		}
		catch (Exception e) {
			 try {
				 return ionsNameToFormula.get(name.toLowerCase());
			 }
			 catch (Exception e2) {
		            System.out.println(String.format("Name: %s not in data base", name));
			 }
		}
		return null;
	}
}
