package view.menubar;

import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.panels.graphpanels.DrawPanel;

public class MenuBar extends JMenuBar {

	/**
	 *
	 */
	private static final long serialVersionUID = 3642820232748641874L;

	public MenuBar() {
		init();
	}

	private void init() {
		// Setting up the items
		JMenu optionMenu = new JMenu("Option");
		JMenu colorMenu = new JMenu("Colors");
		JMenuItem grapColor = new JMenuItem("GRAPH");
		JMenuItem topColor = new JMenuItem("ID");
		JMenuItem botColor = new JMenuItem("AMOUNT");
		JMenuItem panelColor = new JMenuItem("Background");
		// Setting up the Mnemonic´s
		panelColor.setMnemonic(KeyEvent.VK_B);
		optionMenu.setMnemonic(KeyEvent.VK_O);
		colorMenu.setMnemonic(KeyEvent.VK_C);
		grapColor.setMnemonic(KeyEvent.VK_G);
		topColor.setMnemonic(KeyEvent.VK_I);
		botColor.setMnemonic(KeyEvent.VK_A);
		// Setting up the ToolTip Text
		panelColor.setToolTipText("Ändern der Hintergrundfarbe");
		grapColor.setToolTipText("Graph");
		topColor.setToolTipText("Zahl über dem Graphen");
		botColor.setToolTipText("angezeigter Text unter dem Graphen");
		// Setting up the action Listener
		panelColor.addActionListener(e -> {
			DrawPanel.getInstance()
					.setPanelColor(JColorChooser.showDialog(this, "", DrawPanel.getInstance().getPillarColor()));
			DrawPanel.getInstance().repaint();
		});
		grapColor.addActionListener(e -> {
			DrawPanel.getInstance()
					.setPillarColor(JColorChooser.showDialog(this, "", DrawPanel.getInstance().getPillarColor()));
			DrawPanel.getInstance().repaint();
		});
		topColor.addActionListener(e -> {
			DrawPanel.getInstance()
					.setIdColor(JColorChooser.showDialog(this, "", DrawPanel.getInstance().getIdColor()));
			DrawPanel.getInstance().repaint();
		});
		botColor.addActionListener(e -> {
			DrawPanel.getInstance()
					.setNameColor(JColorChooser.showDialog(this, "", DrawPanel.getInstance().getNameColor()));
			DrawPanel.getInstance().repaint();
		});
		// Adding the Items to this
		colorMenu.add(panelColor);
		colorMenu.add(botColor);
		colorMenu.add(grapColor);
		colorMenu.add(topColor);
		optionMenu.add(colorMenu);
		this.add(optionMenu);
	}

}
