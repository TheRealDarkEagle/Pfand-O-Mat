package view.panels.graphpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import control.PropertyHandler;
import resources.reader.Reader;
import resources.writer.Writer;

public class DrawPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 7229008968245207288L;
	private static DrawPanel panelInstance;
	public static final Dimension MIN_SIZE = new Dimension(1000, 800);
	public static final Dimension PREFFEREDSIZE = new Dimension(1200, 1200);
	public static final Dimension MAX_SIZE = new Dimension(1600, 1600);

	private Map<String, ArrayList<String>> bonMap;
	private Reader reader;
	private int multiplayer = 10;

	public static DrawPanel getInstance() {
		if (panelInstance == null) {
			panelInstance = new DrawPanel();
		}
		return panelInstance;
	}

	private DrawPanel() {
		this.setBackground(Color.black);
		this.setMinimumSize(MIN_SIZE);
		this.setPreferredSize(PREFFEREDSIZE);
		this.setMaximumSize(MAX_SIZE);
		this.reader = new Reader(new Writer().getFilePath());
		bonMap = reader.mapPfandBons();
	}

	public void drawID() {
		draw(bonMap, 11);
	}

	private void draw(Map<String, ArrayList<String>> map, int bottomSpacing) {
		super.paintComponent(this.getGraphics());
		int width = this.getWidth() / map.size();
		int whitespace = (int) (width * 0.2) + 1;
		width -= whitespace;
		int counter = 0;
		// hier soll der graph enden
		int graphBottom = (int) (this.getSize().getHeight() - (this.getSize().getHeight() / bottomSpacing));
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			int height = multiplayer * entry.getValue().size();
			int xPos = whitespace + (whitespace * counter) + (width * counter);
			drawGraph(xPos, graphBottom, width, height, String.valueOf(entry.getKey()),
					String.valueOf(entry.getValue().size()));
			counter++;
		}
	}

	public void drawArt() {
		HashMap<String, ArrayList<String>> map = new HashMap<>();
		map.put("Plastik", countItem("plastik"));
		map.put("GLas", countItem("glas"));
		map.put("Dose", countItem("dose"));
		draw(map, 8);
	}

	private ArrayList<String> countItem(String item) {
		ArrayList<String> items = new ArrayList<>();
		for (Map.Entry<String, ArrayList<String>> entry : bonMap.entrySet()) {
			for (String s : entry.getValue()) {
				if (s.toLowerCase().contains(item.toLowerCase())) {
					items.add(s);
				}
			}
		}
		return items;
	}

	/*
	 * Methode welche den kompletten Graphen zeichnet (summe + graph + id)
	 */
	private void drawGraph(int xPos, int yPos, int width, int height, String name, String amount) {
		Graphics g = getGraphics();
		g.setColor(Color.red);
		g.fillRect(xPos, yPos - height, width, height);
		drawName(xPos + (width / 2), yPos, name);
		drawAmount(xPos + (width / 2), yPos - height, amount);
	}

	/*
	 * Name under the Graph
	 */
	private void drawName(int xPos, int yPos, String name) {
		Graphics g = getGraphics();
		g.setColor(Color.white);
		for (char c : name.toCharArray()) {
			yPos += 12;
			xPos -= 1;
			g.drawString("" + c, xPos, yPos);
		}
	}

	/*
	 * schreibt die summe beim jeweiligen graphen
	 */
	private void drawAmount(int xPos, int yPos, String amount) {
		Graphics g = getGraphics();
		g.setColor(Color.yellow);
		g.drawString(amount, xPos - (3 * amount.length()), yPos - 5);
	}

	public void drawBrand() {
		HashMap<String, ArrayList<String>> map = new HashMap<>();
		for (Map.Entry<Object, Object> entry : PropertyHandler.getBrand().entrySet()) {
			String item = String.valueOf(entry.getValue());
			map.put(item, countItem(item));
		}
		draw(map, 5);
	}

}