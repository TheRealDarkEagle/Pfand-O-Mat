package model;

public abstract class Behaeltnis implements ILiquidContainer {

	protected double pfand;
	protected String brand;
	protected double vol;
	protected String art;

	public Behaeltnis(double pfand, String brand, double vol, String art) {
		this.pfand = pfand;
		this.brand = brand;
		this.vol = vol;
		this.art = art;
	}

	public Behaeltnis(boolean handle, String brand, double vol, String art) {
		if (handle) {
			this.pfand = (MIN * 2);
		} else {
			this.pfand = MIN;
		}
		this.brand = brand;
		this.vol = vol;
		this.art = art;
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

	@Override
	public String getArt() {
		return this.art;
	}

}
