package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.panels.ButtonPanel;
import view.panels.graphpanels.DrawPanel;

public class Auswertung extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -7668987072300376408L;

	private static Auswertung instance;

	private GridBagConstraints c;
	private static final String[] attribute = { "ID", "ART", "BRAND", "VOLUMEN", "PAWN" }; // , "ARTS", "BRANDS",
																							// "VOLUMEN",
	// "PAWN" <-
	// weitere
	// attribute
	private DrawPanel drawArea;
	private JPanel bar;
	private JPanel foo;

	public static Auswertung getInstance() {
		if (instance == null) {
			instance = new Auswertung();
		}
		return instance;
	}

	private Auswertung() {
		super("Auswertung");
		init();
		config();
		build();
	}

	private void init() {
		this.setPreferredSize(new Dimension(1100, 950));
		this.setMinimumSize(new Dimension(1100, 950));
		this.setMaximumSize(new Dimension(1100, 950));
		bar = new JPanel(new GridBagLayout());
		foo = new JPanel(new GridLayout(1, 0));
		drawArea = DrawPanel.getInstance();
		c = new GridBagConstraints();
		this.setLayout(new GridLayout(1, 1));
	}

	private void config() {
		this.getContentPane().setLayout(new GridBagLayout());
		configBar();
		buildBar(attribute);
		configFoo();
	}

	private void configFoo() {
		foo.setBackground(Color.CYAN);
		JButton exit = new JButton("EXIT");
		JPanel endButtonPanel = new JPanel(new GridLayout(1, 1));
		/*
		 * @TODO: Exit listener überarbeiten! Frame schließt sich nicht durch exit
		 */
		exit.addActionListener(e -> {
			reset();
			this.dispose();
			this.setVisible(false);
		});
		JButton back = new JButton("BACK");
		back.addActionListener(e -> {
			this.dispose();
			reset();
			Auswertung.getInstance().setVisible(true);
		});
		endButtonPanel.add(back);
		endButtonPanel.add(exit);
		foo.add(endButtonPanel);
	}

	private void configBar() {
		bar = new JPanel(new GridBagLayout());
		bar.setBackground(Color.DARK_GRAY);
	}

	private void buildBar(String[] attribute) {
		configBar();
		bar.removeAll();
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.VERTICAL;
		bar.add(new ButtonPanel().createPanel(attribute), c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.5;
		c.weighty = 1.5;
		bar.add(drawArea, c);
		c.weightx = 0;
		c.weighty = 0;
	}

	private void build() {
		this.add(bar, 0);
		c.gridx = 0;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(foo, c);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setVisible(true);
		pack();
	}

	public void changeSize(Dimension dim) {
		this.setSize(dim.width, dim.height);
	}

	public void changeBar(String[] buttonText) {
		this.setVisible(false);
		this.remove(bar);
		buildBar(buttonText);
		bar.repaint();
		this.add(bar, 0);
		super.repaint();
		pack();
		this.setVisible(true);
	}

	private static void reset() {
		instance = new Auswertung();
	}
}