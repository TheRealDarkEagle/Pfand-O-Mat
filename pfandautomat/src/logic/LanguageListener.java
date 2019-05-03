package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Screen;
import view.panels.StartPanel;

public class LanguageListener implements ActionListener {

	private Screen frame;

	public LanguageListener(Screen frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		changeLang(e.getActionCommand().toLowerCase());
	}

	private void changeLang(String lang) {
		frame.setLanguage(lang);
		frame.exchangeStatePanel(new StartPanel(frame));
		frame.setState(0);
	}
}
