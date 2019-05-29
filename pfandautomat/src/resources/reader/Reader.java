package resources.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import model.Behaeltnis;
import model.Dose;
import model.Glas;
import model.Plastik;

public class Reader {

	private String filepath;
	private ArrayList<String> csvEntrys;

	public Reader(String filepath) {
		this.filepath = filepath;
		readFile();
	}

	/*
	 * Searches through the csv file to find the last used id number it returns the
	 * last used id number+1
	 */
	private void readFile() {
		csvEntrys = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filepath)))) {
			while ((line = br.readLine()) != null) {
				if (!line.contains("id")) {
					csvEntrys.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNextId() {
		String lastEntry = csvEntrys.get(csvEntrys.size() - 1);
		return Integer.parseInt(lastEntry.substring(0, lastEntry.indexOf(';'))) + 1;
	}

	/*
	 * 1000;Plastik;Fiji;1;0,25
	 */
	public Map<String, Integer> getArtMap() {
		Map<String, Integer> map = new TreeMap<>();
		for (String s : csvEntrys) {
			int firstSemi = s.indexOf("" + ';') + 1;
			map = mapping(map, s.substring(firstSemi, s.indexOf("" + ';', firstSemi)));
		}
		return map;
	}

	public Map<String, ArrayList<String>> mapPfandBons() {
		TreeMap<String, ArrayList<String>> map = new TreeMap<>();
		for (String s : csvEntrys) {
			String id = getID(s);
			String entry = getEntry(s);
			if (map.containsKey(id)) {
				ArrayList<String> list = map.get(id);
				list.add(entry);
			} else {
				ArrayList<String> list = new ArrayList<>();
				list.add(entry);
				map.put(id, list);
			}

		}
		return map;
	}

	private String getID(String entry) {
		return String.valueOf(Integer.parseInt(entry.substring(0, entry.indexOf("" + ';'))));
	}

	private String getEntry(String entry) {
		return entry.substring(entry.indexOf("" + ';') + 1);
	}

	public Map<String, ArrayList<Behaeltnis>> getBonObjectMap() {
		TreeMap<String, ArrayList<Behaeltnis>> map = new TreeMap<>();
		for (String s : csvEntrys) {
			String id = getID(s);
			Behaeltnis bottle = getObject(getEntry(s));
			map.putIfAbsent(id, new ArrayList<Behaeltnis>());
			map.get(id).add(bottle);
		}

		return map;
	}

	private Map<String, Integer> mapping(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, (map.get(key) + 1));
		} else {
			map.put(key, 1);
		}
		return map;
	}

	public String[] getIDs(Map<?, ?> map) {
		String[] id = new String[map.size()];
		int counter = 0;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			id[counter++] = String.valueOf(entry.getKey());
		}
		return id;
	}

	// double pfand, String brand, double vol, String art
	private Behaeltnis getObject(String data) {
		int firstSemi = data.indexOf("" + ';');
		int secondSemi = data.indexOf("" + ';', firstSemi + 1);
		String brand = data.substring(firstSemi + 1, secondSemi);
		double vol = Double.parseDouble(
				data.substring(secondSemi + 1, data.indexOf("" + ';', secondSemi + 1)).replaceAll(",", "."));
		if (data.toLowerCase().contains("plastik")) {
			return new Plastik(brand, vol);
		} else if (data.toLowerCase().contains("glas")) {
			if (data.contains("0,08")) {
				return new Glas(false, brand, vol);
			} else {
				return new Glas(true, brand, vol);
			}
		} else if (data.toLowerCase().contains("dose")) {
			return new Dose(brand, vol);
		}
		return null;
	}

}
