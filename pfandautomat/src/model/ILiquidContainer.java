package model;

public interface ILiquidContainer {

	double MIN = 0.08;
	double MAX = 0.25;

	double getPfand();

	String getBrand();

	double getVol();

	String getArt();

}
