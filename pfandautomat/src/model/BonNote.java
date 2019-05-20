package model;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BonNote extends JPanel {

	private JTextArea textArea;
	private PfandBon bon;

	public BonNote() {
		init();
		config();
		fillTextArea();
	}

	private void init() {
		this.setBackground(Color.LIGHT_GRAY);
		textArea = new JTextArea();
	}

	private void config() {
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBackground(Color.lightGray);
		textArea.setEditable(false);
		this.add(textArea);
		textArea.setLocation(0, 0);
	}

	private void fillTextArea() {
		PfandBon bon = PfandBon.getInstance();
		if (bon.bon.size() == 0) {
			return;
		}
		textArea.append("Marke:  \tVolumen:  \tPfand:  \r\n");
		double sum = 0;
		for (Behaeltnis b : bon.bon) {
			String line = String.format(b.brand + "\t  " + b.vol + " L\t  " + b.pfand + " €");
			textArea.append(line + "\n\r");
			sum += b.pfand;
		}
		textArea.append("----------------------------------------------------------\r\n");
		textArea.append("Summe: \t\t " + String.valueOf(Double.toString(sum)).substring(0, 4) + " €");
	}
}
