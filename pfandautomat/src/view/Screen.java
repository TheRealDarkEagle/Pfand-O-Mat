package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.StateListener;
import view.panels.StartPanel;

public class Screen extends JFrame {

	private static Screen screen_instance;

	private GridBagConstraints c;
	private JPanel switchtingPanel;
	private JPanel endPanel;
	private String lang;
	private JButton back;
	private JButton abbruch;
	private Dimension dim;
	private StateListener screenListener;

	public static Screen getInstance() {
		if (screen_instance == null) {
			screen_instance = new Screen();
		}
		return screen_instance;
	}

	private Screen() {
		init();
		config();
		configEndPanel();
		build();
	}

	private void init() {
		this.lang = "de";
		dim = new Dimension(600, 450);
		endPanel = new JPanel();
		c = new GridBagConstraints();
		switchtingPanel = new StartPanel(lang);
		screenListener = new StateListener();
	}

	private void config() {
		this.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		back = new JButton(PropertyHandler.getLanguage().getProperty(lang + ".zurueck"));
		back.setMinimumSize(new Dimension(50, 30));
		abbruch = new JButton(PropertyHandler.getLanguage().getProperty(lang + ".abbruch"));
		back.addActionListener(screenListener);
		back.setActionCommand("0");
		abbruch.addActionListener(screenListener);
		abbruch.setActionCommand("99");
	}

	private void changeButtonText() {
		back.setText(PropertyHandler.getLanguage().getProperty(lang + ".zurueck"));
		abbruch.setText(PropertyHandler.getLanguage().getProperty(lang + ".abbruch"));
	}

	private void addEndPanel() {
//		endPanel.setForeground(Color.green);
//		endPanel.setBackground(Color.blue);
		c.gridwidth = 1;
		c.weighty = 0.5;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 99;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(endPanel, c);
	}

	private void configEndPanel() {
		endPanel.setMinimumSize(new Dimension(250, 50));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		endPanel.add(back, c);
		c.gridx = 10;
		c.gridwidth = GridBagConstraints.REMAINDER;
		endPanel.add(abbruch, c);
	}

	private void build() {
		this.setTitle("Pfann-O-Mat 2k.9");
		this.setSize(600, 450);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		exchangeStatePanel(switchtingPanel);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setLanguage(String lang) {
		this.lang = lang;
		changeButtonText();
	}

	public String getLanguage() {
		return this.lang;
	}

	public void exchangeStatePanel(JPanel panel) {
		this.setVisible(false);
		this.remove(this.switchtingPanel);
		this.switchtingPanel = panel;
		c.weighty = 1;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		this.add(this.switchtingPanel, c);
		addEndPanel();
		this.pack();
		this.setVisible(true);

	}
}
