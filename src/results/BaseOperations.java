package results;

import java.util.HashMap;
import java.util.Map;

final class BaseOperations {
	
	private BaseOperations() {}
	
	static Map<String, Integer> splitFormula(String formula) {
		Map<String, Integer> formulaDict = new HashMap<String, Integer>();
		String tmpAtom = "", tmpNumber = "";
		int multiplier = 1;
		int coef;
		
		while (formula.length() > 0) {
			if (formula.startsWith("(")) {
				int i = 0;
				while (formula.charAt(i) == ')') {
					i++;
				}
				int j = i+1;
				while (Character.isDigit(formula.charAt(j)) && j < formula.length() - 1) {
					j++;
				}
				multiplier = Integer.valueOf(formula.substring(i+1, j+1));
			}
			
			else if (formula.startsWith(")")) {
				multiplier = 1;
			}
			
			else if (Character.isDigit(formula.charAt(0))) {
				tmpNumber += formula.charAt(0);
			}
			
			else {
				if (Character.isUpperCase(formula.charAt(0)) && tmpAtom != "") {
					if (tmpNumber.length() > 0)
						coef = Integer.valueOf(tmpNumber)*multiplier;
					else
						coef = 1*multiplier;
					if (formulaDict.containsKey(tmpAtom))
						formulaDict.put(tmpAtom, formulaDict.get(tmpAtom) + coef);
					else
						formulaDict.put(tmpAtom, coef);
					tmpAtom = tmpNumber = "";
				}
				tmpAtom += formula.charAt(0);
			}
			formula = formula.substring(1);
		}
		
		if (tmpNumber.length() > 0)
			coef = Integer.valueOf(tmpNumber)*multiplier;
		else
			coef = 1*multiplier;
		if (formulaDict.containsKey(tmpAtom))
			formulaDict.put(tmpAtom, formulaDict.get(tmpAtom) + coef);
		else
			formulaDict.put(tmpAtom, coef);

	    return formulaDict;
	}

	static String getPrecedingNumbers(String str) {
		String precedingNumbers = "";
		for (int i=0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i)))
				precedingNumbers += str.charAt(i);
			else
				break;
		}
		return precedingNumbers;
	}
}
