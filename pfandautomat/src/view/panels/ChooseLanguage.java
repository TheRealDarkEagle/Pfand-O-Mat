package view.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.LanguageListener;
import view.Screen;

public class ChooseLanguage extends JPanel implements IPanel {

	private Screen frame;
	private GridBagConstraints c;
	private LanguageListener listener;

	public ChooseLanguage() {
		this.frame = Screen.getInstance();
		init();
		config();
	}

	private void init() {
		this.setSize(500, 500);
		listener = new LanguageListener(frame);
		c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

	}

	private void config() {
		c.fill = GridBagConstraints.BOTH;
		Properties prop = PropertyHandler.getFlags();
		Enumeration<?> e = prop.propertyNames();
		int x;
		int y;
		x = y = 0;
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			try {
				addButton(x, y, 1, 1,
						new JButton(PropertyHandler.getLanguage().getProperty(name),
								new ImageIcon(ImageIO.read(
										this.getClass().getResource(PropertyHandler.getFlags().getProperty(name))))),
						true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			x += 10;
		}
		frame.add(this);
	}

	@Override
	public void addButton(int x, int y, int gridHeight, int gridWidth, JButton button, boolean addActionListener) {
		button.setPreferredSize(new Dimension(125, 125));
		button.setMinimumSize(new Dimension(50, 50));
		button.setMaximumSize(new Dimension(170, 170));
		if (addActionListener) {
			button.addActionListener(listener);
		}
		c.gridx = x;
		c.gridy = y;
		this.add(button, c);
	}

}
