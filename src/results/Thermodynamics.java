package results;

import static data.DataReader.*;
import static results.Constants.RThermo;

public class Thermodynamics {
	
	private static String getPhaseFormat(String phase) {
		phase = phase.toLowerCase();
		if (phase.equals("gas") || phase.equals("g"))
	        return "(g)";
		else if (phase.equals("liquid") || phase.equals("l"))
			return "(l)";
		else if (phase.equals("solid") || phase.equals("s"))
	        return "(s)";
		else if (phase.equals("aqua") || phase.equals("aqueous") || phase.equals("aq"))
	        return "(aq)";
		return null;
	}
	
	public static String getStdEnthalpyFromFormulaAndPhase(String formula, String phase) {
		phase = getPhaseFormat(phase);
		try {
			double enthalpy = formulaToEnthalpy.get(formula+phase);
			return String.format("%f kJ/mol", enthalpy);
		}
	    catch (Exception e) {
	    	System.out.println(String.format("Formula: %s not in data base.", formula));
			return null;
	    }
	}
	
	public static String getStdEnthalpyFromReaction(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases) {
		double reactantsSum = 0., productsSum = 0.;
		String phase;
		int coefficient, k;
		for (int i=0; i < reactants.length; i++) {
			String reactant = reactants[i];
			phase = reactantPhases[i];
			coefficient = 1;
			k = 0;
			while (Character.isDigit(reactant.charAt(k))) {
				k++;
			}
			if (k != 0) {
				coefficient = Integer.valueOf((String) reactant.subSequence(0, k));
			    reactant = reactant.substring(String.valueOf(coefficient).length());
			    String stdEnthalpyString = getStdEnthalpyFromFormulaAndPhase(reactant, phase);
			    double stdEnthalpy = Double.valueOf(stdEnthalpyString.substring(0, stdEnthalpyString.length()-7));
			    reactantsSum += coefficient * stdEnthalpy;
			}
		}
		for (int j=0; j < products.length; j++) {
			String product = products[j];
			phase = productPhases[j];
			coefficient = 1;
			k = 0;
			while (Character.isDigit(product.charAt(k))) {
				k++;
			}	
			if (k != 0) {
				coefficient = Integer.valueOf(product.substring(0, k));
				product = product.substring(String.valueOf(coefficient).length());
			}
			String stdEnthalpyString = getStdEnthalpyFromFormulaAndPhase(product, phase);
			double stdEnthalpy = Double.valueOf(stdEnthalpyString.substring(0, stdEnthalpyString.length()-7));
			productsSum += coefficient * stdEnthalpy;
		}
	
	    return String.format("%f kJ/mol", productsSum - reactantsSum);
	}
	
	public static String getStdEntropyFromFormulaAndPhase(String formula, String phase) {
		phase = getPhaseFormat(phase);
		try {
			double entropy = formulaToEntropy.get(formula+phase);
			return String.format("%f J/(K*mol)", entropy);
		}
	    catch (Exception e) {
	    	System.out.println(String.format("Formula: %s not in data base.", formula));
			return null;
	    }
	}
	
	public static String getStdEntropyFromReaction(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases) {
		double reactantsSum = 0., productsSum = 0.;
		String phase;
		int coefficient, k;
		for (int i=0; i < reactants.length; i++) {
			String reactant = reactants[i];
			phase = reactantPhases[i];
			coefficient = 1;
			k = 0;
			while (Character.isDigit(reactant.charAt(k))) {
				k++;
			}
			if (k != 0) {
				coefficient = Integer.valueOf((String) reactant.subSequence(0, k));
			    reactant = reactant.substring(String.valueOf(coefficient).length());
			    String stdEntropyString = getStdEntropyFromFormulaAndPhase(reactant, phase);
				double stdEntropy = Double.valueOf(stdEntropyString.substring(0, stdEntropyString.length()-10));
			    reactantsSum += coefficient * stdEntropy;
			}
		}
		for (int j=0; j < products.length; j++) {
			String product = products[j];
			phase = productPhases[j];
			coefficient = 1;
			k = 0;
			while (Character.isDigit(product.charAt(k))) {
				k++;
			}	
			if (k != 0) {
				coefficient = Integer.valueOf(product.substring(0, k));
				product = product.substring(String.valueOf(coefficient).length());
			}
			String stdEntropyString = getStdEntropyFromFormulaAndPhase(product, phase);
			double stdEntropy = Double.valueOf(stdEntropyString.substring(0, stdEntropyString.length()-10));
			productsSum += coefficient * stdEntropy;
		}
	
	    return String.format("%f J/(K*mol)", productsSum - reactantsSum);
	}
	
