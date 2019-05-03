package control;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class FlaschenCreator {

	public static double getRndVol(Properties prop, String key) {
		ArrayList<String> selectedList = sortForValues(prop, key);
		int number = new Random().nextInt(selectedList.size());
		return Double.parseDouble(selectedList.get(number));
	}

	public static String getRndBrand(Properties prop, String key) {
		ArrayList<String> selectedList = sortForValues(prop, key);
		int number = new Random().nextInt(selectedList.size());
		return selectedList.get(number);
	}

	private static ArrayList<String> sortForValues(Properties prop, String key) {
		ArrayList<String> propList = new ArrayList<>();
		Set<Entry<Object, Object>> entries = prop.entrySet();
		for (Entry<Object, Object> entry : entries) {
			if (entry.getKey().toString().contains(key)) {
				propList.add(entry.getValue().toString());
			}
		}
		return propList;
	}

	public static String getRndType(Properties prop, String key) {
		ArrayList<String> selectedList = sortForValues(prop, key);
		ArrayList<String> splitteEntrys = splitList(selectedList);
		int counter = countEntrys(splitteEntrys);
		int number = new Random().nextInt(counter);
		return splitteEntrys.get(number);
	}

	private static int countEntrys(ArrayList<String> splitteEntrys) {
		int counter = 0;
		for (String s : splitteEntrys) {
			counter++;
		}
		return counter;
	}

	private static ArrayList<String> splitList(ArrayList<String> selectedList) {
		ArrayList<String> splitteEntrys = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < selectedList.get(0).length(); i++) {
			if (selectedList.get(0).charAt(i) == ',') {
				splitteEntrys.add(sb.toString());
				sb = new StringBuilder();
				i++;
			}
			sb = sb.append(selectedList.get(0).charAt(i));
		}
		splitteEntrys.add(sb.toString());
		return splitteEntrys;
	}

}
