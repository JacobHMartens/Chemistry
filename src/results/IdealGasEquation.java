package results;

import static results.Constants.RIdealGas;

public class IdealGasEquation {
	
	public static Double getUnknownValue(Double p, Double V, Double n, Double T) {
		System.out.println("Remember the units must be in atm, L, mol and K!");
		if (p == null)
			return n*RIdealGas*T / V;
		else if (V == null)
			return n*RIdealGas*T / p;
		else if (n == null)
			return p*V / (RIdealGas*T);
		else if (T == null)
			return p*V / (n*RIdealGas);
		else
			System.out.println("Error: The unknown parameter should be null");
			return null;
	}
	
	public static double getBarFromAtm(double atm) {
		return atm/0.986923267;
	}
	
	public static double getAtmFromBar(double bar) {
		return 0.986923267*bar;
	}
}
