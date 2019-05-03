package view.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.StateListener;
import view.Screen;

public class StartPanel extends JPanel implements IPanel {

	private JButton flasche;
	private JButton lang;
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
		this.setSize(500, 500);
		this.setBackground(Color.GRAY);
		listener = new StateListener(frame);
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		this.setLayout(layout);
	}

	private void config() {
		flasche = new JButton(PropertyHandler.getLanguage().getProperty(frame.getLanguage() + ".abgeben"));
		lang = new JButton(PropertyHandler.getLanguage().getProperty(frame.getLanguage() + ".sprache"));
		flasche.setActionCommand("1");
		lang.setActionCommand("2");
		addButton(0, 0, 1, 1, flasche);
		c.fill = GridBagConstraints.HORIZONTAL;
		addButton(0, 10, 1, 1, lang);

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
