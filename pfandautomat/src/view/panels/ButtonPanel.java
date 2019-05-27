package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.listener.GraphListener;

public class ButtonPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4023445952787622571L;

	/*
	 * Creates a dynamic Panel with Buttons only
	 */
	public ButtonPanel createPanel(String[] args) {
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(100, 700));
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 0.5;
		c.weightx = 0.5;
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(new GridLayout(args.length, 1));
		for (int i = 0; i < args.length; i++) {
			JButton button = new JButton(args[i]);
			button.setSize(button.getPreferredSize());
			button.addActionListener(new GraphListener().getListener(args[i]));
			button.setActionCommand(args[i]);
			this.add(button, c);
		}
		this.revalidate();
		return this;
	}

}
