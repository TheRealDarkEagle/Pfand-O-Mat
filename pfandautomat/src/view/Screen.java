package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Property;
import logic.StateListener;
import view.panels.StartPanel;

public class Screen extends JFrame {

	private JButton language;
	private GridBagLayout layout;
	private GridBagConstraints c;
	private JPanel panel;
	private String lang;
	private JButton back;

	public Screen() {
		init();
		config();
		build();
	}

	private void init() {
		this.lang = "en";
		back = new JButton(Property.getLanguage().getProperty(lang + "zurück"));
		this.panel = new StartPanel(this);
		this.layout = new GridBagLayout();
		this.c = new GridBagConstraints();
		this.setLayout(layout);

	}

	private void config() {
		back.addActionListener(new StateListener(this));
		back.setActionCommand("0");
		c.anchor = GridBagConstraints.SOUTHEAST;
		this.getContentPane().add(back, c);
		this.setTitle("Pfann-O-Mat 2k.9");
		this.setSize(500, 500);
		this.add(this.panel);
	}

	private void build() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setLanguage(String lang) {
		this.lang = lang;
	}

	public String getLanguage() {
		return this.lang;
	}

	public void exchangeStatePanel(JPanel panel) {
		this.setVisible(false);
		this.remove(this.panel);
		this.panel = panel;
		this.add(this.panel);
		this.setVisible(true);
	}
}
