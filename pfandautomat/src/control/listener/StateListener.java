package control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PfandBon;
import pfandomatexe.PfandOMat;
import view.Screen;
import view.panels.ChooseLanguage;
import view.panels.FlaschenAbgabe;
import view.panels.StartPanel;

public class StateListener implements ActionListener {

	private Screen frame;
	private StartPanel start;
	private FlaschenAbgabe flAbgabe;
	private ChooseLanguage lang;

	public StateListener(Screen frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		exchangeState(e.getActionCommand());
//		changeState(e.getActionCommand());

	}

	private void changeState(String state) {
		frame.setState(Integer.valueOf(state));
	}

	private void exchangeState(String state) {
		switch (state) {
		case "0":
			frame.exchangeStatePanel(new StartPanel(frame));
			break;
		case "1":
			frame.exchangeStatePanel(new FlaschenAbgabe(frame));
			break;
		case "2":
			frame.exchangeStatePanel(new ChooseLanguage(frame));
			break;
		case "3":
//			frame.exchangeStatePanel(panel);
			break;
		case "99":
			frame.dispose();
			PfandBon.getInstance().resetPfand();
			new PfandOMat();
			frame = new Screen();
			break;
		}
	}

}
