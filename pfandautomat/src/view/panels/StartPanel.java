package view.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.StateListener;

public class StartPanel extends JPanel implements IPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 139883265940726890L;
	private GridBagConstraints c;
	private StateListener listener;
	private String language;

	public StartPanel(String language) {
		this.language = language;
		init();
		config();
	}

	private void init() {
		this.setMinimumSize(new Dimension(400, 400));
		this.setPreferredSize(new Dimension(400, 400));
		this.setMaximumSize(new Dimension(400, 400));
		listener = new StateListener();
		c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
	}

	private void config() {
		JButton flasche;
		JButton lang;
		flasche = new JButton(PropertyHandler.getLanguage().getProperty(language + ".abgeben"));
		lang = new JButton(PropertyHandler.getLanguage().getProperty(language + ".sprache"));
		flasche.setActionCommand("1");
		lang.setActionCommand("2");
		c.fill = GridBagConstraints.BOTH;
		addButton(0, 0, 1, 1, flasche, true);
		c.fill = GridBagConstraints.HORIZONTAL;
		addButton(1, 0, 1, 1, lang, true);

	}

	@Override
	public void addButton(int x, int y, int gridHeight, int gridWidth, JButton button, boolean addActionListener) {
		if (addActionListener) {
			button.addActionListener(listener);
		}
		c.gridx = x;
		c.gridy = y;
		c.gridheight = gridHeight;
		c.gridwidth = gridWidth;
		this.add(button, c);
	}

}
