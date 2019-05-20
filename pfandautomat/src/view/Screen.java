package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.StateListener;
import view.panels.StartPanel;

public class Screen extends JFrame {

	private GridBagConstraints c;
	private JPanel switchtingPanel;
	private JPanel endPanel;
	private String lang;
	private JButton back;
	private JButton abbruch;

	public Screen() {
		init();
		config();
		addDefaultButtons();
		build();
	}

	private void init() {
		endPanel = new JPanel();
		this.lang = "en";
		c = new GridBagConstraints();
		switchtingPanel = new StartPanel(this);
	}

	private void config() {
		c.fill = GridBagConstraints.BOTH;
		this.setTitle("Pfann-O-Mat 2k.9");
		this.setSize(600, 450);
		this.setLayout(new GridBagLayout());
		this.add(this.switchtingPanel);
		back = new JButton(PropertyHandler.getLanguage().getProperty(lang + ".zurueck"));
		abbruch = new JButton(PropertyHandler.getLanguage().getProperty(lang + ".abbruch"));
		StateListener screenListener = new StateListener(this);
		back.addActionListener(screenListener);
		back.setActionCommand("0");
		abbruch.addActionListener(screenListener);
		abbruch.setActionCommand("99");
	}

	private void changeButtonText() {
		back.setText(PropertyHandler.getLanguage().getProperty(lang + ".zurueck"));
		abbruch.setText(PropertyHandler.getLanguage().getProperty(lang + ".abbruch"));
	}

	private void addDefaultButtons() {
		c.gridx = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridy = 100;
		endPanel.add(back, c);
		c.gridx = 2;
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		endPanel.add(abbruch, c);
	}

	private void build() {
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(endPanel, c);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setLanguage(String lang) {
		this.lang = lang;
		this.remove(abbruch);
		this.remove(back);
		changeButtonText();
	}

	public String getLanguage() {
		return this.lang;
	}

	public void exchangeStatePanel(JPanel panel) {
		this.setVisible(false);
		this.remove(this.switchtingPanel);
		this.switchtingPanel = panel;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		this.add(panel, c);
		changeButtonText();
		addDefaultButtons();
		this.setVisible(true);

	}
}
