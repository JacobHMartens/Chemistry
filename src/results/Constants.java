package results;

final class Constants {
	
	static final double NAvigadro = 6.022 * Math.pow(10, 23);
	static final double KBolzmann = 1.38 * Math.pow(10, -23);
	static final double stdTemp = 273.15 + 25;  // Kelvin
	static final double stdPres = 1;  // bar
	static final double RIdealGas = 0.082057;  // L*atm / K*mol
	static final double RThermo = 8.314;  // J / K*mol
	static final double hPlanck = 6.63 * Math.pow(10, -34);  // J*s
	static final double c = 3.00 * Math.pow(10, 8);  // m/s

	private Constants() {}  // Private constructor to prevent class instantiation
}
