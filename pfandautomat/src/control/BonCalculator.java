package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;

import model.Behaeltnis;
import model.Dose;
import model.Glas;
import model.PfandBon;
import model.Plastik;
import view.panels.FlaschenAbgabe;

public class BonCalculator implements ActionListener {

	private PfandBon pfandBon;
	private FlaschenAbgabe screen;

	public BonCalculator(FlaschenAbgabe screen) {
		this.screen = screen;
		this.pfandBon = PfandBon.getInstance();
		screen.setPfand(pfandBon.getPfand());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("4")) {
			return;
		}
		Behaeltnis f = createFlasche(e.getActionCommand());
		calculatePfand(f.getPfand());
		pfandBon.addBottle(f);
	}

	private Behaeltnis createFlasche(String type) {
		String key = getKey(type); // von value auf key schließen
		if (key.length() == 0) {
			return null;
		}
		key = key.substring(key.indexOf('.') + 1).toLowerCase();
		Behaeltnis f = null;
		if (key.equalsIgnoreCase("plastik")) {
			f = new Plastik(
					FlaschenCreator.getRndBrand(PropertyHandler.getBrand(),
							FlaschenCreator.getRndType(PropertyHandler.allowedTypes(), key)),
					FlaschenCreator.getRndVol(PropertyHandler.getVolumen(), key));
		} else if (key.equalsIgnoreCase("dose")) {
			f = new Dose(
					FlaschenCreator.getRndBrand(PropertyHandler.getBrand(),
							FlaschenCreator.getRndType(PropertyHandler.allowedTypes(), key)),
					FlaschenCreator.getRndVol(PropertyHandler.getVolumen(), key));
		} else if (key.equalsIgnoreCase("glas")) {
			f = new Glas(new Random().nextBoolean(),
					FlaschenCreator.getRndBrand(PropertyHandler.getBrand(),
							FlaschenCreator.getRndType(PropertyHandler.allowedTypes(), key)),
					FlaschenCreator.getRndVol(PropertyHandler.getVolumen(), key));
		}
		return f;
	}

	private String getKey(String type) {
		for (Entry<Object, Object> entry : PropertyHandler.getLanguage().entrySet()) {
			if (Objects.equals(type, entry.getValue())) {
				return (String) entry.getKey();
			}
		}
		return null;
	}

	private void calculatePfand(double amount) {
		screen.setPfand(roundPfand(pfandBon.getPfand() + amount));
	}

	public static double roundPfand(double amount) {
		return Math.round(amount * 1000.0) / 1000.0;
	}

	public double getPfand() {
		return pfandBon.getPfand();
	}

}
