package model;

public abstract class Behaeltnis implements ILiquidContainer {

	protected double pfand;
	protected String brand;
	protected double vol;

	public Behaeltnis(double pfand, String brand, double vol) {
		this.pfand = pfand;
		this.brand = brand;
		this.vol = vol;
	}

	public Behaeltnis(boolean handle, String brand, double vol) {
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
