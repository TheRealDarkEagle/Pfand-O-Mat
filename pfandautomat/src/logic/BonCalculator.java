package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;

import model.Dose;
import model.Flasche;
import model.Glas;
import model.Plastik;
import view.Screen;

public class BonCalculator implements ActionListener {

	private double pfandBon;
	private Screen screen;

	public BonCalculator(Screen screen) {
		this.screen = screen;
		this.pfandBon = 0.0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Flasche f = createFlasche(e.getActionCommand());
		double amount = f.getPfand();
		calculatePfand(amount);
	}

	private Flasche createFlasche(String type) {
		String key = getKey(type); // von value auf key schließen
		key = key.substring(key.indexOf('.') + 1).toLowerCase();
		Flasche f = null;
		if (key.equalsIgnoreCase("plastik")) {
			f = new Plastik(
					FlaschenCreator.getRndBrand(Property.getBrand(),
							FlaschenCreator.getRndType(Property.allowedTypes(), key)),
					FlaschenCreator.getRndVol(Property.getVolumen(), key));
		} else if (key.equalsIgnoreCase("dose")) {
			f = new Dose(
					FlaschenCreator.getRndBrand(Property.getBrand(),
							FlaschenCreator.getRndType(Property.allowedTypes(), key)),
					FlaschenCreator.getRndVol(Property.getVolumen(), key));
		} else if (key.equalsIgnoreCase("glas")) {
			f = new Glas(new Random().nextBoolean(),
					FlaschenCreator.getRndBrand(Property.getBrand(),
							FlaschenCreator.getRndType(Property.allowedTypes(), key)),
					FlaschenCreator.getRndVol(Property.getVolumen(), key));
		} else {
			System.out.println("Objekt wird nicht angenommen");
		}
		return f;
	}

	private String getKey(String type) {
		for (Entry<Object, Object> entry : Property.getLanguage().entrySet()) {
			if (Objects.equals(type, entry.getValue())) {
				return (String) entry.getKey();
			}
		}
		return null;
	}

	private void calculatePfand(double amount) {
		this.pfandBon += amount;
		this.pfandBon = Math.round(this.pfandBon * 100) / 100.0;
		System.out.println(this.pfandBon);
	}

}
