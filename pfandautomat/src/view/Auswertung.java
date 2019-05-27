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

import control.listener.GraphListener;
import view.panels.ButtonPanel;
import view.panels.graphPanels.DrawPanel;

public class Auswertung extends JFrame {

	public static final Dimension MAX = new Dimension(1200, 1000);
	public static Auswertung instance;

	private GridBagConstraints c;
	private GraphListener listener;
	private final String[] attribute = { "ID" }; // , "ARTS", "BRANDS", "VOLUMEN", "PAWN" <- weitere attribute
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
		bar = new JPanel(new GridBagLayout());
		foo = new JPanel(new GridLayout(1, 0));
		drawArea = DrawPanel.getInstance();
		c = new GridBagConstraints();
		this.setLayout(new GridLayout(1, 1));
		listener = new GraphListener();
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
		exit.addActionListener(e -> {
			this.dispose();
			reset();
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
		this.setSize(MAX);
		this.add(bar, 0);
		c.gridx = 0;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(foo, c);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
//		this.revalidate();
		super.repaint();
		pack();
		this.setVisible(true);
	}

	private void reset() {
		instance = new Auswertung();
	}
}