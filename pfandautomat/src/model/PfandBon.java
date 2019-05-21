package model;

import java.util.ArrayList;

public class PfandBon {

	public ArrayList<Behaeltnis> bon = new ArrayList<>();

	private static PfandBon pfandbon_instance;

	private double pfand;

	public static PfandBon getInstance() {
		if (pfandbon_instance == null) {
			pfandbon_instance = new PfandBon();
		}
		return pfandbon_instance;
	}

	private PfandBon() {
		pfand = 0.0;
	}

	public double getPfand() {
		return this.pfand;
	}

	public void setPfand(double amount) {
		this.pfand = amount;
	}

	public void resetPfand() {
		bon = new ArrayList<>();
		this.pfand = 0.0;
	}

	public void addBottle(Behaeltnis bottle) {
		this.bon.add(bottle);
	}

}