	public static String getStdGibbsFreeEnergyFromFormulaAndPhase(String formula, String phase) {
		phase = getPhaseFormat(phase);
		try {
			double gibbsFreeEnergy = formulaToGibbsFreeEnergy.get(formula+phase);
			return String.format("%f kJ/mol", gibbsFreeEnergy);
		}
	    catch (Exception e) {
	    	System.out.println(String.format("Formula: %s not in data base.", formula));
			return null;
	    }
	}
	
	public static String getStdGibbsFreeEnergyFromReaction(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases) {
		double reactantsSum = 0., productsSum = 0.;
		String phase;
		int coefficient, k;
		for (int i=0; i < reactants.length; i++) {
			String reactant = reactants[i];
			phase = reactantPhases[i];
			coefficient = 1;
			k = 0;
			while (Character.isDigit(reactant.charAt(k))) {
				k++;
			}
			if (k != 0) {
				coefficient = Integer.valueOf((String) reactant.subSequence(0, k));
			    reactant = reactant.substring(String.valueOf(coefficient).length());
			    String stdGibbsString = getStdGibbsFreeEnergyFromFormulaAndPhase(reactant, phase);
				double stdGibbs = Double.valueOf(stdGibbsString.substring(0, stdGibbsString.length()-7));
			    reactantsSum += coefficient * stdGibbs;
			}
		}
		for (int j=0; j < products.length; j++) {
			String product = products[j];
			phase = productPhases[j];
			coefficient = 1;
			k = 0;
			while (Character.isDigit(product.charAt(k))) {
				k++;
			}	
			if (k != 0) {
				coefficient = Integer.valueOf(product.substring(0, k));
				product = product.substring(String.valueOf(coefficient).length());
			}
			String stdGibbsString = getStdGibbsFreeEnergyFromFormulaAndPhase(product, phase);
			double stdGibbs = Double.valueOf(stdGibbsString.substring(0, stdGibbsString.length()-7));
			productsSum += coefficient * stdGibbs;
		}
	
	    return String.format("%f kJ/mol", productsSum - reactantsSum);
	}
	
	public static String getGibbsFromEnthalpyAndEntropyAndTemp(double enthalpy, double entropy, double temp) {
		return String.format("%f J/mol or kJ/mol (depends on which units you used for the entropy and enthalpy)", enthalpy - temp * entropy);
	}
	
	public static String getSpontaneousTempFromEnthalpyAndEntropy(double enthalpy, double entropy) {
	    return "Greater than: " + Double.valueOf(enthalpy/entropy) + " Kelvin\nAssuming you used correct units!\n";
	}
	
	public static String getStdGibbsFromTempAndEquilConst(double temp, double K) {
		return String.valueOf(-RThermo*temp*Math.log(K)) + " J/mol";
	}
	
	public static String getEquilConstForStdFreeEnergyFromReactionAndTemp(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases, double temp) {
	    // Std Gibbs conversion to Joule
		String stdGibbsString = getStdGibbsFreeEnergyFromReaction(reactants, reactantPhases, products, productPhases);
		double stdGibbs = 1000 * Double.valueOf(stdGibbsString.substring(0, stdGibbsString.length()-7));
	    
	    String K = String.valueOf(Math.exp(stdGibbs / (-RThermo*temp)));
	    if (K.contains("e"))
	        K = K.replace("e+", " x 10^");
	    return K;
	}
}
