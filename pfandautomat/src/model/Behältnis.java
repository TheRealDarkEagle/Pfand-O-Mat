package model;

public class Behältnis implements Flaschen {

	private double pfand;
	private String brand;
	private double vol;

	public Behältnis(double pfand, String brand, double vol) {
		this.pfand = pfand;
		this.brand = brand;
		this.vol = vol;
	}

	public Behältnis(boolean handle, String brand, double vol) {
		if (handle) {
			this.pfand = MIN * 2;
		} else {
			this.pfand = MIN;
		}
		this.brand = brand;
		this.vol = vol;
	}

	@Override
	public double getPfand() {
		return this.pfand;
	}

	@Override
	public String getBrand() {
		return this.brand;
	}

	@Override
	public double getVol() {
		return this.vol;
	}

}
