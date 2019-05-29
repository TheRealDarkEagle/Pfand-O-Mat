package view.panels.graphpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JPanel;

import control.PropertyHandler;
import model.Behaeltnis;
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

	private Map<String, ArrayList<Behaeltnis>> objectMap;
	private Reader reader;

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
		objectMap = reader.getBonObjectMap();
	}

	private void visuellOutput(Map<String, ArrayList<Behaeltnis>> map) {
		System.out.println("[STARTING MAP OUTPUT");
		for (Entry<String, ArrayList<Behaeltnis>> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			for (Behaeltnis b : entry.getValue()) {
				System.out.printf("%s %s %s %s %n", b.getArt(), b.getBrand(), b.getVol(), b.getPfand());
			}
		}
		System.out.println("[ENDING] MAP OUTPUT");
	}

	public void drawID() {
		draw(objectMap, 11);
	}

	public void drawArt() {
		HashMap<String, ArrayList<Behaeltnis>> map = new HashMap<>();
		map.put("Plastik", countObjects("plastik"));
		map.put("GLas", countObjects("glas"));
		map.put("Dose", countObjects("dose"));
		draw(map, 8);
	}

	public void drawBrand() {
		TreeMap<String, ArrayList<Behaeltnis>> map = new TreeMap<>();
		for (Map.Entry<Object, Object> entry : PropertyHandler.getBrand().entrySet()) {
			String item = String.valueOf(entry.getValue());
			map.put(item, countObjects(item));
		}
		draw(map, 5);
	}

	public void drawVolumen() {
		TreeMap<String, ArrayList<Behaeltnis>> map = new TreeMap<>();
		for (Map.Entry<Object, Object> entry : PropertyHandler.getVolumen().entrySet()) {
			String vol = String.valueOf(entry.getValue());
			map.put(vol, countObjects(vol));
		}
		draw(map, 13);
	}

	public void drawPawn() {
		TreeMap<String, ArrayList<Behaeltnis>> map = new TreeMap<>();
		ArrayList<String> pawnList = getPawnList();
		for (String pawn : pawnList) {
			for (Map.Entry<String, ArrayList<Behaeltnis>> entry : objectMap.entrySet()) {

				map.put(pawn, countObjects(pawn));
			}
		}
		draw(map, 15);
	}

	private ArrayList<String> getPawnList() {
		ArrayList<String> pawns = new ArrayList<>();
		for (Map.Entry<String, ArrayList<Behaeltnis>> entry : objectMap.entrySet()) {
			for (Behaeltnis s : entry.getValue()) {
				String pawn = String.valueOf(s.getPfand());
				if (!pawns.contains(pawn)) {
					pawns.add(pawn);
				}
			}
		}
		return pawns;
	}

	private ArrayList<Behaeltnis> countObjects(String item) {
		ArrayList<Behaeltnis> items = new ArrayList<>();
		for (Map.Entry<String, ArrayList<Behaeltnis>> entry : objectMap.entrySet()) {
			for (Behaeltnis s : entry.getValue()) {
				if (s.toString().toLowerCase().contains(item.toLowerCase())) {
					items.add(s);
				}
			}
		}
		return items;
	}

	/*
	 * Methode welche den kompletten Graphen zeichnet (summe + graph + id)
	 */
	private void drawGraph(Graphics g, int xPos, int yPos, int width, int height, String name, String amount) {
		g.setColor(Color.red);
		g.fillRect(xPos, yPos - height, width, height);
		drawName(g, xPos + (width / 2), yPos, name);
		drawAmount(g, xPos + (width / 2), yPos - height, amount);
	}

	/*
	 * Name under the Graph
	 */
	private void drawName(Graphics g, int xPos, int yPos, String name) {
		g.setColor(Color.white);
		for (char c : name.toCharArray()) {
			yPos += 13;
			xPos += 1;
			g.drawString("" + c, xPos, yPos);
		}
	}

	/*
	 * schreibt die summe beim jeweiligen graphen
	 */
	private void drawAmount(Graphics g, int xPos, int yPos, String amount) {
		g.setColor(Color.yellow);
		g.drawString(amount, xPos - (3 * amount.length()), yPos - 5);
	}

	private void draw(Map<String, ArrayList<Behaeltnis>> map, int bottomSpacing) {
		Graphics g = getGraphics();
		super.paintComponent(g);
		int width = this.getWidth() / map.size();
		int whitespace = (int) (width * 0.2) + 1;
		width -= whitespace;
		int counter = 0;
		// hier soll der graph enden
		int graphBottom = (int) (this.getSize().getHeight() - (this.getSize().getHeight() / bottomSpacing));
		int multiplier = calculateMultiplicator(map, bottomSpacing);
		for (Entry<String, ArrayList<Behaeltnis>> entry : map.entrySet()) {
			int height = multiplier * entry.getValue().size();
			int xPos = whitespace + (whitespace * counter) + (width * counter) - 5;
			drawGraph(g, xPos, graphBottom, width, height, String.valueOf(entry.getKey()),
					String.valueOf(entry.getValue().size()));
			counter++;
		}
	}

	private int calculateMultiplicator(Map<String, ArrayList<Behaeltnis>> entry, int bottomSpacing) {
		int multiplier = 10;
		int maxSize = getMaxSize(entry);
		int panelHight = this.getHeight() - bottomSpacing - 100;
		int height = multiplier * maxSize;
		while (height > panelHight) {
			multiplier--;
			height = multiplier * maxSize;
		}
		return multiplier;
	}

	private int getMaxSize(Map<String, ArrayList<Behaeltnis>> entry) {
		int maxSize = 0;
		for (Map.Entry<String, ArrayList<Behaeltnis>> m : entry.entrySet()) {
			if (m.getValue().size() > maxSize) {
				maxSize = m.getValue().size();
			}
		}
		return maxSize;
	}

	public static void reset() {
		panelInstance = new DrawPanel();
	}

}