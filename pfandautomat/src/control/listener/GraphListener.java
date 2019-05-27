package control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import resources.reader.Reader;
import resources.writer.Writer;
import view.Auswertung;
import view.panels.graphPanels.DrawPanel;

public class GraphListener implements ActionListener {

	public ActionListener getListener(String name) {
		Reader reader = new Reader(new Writer().getFilePath());
		System.out.println("[Listener]" + name);
		switch (name) {
		case "ID":
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Auswertung.getInstance().changeBar(reader.getIDs(DrawPanel.getInstance().getBonMap()));
					DrawPanel.getInstance().drawID();
				}
			};

		default:
			return drawListener();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand().toLowerCase()) {
		case "id":
			System.out.println("CASE ID");
			break;
		case "art":
			System.out.println("CASE ART");
			break;
		}
	}

	private ActionListener drawListener() {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(String.format("DRAWLISTENER [%s] wurde gedrückt", e.getActionCommand()));
				int[] testAr = { 2, 6, 5, 1 };
				DrawPanel.getInstance().drawPilier(testAr);
				System.out.println(e.getActionCommand());
				switch (e.getActionCommand()) {
				case "size":

					break;
				default: // steht für den BonID abfang!
					DrawPanel.getInstance().drawBon(Integer.parseInt(e.getActionCommand()));
				}

			}
		};
	}

}
