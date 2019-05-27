package view.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.PropertyHandler;
import model.Behaeltnis;
import model.PfandBon;

public class BonNote extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 3977814278741536875L;
	private JTextArea textArea;
	private JScrollPane scroll;
	private String lang;
	private String linedLine;

	public BonNote(String lang) {
		this.lang = lang;
		init();
		config();
		fillTextArea();
	}

	private void init() {
		linedLine = "   -----------------------------------------------------------------------------\r\n";
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
		if (bon.bon.isEmpty()) {
			emptyBon();
			return;
		}
		textArea.append(
				String.format(" %s:  \t\t%s:  \t%s:  \r\n", PropertyHandler.getLanguage().getProperty(lang + ".marke"),
						PropertyHandler.getLanguage().getProperty(lang + ".volumen"),
						PropertyHandler.getLanguage().getProperty(lang + ".pfand")));
		double sum = 0;
		for (Behaeltnis b : bon.bon) {
			String line = String.format("  %s\t\t     %s L\t  %s €", b.getBrand(), b.getVol(), b.getPfand());
			textArea.append(line + "\n\r");
			sum += b.getPfand();
		}
		textArea.append(linedLine);
		String lastLine = null;
		String summe = PropertyHandler.getLanguage().getProperty(lang + ".summe");
		if (String.valueOf(sum).length() < 4) {
			lastLine = String.format(" %s: \t\t\t  %s0 €", summe, sum);
		} else {
			lastLine = String.format(" %s: \t\t\t %s €", summe, String.valueOf(Double.toString(sum)).substring(0, 4));
		}
		textArea.append(lastLine);
	}

	private void emptyBon() {
		textArea.append("\r\n\r\n\r\n\r\n\r\n");
		textArea.append(linedLine);
		textArea.append(String.format("   |         %s    |\r\n",
				PropertyHandler.getLanguage().getProperty(lang + ".emptyBon")));
		textArea.append(linedLine);
	}

}
