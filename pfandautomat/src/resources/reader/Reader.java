package resources.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
			while (br.readLine() != null) {
				if (br.readLine() != null) {
					line = br.readLine();
				}
				if (line != null) {
					System.out.println(line);
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

	private void visuelleAusgabe(Map<?, ?> map) {
		System.out.println("Visuelle Ausgabe:");
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			System.out.println(String.format("%s => %s", entry.getKey(), entry.getValue()));
		}
		System.out.println("[ENDE]");
	}

	/*
	 * 1000;Plastik;Fiji;1;0,25
	 */
	public Map<String, Integer> getArtMap() {
//		HashMap<String, Integer> map = new Map<>();
		Map<String, Integer> map = new TreeMap<>();
		for (String s : csvEntrys) {
			int firstSemi = s.indexOf("" + ';') + 1;
			map = mapping(map, s.substring(firstSemi, s.indexOf("" + ';', firstSemi)));
		}
		return map;
	}

	public Map<Integer, ArrayList<String>> mapPfandBons() {
		HashMap<Integer, ArrayList<String>> map = new HashMap<>();
		for (String s : csvEntrys) {
			int firstSemi = s.indexOf("" + ';');
			Integer id = Integer.parseInt(s.substring(0, firstSemi));
			String entry = s.substring(firstSemi + 1);
			if (map.containsKey(id)) {
				ArrayList<String> list = map.get(id);
				list.add(entry);
			} else {
				ArrayList<String> list = new ArrayList<>();
				list.add(entry);
				map.put(id, list);
			}

		}
		Map<Integer, ArrayList<String>> sortedMap = new TreeMap<>(map);
		visuelleAusgabe(sortedMap);
		return sortedMap;
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

}
