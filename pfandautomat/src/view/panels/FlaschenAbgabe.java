package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Property;
import logic.StateListener;
import view.Screen;

public class FlaschenAbgabe extends JPanel implements Panel {

	private GridBagConstraints c;
	private GridBagLayout layout;
	private Screen frame;
	private String lang;
	private JButton pfand;
	private JLabel label;
	private JButton back;
	private StateListener state;

	public FlaschenAbgabe(Screen frame) {
		System.out.println("flasche abgeben");
		this.frame = frame;
		init();
		config();
	}

	private void init() {
		state = new StateListener(frame);
		c = new GridBagConstraints();
		layout = new GridBagLayout();
		lang = frame.getLanguage();
		back = new JButton(Property.getLanguage().getProperty(lang + ".zurück"));
		pfand = new JButton(Property.getLanguage().getProperty(lang + ".pfandbon"));
		label = new JLabel(Property.getLanguage().getProperty(lang + ".pfand") + ":");
	}

	private void config() {
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		addButton(11, 1, 1, 1, new JButton(Property.getLanguage().getProperty(lang + ".plastik")));
		addButton(11, 4, 1, 1, new JButton(Property.getLanguage().getProperty(lang + ".glas")));
		addButton(11, 7, 1, 1, new JButton(Property.getLanguage().getProperty(lang + ".dose")));
		addButton(15, 15, 4, 1, pfand);
		addComponents(5, 12, 0, 0, label);
		addComponents(0, 0, 9, 9, new JLabel(new ImageIcon("src/resources/pfandautomat.jpg")));
		configBackBtn();
		this.setBackground(Color.green);
	}

	private void addComponents(int x, int y, int gridHeight, int gridWidth, Component component) {
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(component, c);
	}

	private void configBackBtn() {
		back.addActionListener(state);
		back.setActionCommand("0");
		c.gridx = 30;
		c.gridy = 30;
		c.anchor = GridBagConstraints.SOUTHEAST;
		this.add(back, c);
	}

	@Override
	public void addButton(int x, int y, int gridHeight, int gridWidth, JButton button) {
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(button, c);
	}

}
