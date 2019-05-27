package view.panels;

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
	private BonCalculator bc;
	private JButton pfandBon;

	public FlaschenAbgabe() {
		this.frame = Screen.getInstance();
		init();
		config();
	}

	public void setPfand(double amount) {
		this.label.setText(PropertyHandler.getLanguage().getProperty(lang + ".pfand") + ": " + amount + "€");
	}

	private void init() {
		label = new JLabel(PropertyHandler.getLanguage().getProperty(lang + ".pfand") + ":");
		c = new GridBagConstraints();
		layout = new GridBagLayout();
		lang = frame.getLanguage();
		bc = new BonCalculator(this);
		pfandBon = new JButton(PropertyHandler.getLanguage().getProperty(lang + ".bon"));
	}

	private void config() {
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		c.weighty = 0.5;
		addButton(11, 1, 1, 1, new JButton(PropertyHandler.getLanguage().getProperty(lang + ".plastik")), true);
		addButton(11, 4, 1, 1, new JButton(PropertyHandler.getLanguage().getProperty(lang + ".glas")), true);
		addButton(11, 7, 1, 1, new JButton(PropertyHandler.getLanguage().getProperty(lang + ".dose")), true);
		c.fill = GridBagConstraints.BOTH;
		addComponents(5, 12, 0, 0, label);
		addComponents(0, 0, 9, 9, new JLabel(new ImageIcon("src/resources/pfandautomat.jpg")));
		pfandBon.setActionCommand("4");
		pfandBon.addActionListener(new StateListener());
		c.weighty = 0.0;
		addButton(30, 30, 0, 1, pfandBon, false);
	}

	private void addComponents(int x, int y, int gridHeight, int gridWidth, Component component) {
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		c.weightx = 0.5;
		c.weighty = 0.5;
		this.add(component, c);
	}

	@Override
	public void addButton(int x, int y, int gridHeight, int gridWidth, JButton button, boolean addActionListener) {
		if (addActionListener) {
			button.addActionListener(bc);
		}
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(button, c);
	}

}
