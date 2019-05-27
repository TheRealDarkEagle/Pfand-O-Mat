package view.panels.graphPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import resources.reader.Reader;
import resources.writer.Writer;
import view.panels.Draw;

public class DrawPanel extends Draw {

	private static DrawPanel Panel_Instance;
	public static final Dimension MIN_SIZE = new Dimension(500, 300);
	public static final Dimension PREFFEREDSIZE = new Dimension(1200, 1200);
	public static final Dimension MAX_SIZE = new Dimension(1600, 1600);

	private final static String[] graphs = { "Pilier", "Cake", "Tracer" };
	private Map<Integer, ArrayList<String>> bonMap;
	private Reader reader;
	private int whitespace = 50; // abstand zwischen den säulen
	private int x = 30; // Abstand zum linken rand
	private int drawsumLine = 50; // unten links -> startpunkt für y

	public static DrawPanel getInstance() {
		if (Panel_Instance == null) {
			Panel_Instance = new DrawPanel();
		}
		return Panel_Instance;
	}

	private DrawPanel() {
		this.setBackground(Color.BLACK);
		this.setMinimumSize(MIN_SIZE);
		this.setPreferredSize(PREFFEREDSIZE);
		this.setMaximumSize(MAX_SIZE);
		this.setSize(MAX_SIZE);
		this.reader = new Reader(new Writer().getFilePath());
		bonMap = reader.mapPfandBons();
	}

	public Map<?, ?> getBonMap() {
		return this.bonMap;
	}

	public void drawBon(Integer id) {
		Color[] colors = { Color.BLUE, Color.green, Color.ORANGE };
		Graphics g = graphic();
		super.paintComponent(g);
		ArrayList<String> entry = bonMap.get(id);
		drawPil(g, 50, 50, 50, 50);
//		for (int i = 0; i < entry.size(); i++) {
//			drawTop(g, entry.get(i), xPos, yPos);
//			drawPil(g, height, drawRectLine, drawWith, xPos);
//			drawName(g, entry.get(i), drawNameLine, xPos);
//		}
	}

	public void drawPilier(int[] stuff) {
		Graphics g = graphic();
		super.paintComponent(g);
		for (int i = 0; i < stuff.length; i++) {
			int height = stuff[i] * 100;
			int drawRectLine = ((int) this.getSize().getHeight() - height) - 50; // unten links -> startpunkt für y
			int drawNameLine = height + drawRectLine + 35; // unten links -> startpunkt für
			// y
			int drawWidth = ((this.getWidth()) - (whitespace * stuff.length)) / stuff.length;
			int xPos = x + ((drawWidth * i) + whitespace * i);
			g.setColor(Color.green);
			g.drawString("TEst " + String.valueOf(stuff[i]), xPos + 30, drawsumLine);
			drawPil(g, height, drawRectLine, drawWidth, xPos);
			g.setColor(Color.YELLOW);
			g.drawString("TEESTT2", xPos + 30, drawNameLine);
		}
	}

	/*
	 * Anzahl der flaschen pro pfandbon
	 */
	public void drawID() {
		Graphics g = this.getGraphics();
		int sizeOfMap = bonMap.size();
		int i = 0;
		for (Entry<Integer, ArrayList<String>> entry : bonMap.entrySet()) {
			int height = entry.getValue().size() * 10;
			int drawRectLine = ((int) this.getSize().getHeight() - height) - 50; // unten links -> startpunkt für y
			int drawRechtLine = drawRectLine - 50;
			int drawNameLine = height + drawRectLine + 35; // unten links -> startpunkt für
			int drawWidth = ((this.getWidth()) - (whitespace * bonMap.size())) / bonMap.size();
			int xPos = x + ((drawWidth * i) + whitespace * i);
			drawPil(g, height, drawRectLine, drawWidth, xPos);
			drawTop(g, entry, xPos + 5, drawRectLine + 10);
			drawName(g, entry, drawNameLine, xPos);
			i++;
		}
	}

	private void drawName(Graphics g, Entry<Integer, ArrayList<String>> entry, int drawNameLine, int xPos) {
		g.setColor(Color.YELLOW);
		g.drawString(String.format("ID:%s", entry.getKey()), xPos, drawNameLine);
	}

	private void drawPil(Graphics g, int height, int drawRectLine, int drawWith, int xPos) {
		g.setColor(Color.red);
		g.fillRect(xPos, drawRectLine, drawWith, height);
	}

	private void drawTop(Graphics g, Entry<Integer, ArrayList<String>> entry, int xPos, int yPos) {
		g.setColor(Color.green);
		g.drawString(String.valueOf(entry.getValue().size()), xPos, yPos);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void pilierGraph() {
		// TODO Auto-generated method stub

	}

}
