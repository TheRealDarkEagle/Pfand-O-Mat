package model;

public class Flasche implements Flaschen {

	private double pfand;
	private String brand;
	private double vol;

	public Flasche(double pfand, String brand, double vol) {
		this.pfand = pfand;
		this.brand = brand;
		this.vol = vol;
	}

	public Flasche(boolean handle, String brand, double vol) {
		if (handle) {
			this.pfand = 0.16;
		} else {
			this.pfand = 0.08;
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
