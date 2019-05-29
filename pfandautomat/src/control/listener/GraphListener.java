package control.listener;

import java.awt.event.ActionListener;

import view.panels.graphpanels.DrawPanel;

public class GraphListener {

	public ActionListener getListener(String name) {
		switch (name) {
		case "ID":
			return e -> DrawPanel.getInstance().drawID();
		case "ART":
			return e -> DrawPanel.getInstance().drawArt();
		case "BRAND":
			return e -> DrawPanel.getInstance().drawBrand();
		case "VOLUMEN":
			return e -> DrawPanel.getInstance().drawVolumen();
		case "PAWN":
			return e -> DrawPanel.getInstance().drawPawn();
		default:

		}
		return null;
	}
}
