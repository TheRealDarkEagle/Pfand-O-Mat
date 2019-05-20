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
		if (e.getActionCommand().contains("bon")) {
			return;
		}
		Behaeltnis f = createFlasche(e.getActionCommand());
		double amount = f.getPfand();
		calculatePfand(amount);
		pfandBon.addBottle(f);
	}

	private Behaeltnis createFlasche(String type) {
		String key = getKey(type); // von value auf key schließen
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
		} else {
			System.out.println("Objekt wird nicht angenommen");
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
		double pfand = pfandBon.getPfand() + amount;
		if (String.valueOf(pfandBon.getPfand() + amount).substring(String.valueOf(pfand).indexOf('.')).length() > 3) {
			pfandBon.setPfand(Double.parseDouble(
					String.valueOf(pfand).substring(0, String.valueOf(pfandBon.getPfand() + amount).indexOf('.') + 3)));
		} else {
			pfandBon.setPfand(pfandBon.getPfand() + amount);
		}
		screen.setPfand(pfandBon.getPfand());
	}

	public double getPfand() {
		return pfandBon.getPfand();
	}

}
