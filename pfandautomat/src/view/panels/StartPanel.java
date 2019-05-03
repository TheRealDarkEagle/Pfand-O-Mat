package view.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import logic.Property;
import logic.StateListener;
import view.Screen;

public class StartPanel extends JPanel implements Panel {

	private JButton flasche;
	private JButton lang;
	private JButton bon;
	private Screen frame;
	private GridBagLayout layout;
	private GridBagConstraints c;
	private StateListener listener;

	public StartPanel(Screen frame) {
		System.out.println("startpanel");
		this.frame = frame;
		init();
		config();
	}

	private void init() {
		listener = new StateListener(frame);
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		this.setLayout(layout);
	}

	private void config() {
		flasche = new JButton(Property.getLanguage().getProperty(frame.getLanguage() + ".abgeben"));
		lang = new JButton(Property.getLanguage().getProperty(frame.getLanguage() + ".sprache"));
		bon = new JButton(Property.getLanguage().getProperty(frame.getLanguage() + ".pfandbon"));
		flasche.setActionCommand("1");
		lang.setActionCommand("2");
		bon.setActionCommand("3");
		addButton(0, 0, 1, 1, flasche);
		addButton(1, 0, 1, 1, lang);
		addButton(2, 0, 1, 1, bon);
		this.setBackground(Color.blue);

	}

	@Override
	public void addButton(int x, int y, int gridHeight, int gridWidth, JButton button) {
		button.addActionListener(listener);
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(button, c);
	}

}
