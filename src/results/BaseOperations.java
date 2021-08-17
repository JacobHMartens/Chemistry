package results;

import java.util.HashMap;
import java.util.Map;

import objects.Ion;

final class BaseOperations {
	
	private BaseOperations() {}
	
	static Map<String, Integer> splitFormula(String formula) {
		Map<String, Integer> formulaDict = new HashMap<String, Integer>();
		String tmpAtom = "", tmpNumber = "";
		int coef;
		
		while (formula.length() > 0) {
			if (formula.startsWith("(")) {
				int i = 0;
				while (formula.charAt(i) != ')') {
					i++;
				}
				int j = i+1;
				while (Character.isDigit(formula.charAt(j)) && j < formula.length() - 1) {
					j++;
				}
				int multiplier = Integer.valueOf(formula.substring(i+1, j));
				System.out.println(formula);
				formula = formula.substring(j) + formula.substring(1, i).repeat(multiplier);
				System.out.println(formula);
			}
			
			if (Character.isDigit(formula.charAt(0))) {
				tmpNumber += formula.charAt(0);
			}
			
			else {
				if (Character.isUpperCase(formula.charAt(0)) && !tmpAtom.equals("")) {
					if (tmpNumber.length() > 0)
						coef = Integer.valueOf(tmpNumber);
					else
						coef = 1;
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
			coef = Integer.valueOf(tmpNumber);
		else
			coef = 1;
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
	
	static Ion getIonChargeAndRoot(String formula) {
		if (!(formula.contains("+") || formula.contains("-"))) {
			return new Ion(formula, 0);
		}
		int ion = 0;
		int startIdx = formula.lastIndexOf("(") + 1;
		int endIdx = formula.lastIndexOf(")") - 1;
		if (startIdx == endIdx) {
			ion = 1;
		}
		else {
			ion = Integer.valueOf(formula.substring(startIdx, endIdx));
		}
		if (formula.charAt(endIdx) == '-')
			ion *= -1;
		formula = formula.substring(0, startIdx-1);	
		return new Ion(formula, ion);
	}
}
