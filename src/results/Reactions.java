package results;

import static results.AtomsAndMolecules.getMassFromMole;
import static results.AtomsAndMolecules.getMolesFromMass;
import static results.AtomsAndMolecules.getMolarMassFromFormula;
import static results.BaseOperations.getPrecedingNumbers;

public class Reactions {
	
	public static double getMassFromLiquidReactantsAndMolarAndVolume(String targetProduct, String[] reactants, double[] molar, double[] volume) {
	    double limReactant = -1;
	    for (int i=0; i < reactants.length; i++) {
	    	System.out.println(reactants.length);
	        double tmpMole = molar[i]*volume[i];
	        if (limReactant == -1)
	            limReactant = tmpMole;
	        else if (limReactant > tmpMole)
	            limReactant = tmpMole;
	    }
	    return getMassFromMole(targetProduct, limReactant);
	}
	
	public static double getMassFromGasReactantsAndMass(String targetProduct, String[] reactants, double[] mass) {
	    double limReactant = -1;
	    String targetProductMole = getPrecedingNumbers(targetProduct);
	    if (targetProductMole == "")
	        targetProductMole = "1";
	    for (int i=0; i < reactants.length; i++) {
	        String curMole = "1";
	        String potMole = getPrecedingNumbers(reactants[i]);
	        if (potMole != "")
	            curMole = potMole;
	  
	        String pureReactant = reactants[i].substring(getPrecedingNumbers(reactants[i]).length());
	        double tmpMole = (Integer.valueOf(targetProductMole) / Integer.valueOf(curMole)) * getMolesFromMass(pureReactant, mass[i]);
	        if (limReactant == -1)
	            limReactant = tmpMole;
	        else if (limReactant > tmpMole)
	            limReactant = tmpMole;
	    }
	    String pureTargetProduct = targetProduct.substring(getPrecedingNumbers(targetProduct).length());
	    return limReactant * getMolarMassFromFormula(pureTargetProduct);
	}
}
