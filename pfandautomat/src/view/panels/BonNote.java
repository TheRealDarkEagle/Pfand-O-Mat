package view.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.PropertyHandler;
import model.Behaeltnis;
import model.PfandBon;

public class BonNote extends JPanel {

	private JTextArea textArea;
	private PfandBon bon;
	private JScrollPane scroll;
	private String lang;

	public BonNote(String lang) {
		this.lang = lang;
		init();
		config();
		fillTextArea();
	}

	private void init() {
		this.setBackground(Color.LIGHT_GRAY);
		textArea = new JTextArea(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll = new JScrollPane(textArea);

	}

	private void config() {
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBackground(Color.lightGray);
		textArea.setEditable(false);
		this.add(scroll);
	}

	private void fillTextArea() {
		PfandBon bon = PfandBon.getInstance();
		if (bon.bon.size() == 0) {
			return;
		}
		textArea.append("Marke:  \t\tVolumen:  \tPfand:  \r\n");
		double sum = 0;
		for (Behaeltnis b : bon.bon) {
			String line = String.format(b.getBrand() + "\t\t     " + b.getVol() + " L\t  " + b.getPfand() + " €");
			textArea.append(line + "\n\r");
			sum += b.getPfand();
		}
		textArea.append("-------------------------------------------------------------------------------\r\n");
		if (String.valueOf(sum).length() < 4) {
			textArea.append(PropertyHandler.getLanguage().getProperty(lang + ".summe") + ": \t\t\t  "
					+ String.valueOf(sum) + "0 €");
		} else {
			textArea.append(PropertyHandler.getLanguage().getProperty(lang + ".summe") + ": \t\t\t  "
					+ String.valueOf(Double.toString(sum)).substring(0, 4) + " €");
		}
	}
}
