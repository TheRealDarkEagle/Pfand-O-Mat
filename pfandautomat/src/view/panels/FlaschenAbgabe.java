package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.BonCalculator;
import control.PropertyHandler;
import control.listener.StateListener;
import view.Screen;

public class FlaschenAbgabe extends JPanel implements IPanel {

	private GridBagConstraints c;
	private GridBagLayout layout;
	private Screen frame;
	private String lang;
	private JLabel label;
	private StateListener state;
	private BonCalculator bc;

	public FlaschenAbgabe(Screen frame) {
		System.out.println("flasche abgeben");
		this.frame = frame;
		init();
		config();
	}

	public void setPfand(double amount) {
		this.label.setText(PropertyHandler.getLanguage().getProperty(lang + ".pfand") + ": " + amount + "€");
	}

	private void init() {
		label = new JLabel(PropertyHandler.getLanguage().getProperty(lang + ".pfand") + ":");
		state = new StateListener(frame);
		c = new GridBagConstraints();
		layout = new GridBagLayout();
		lang = frame.getLanguage();
		bc = new BonCalculator(this);
	}

	private void config() {
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		addButton(11, 1, 1, 1, new JButton(PropertyHandler.getLanguage().getProperty(lang + ".plastik")));
		addButton(11, 4, 1, 1, new JButton(PropertyHandler.getLanguage().getProperty(lang + ".glas")));
		addButton(11, 7, 1, 1, new JButton(PropertyHandler.getLanguage().getProperty(lang + ".dose")));
		addComponents(5, 12, 0, 0, label);
		addComponents(0, 0, 9, 9, new JLabel(new ImageIcon("src/resources/pfandautomat.jpg")));
		this.setBackground(Color.green);
	}

	private void addComponents(int x, int y, int gridHeight, int gridWidth, Component component) {
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(component, c);
	}

	@Override
	public void addButton(int x, int y, int gridHeight, int gridWidth, JButton button) {
		button.addActionListener(bc);
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(button, c);
	}

}
