package resources.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Behaeltnis;
import resources.reader.Reader;

public class Writer {

	private String filepath;
	private int id;

	public Writer() {
		this.filepath = System.getProperty("user.dir") + "/auswertung.csv";
	}

	public void writeToCSV(List<Behaeltnis> list) {
		if (list.isEmpty()) {
			return;
		}
		createFile(filepath);

		for (Behaeltnis b : list) {
			write(String.format("%s;%s;%s;%s;%s", String.valueOf(id), b.getArt(), b.getBrand(),
					String.valueOf(b.getVol()).replace('.', ','), String.valueOf(b.getPfand()).replace('.', ',')));
		}
	}

	private void createFile(String dir) {
		File file = new File(dir);
		try {
			if (file.createNewFile()) {
				this.id = 1000;
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
					bw.write(String.format("%s;%s;%s;%s;%s\r%n", "id", "Art", "Marke", "Volumen", "Pfand"));
					bw.flush();
				}
			} else {
				Reader reader = new Reader(filepath);
				this.id = reader.getNextId();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write(String entry) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true))) {
			bw.write(entry);
			bw.newLine();
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getFilePath() {
		return this.filepath;
	}

}
