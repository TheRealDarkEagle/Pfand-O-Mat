package model;

public class PfandBon {

	private static PfandBon pfandbon = new PfandBon();

	private double pfand;

	private PfandBon() {
		pfand = 0.0;
	}

	public double getPfand() {
		return this.pfand;
	}

	public void setPfand(double amount) {
		this.pfand = amount;
	}

	public static PfandBon getInstance() {
		return pfandbon;
	}

}
