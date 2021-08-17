package objects;

public class Ion {
	
	private String formula;
	private int ion;
	
	public Ion(String formula, int ion) {
		this.formula = formula;
		this.ion = ion;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int getIon() {
		return ion;
	}

	public void setIon(int ion) {
		this.ion = ion;
	}
}
