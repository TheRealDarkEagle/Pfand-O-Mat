package control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PfandBon;
import resources.writer.Writer;
import view.Screen;
import view.panels.BonNote;
import view.panels.ChooseLanguage;
import view.panels.FlaschenAbgabe;
import view.panels.StartPanel;

public class StateListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != "auswertung") {
			exchangeState(e.getActionCommand());
		} else {

		}

	}

	private void exchangeState(String state) {
		String lang = Screen.getInstance().getLanguage();
		switch (state) {
		case "0":
			Screen.getInstance().exchangeStatePanel(new StartPanel(lang));
			break;
		case "1":
			Screen.getInstance().exchangeStatePanel(new FlaschenAbgabe());
			break;
		case "2":
			Screen.getInstance().exchangeStatePanel(new ChooseLanguage());
			break;
		case "3":
			Writer writer = new Writer();
			writer.writeToCSV(PfandBon.getInstance().bon);
			Screen.getInstance().exchangeStatePanel(new BonNote(lang));
			PfandBon.getInstance().resetPfand();
			break;
		case "4":
			Screen.getInstance().exchangeStatePanel(new BonNote(lang));
			break;
		case "99":
			PfandBon.getInstance().resetPfand();
			Screen.getInstance().exchangeStatePanel(new StartPanel(lang));
			break;
		}
	}

}
