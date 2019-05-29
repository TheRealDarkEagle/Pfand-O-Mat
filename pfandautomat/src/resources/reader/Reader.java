package resources.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
			int firstSemi = s.indexOf("" + ';');
			String id = String.valueOf(Integer.parseInt(s.substring(0, firstSemi)));
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

}
