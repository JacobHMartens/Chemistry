package results;

import static data.DataReader.*;
import static results.Constants.RThermo;

public class Thermodynamics {
	
	private static String getPhaseFormat(String phase) {
		phase = phase.toLowerCase();
		if (phase == "gas" || phase == "g")
	        return "(g)";
		else if (phase == "liquid" || phase == "l")
			return "(l)";
		else if (phase == "solid" || phase == "s")
	        return "(s)";
		else if (phase == "aqua" || phase == "aqueous" || phase == "aq")
	        return "(aq)";
		return null;
	}
	
	public static Double getStdEnthalpyFromFormulaAndPhase(String formula, String phase) {
		phase = getPhaseFormat(phase);
		try {
			double enthalpy = formulaToEnthalpy.get(formula+phase);
			return enthalpy;
		}
	    catch (Exception e) {
	    	System.out.println(String.format("Formula: %s not in data base.", formula));
			return null;
	    }
	}
	
	public static double getStdEnthalpyFromReaction(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases) {
		double reactantsSum = 0., productsSum = 0.;
		for (int i=0; i < reactants.length; i++) {
			String reactant = reactants[i];
			String phase = reactantPhases[i];
			int coefficient = 1;
			int k = 0;
			while (Character.isDigit(reactant.charAt(k))) {
				k ++;
			    if (k != 0) {
			    	coefficient = Integer.valueOf((String) reactant.subSequence(0, k));
			        reactant = reactant.substring(String.valueOf(coefficient).length());
			        reactantsSum += coefficient * getStdEnthalpyFromFormulaAndPhase(reactant, phase);
			    }
			    for (int j=0; j < products.length; j++) {
			        String product = products[j];
			        phase = productPhases[j];
			        coefficient = 1;
			        k = 0;
			        while (Character.isDigit(product.charAt(k))) {
			            k ++;
					}	
			        if (k != 0) {
			            coefficient = Integer.valueOf(product.substring(0, k));
			            product = product.substring(String.valueOf(coefficient).length());
			        }
			        productsSum += coefficient * getStdEnthalpyFromFormulaAndPhase(product, phase);
			    }
			}
		}
			    return productsSum - reactantsSum;
	}
	
	public static Double getStdEntropyFromFormulaAndPhase(String formula, String phase) {
		phase = getPhaseFormat(phase);
		try {
			double entropy = formulaToEntropy.get(formula+phase);
			return entropy;
		}
	    catch (Exception e) {
	    	System.out.println(String.format("Formula: %s not in data base.", formula));
			return null;
	    }
	}
	
	public static double getStdEntropyFromReaction(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases) {
		double reactantsSum = 0, productsSum = 0;
		for (int i=0; i < reactants.length; i++) {
			String reactant = reactants[i];
			String phase = reactantPhases[i];
			int coefficient = 1;
			int k = 0;
			while (Character.isDigit(reactant.charAt(k))) {
				k ++;
			    if (k != 0) {
			    	coefficient = Integer.valueOf((String) reactant.subSequence(0, k));
			        reactant = reactant.substring(String.valueOf(coefficient).length());
			        reactantsSum += coefficient * getStdEntropyFromFormulaAndPhase(reactant, phase);
			    }
			    for (int j=0; j < products.length; j++) {
			        String product = products[j];
			        phase = productPhases[j];
			        coefficient = 1;
			        k = 0;
			        while (Character.isDigit(product.charAt(k))) {
			            k ++;
					}	
			        if (k != 0) {
			            coefficient = Integer.valueOf(product.substring(0, k));
			            product = product.substring(String.valueOf(coefficient).length());
			        }
			        productsSum += coefficient * getStdEntropyFromFormulaAndPhase(product, phase);
			    }
			}
		}
			    return productsSum - reactantsSum;
	}
	
	public static Double getStdGibbsFreeEnergyFromFormulaAndPhase(String formula, String phase) {
		phase = getPhaseFormat(phase);
		try {
			double gibbsFreeEnergy = formulaToGibbsFreeEnergy.get(formula+phase);
			return gibbsFreeEnergy;
		}
	    catch (Exception e) {
	    	System.out.println(String.format("Formula: %s not in data base.", formula));
			return null;
	    }
	}
	
	public static double getStdGibbsFreeEnergyFromReaction(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases) {
		double reactantsSum = 0, productsSum = 0;
		for (int i=0; i < reactants.length; i++) {
			String reactant = reactants[i];
			String phase = reactantPhases[i];
			int coefficient = 1;
			int k = 0;
			while (Character.isDigit(reactant.charAt(k))) {
				k ++;
			    if (k != 0) {
			    	coefficient = Integer.valueOf((String) reactant.subSequence(0, k));
			        reactant = reactant.substring(String.valueOf(coefficient).length());
			        reactantsSum += coefficient * getStdGibbsFreeEnergyFromFormulaAndPhase(reactant, phase);
			    }
			    for (int j=0; j < products.length; j++) {
			        String product = products[j];
			        phase = productPhases[j];
			        coefficient = 1;
			        k = 0;
			        while (Character.isDigit(product.charAt(k))) {
			            k ++;
					}	
			        if (k != 0) {
			            coefficient = Integer.valueOf(product.substring(0, k));
			            product = product.substring(String.valueOf(coefficient).length());
			        }
			        productsSum += coefficient * getStdGibbsFreeEnergyFromFormulaAndPhase(product, phase);
			    }
			}
		}
			    return productsSum - reactantsSum;
	}
	
	public static double getGibbsFromEnthalpyAndEntropyAndTemp(double enthalpy, double entropy, double temp) {
		return enthalpy - temp * entropy;
	}
	
	public static String getSpontaneousTempFromEnthalpyAndEntropy(double enthalpy, double entropy) {
	    return "Greater than: " + Double.valueOf(enthalpy/entropy) + " Kelvin\nAssuming you used correct units!\n";
	}
	
	public static String getStdGibbsFromTempAndEquilConst(double temp, double K) {
		return String.valueOf(-RThermo*temp*Math.log(K)) + " J/mol";
	}
	
	public static String getEquilConstForStdFreeEnergyFromReactionAndTemp(String[] reactants, String[] reactantPhases, String[] products, String[] productPhases, double temp) {
	    // Std Gibbs in Joule
	    double stdGibbs = 1000 * getStdGibbsFreeEnergyFromReaction(reactants, reactantPhases, products, productPhases);
	    String K = String.valueOf(Math.exp(stdGibbs / (-RThermo*temp)));
	    if (K.contains("e"))
	        K = K.replace("e+", " x 10^");
	    return K;
	}
}
