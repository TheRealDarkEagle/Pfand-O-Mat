package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.PropertyHandler;
import control.listener.StateListener;
import view.panels.StartPanel;

public class Screen extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 7642258153977410674L;

	private static Screen screenInstance;

	private GridBagConstraints c;
	private JPanel switchtingPanel;
	private JPanel endPanel;
	private String lang;
	private JButton back;
	private JButton abbruch;
	private JButton getPfand;
	private Dimension dim;
	private StateListener screenListener;

	public static Screen getInstance() {
		if (screenInstance == null) {
			screenInstance = new Screen();
		}
		return screenInstance;
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
		getPfand = new JButton(PropertyHandler.getLanguage().getProperty(lang + ".erhaltePfand"));
		getPfand.setActionCommand("3");
		getPfand.addActionListener(screenListener);
	}

	private void changeButtonText() {
		back.setText(PropertyHandler.getLanguage().getProperty(lang + ".zurueck"));
		abbruch.setText(PropertyHandler.getLanguage().getProperty(lang + ".abbruch"));
		getPfand.setText(PropertyHandler.getLanguage().getProperty(lang + ".erhaltePfand"));
	}

	private void addEndPanel() {
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
		c.gridx = 20;
		endPanel.add(getPfand, c);
	}

	private void build() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(getAdminListener());
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

	private KeyListener getAdminListener() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 521) {
					Auswertung.getInstance().setVisible(true);
				}
			}
		};
	}

}
