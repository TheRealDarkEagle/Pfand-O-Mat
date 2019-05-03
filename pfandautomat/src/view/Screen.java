package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.StateListener;
import view.panels.StartPanel;

public class Screen extends JFrame {

	private GridBagLayout layout;
	private GridBagConstraints c;
	private JPanel panel;
	private String lang;
	private JButton back;
	private JButton abbruch;

	public Screen() {
		init();
		config();
		build();
	}

	private void init() {
		this.lang = "en";
		this.panel = new JPanel();
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();
		this.setLayout(layout);

	}

	private void config() {
		this.getContentPane().setBackground(Color.yellow);
		this.setTitle("Pfann-O-Mat 2k.9");
		this.setSize(500, 750);
		exchangeStatePanel(new StartPanel(this));
		configButtons();
	}

	private void configButtons() {
		StateListener screenListener = new StateListener(this);
		back = new JButton(PropertyHandler.getLanguage().getProperty(getLanguage() + ".zurueck"));
		abbruch = new JButton(PropertyHandler.getLanguage().getProperty(getLanguage() + ".abbruch"));
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridy = 2;
		c.weighty = 0.5;
		back.addActionListener(screenListener);
		back.setActionCommand("0");
		this.getContentPane().add(back, c);
		c.gridx = 2;
		abbruch.addActionListener(screenListener);
		abbruch.setActionCommand("99");
		this.getContentPane().add(abbruch, c);
	}

	private void build() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setLanguage(String lang) {
		this.lang = lang;
		this.remove(abbruch);
		this.remove(back);
		configButtons();
	}

	public String getLanguage() {
		return this.lang;
	}

	public void exchangeStatePanel(JPanel panel) {
		this.setVisible(false);
		this.remove(this.panel);
		this.panel = panel;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.8;
		this.getContentPane().add(this.panel, c);
		this.setVisible(true);
	}
}
